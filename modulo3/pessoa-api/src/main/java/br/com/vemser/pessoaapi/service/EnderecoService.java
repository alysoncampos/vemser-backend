package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.entity.Endereco;
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
        log.info("Listar todos os endereços");
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Listar endereco por id");
        return objectMapper.convertValue(findByIdEndereco(idEndereco), EnderecoDTO.class);
    }

    public List<EnderecoDTO> listEnderecoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Listar endereco por id de Pessoa");
        pessoaService.findByIdPessoa(idPessoa);
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(idPessoa))
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        log.info("Criar endereco");
        pessoaService.findByIdPessoa(idPessoa);
        Endereco enderecoEntity = objectMapper.convertValue(endereco, Endereco.class);
        enderecoEntity.setIdPessoa(idPessoa);
        enderecoRepository.create(enderecoEntity);
        log.warn("Endereço com criado!");
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public EnderecoDTO update(Integer idEndereco,
                           EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        log.info("Atualizar endereço");
        Endereco enderecoEntity = objectMapper.convertValue(enderecoAtualizar, Endereco.class);
        pessoaService.findByIdPessoa(enderecoEntity.getIdPessoa());
        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        enderecoRecuperado.setTipo(enderecoEntity.getTipo());
        enderecoRecuperado.setLogradouro(enderecoEntity.getLogradouro());
        enderecoRecuperado.setNumero(enderecoEntity.getNumero());
        enderecoRecuperado.setComplemento(enderecoEntity.getComplemento());
        enderecoRecuperado.setCep(enderecoEntity.getCep());
        enderecoRecuperado.setCidade(enderecoEntity.getCidade());
        enderecoRecuperado.setEstado(enderecoEntity.getEstado());
        enderecoRecuperado.setPais(enderecoEntity.getPais());
        log.warn("Endereço atualizado!");
        return objectMapper.convertValue(enderecoRecuperado, EnderecoDTO.class);
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Deletar endereço");
        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        enderecoRepository.list().remove(enderecoRecuperado);
        log.warn("Endereço deletado!");
    }

    public Endereco findByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoEncontrado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        return enderecoEncontrado;
    }
}
