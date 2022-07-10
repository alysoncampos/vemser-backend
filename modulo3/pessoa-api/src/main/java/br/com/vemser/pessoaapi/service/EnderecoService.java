package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    public EnderecoService() {

    }

    public List<EnderecoDTO> list() {
        log.info("Chamou listar endereço");
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Chamou listar endereco por id");
        return objectMapper.convertValue(findByIdEndereco(idEndereco), EnderecoDTO.class);
    }

    public List<EnderecoDTO> listEnderecoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Chamou listar endereco por idPessoa");
        pessoaService.findByIdPessoa(idPessoa);
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(idPessoa))
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        log.info("Chamou criar endereco");

        pessoaService.findByIdPessoa(idPessoa);

        Endereco enderecoEntity = objectMapper.convertValue(endereco, Endereco.class);
        enderecoEntity.setIdPessoa(idPessoa);
        enderecoRepository.create(enderecoEntity);

        log.info("Endereço com id=" + enderecoEntity.getIdEndereco() + " criado!");

        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public EnderecoDTO update(Integer idEndereco,
                           EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        log.info("Chamou atualizar endereço");

        pessoaService.findByIdPessoa(enderecoAtualizar.getIdPessoa());

        objectMapper.convertValue(enderecoAtualizar, Pessoa.class);

        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        enderecoRecuperado.setIdPessoa(enderecoAtualizar.getIdPessoa());
        enderecoRecuperado.setTipo(enderecoAtualizar.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizar.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizar.getComplemento());
        enderecoRecuperado.setCep(enderecoAtualizar.getCep());
        enderecoRecuperado.setCidade(enderecoAtualizar.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizar.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizar.getPais());

        log.warn("Endereço id=" + enderecoRecuperado.getIdEndereco() + " atualizado!");

        return objectMapper.convertValue(enderecoRecuperado, EnderecoDTO.class);
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Chamou o deletar endereço");

        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        enderecoRepository.list().remove(enderecoRecuperado);

        log.warn("Endereço id=" + enderecoRecuperado.getIdEndereco() + " deletado!");
    }

    public Endereco findByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoEncontrado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        return enderecoEncontrado;
    }
    
}
