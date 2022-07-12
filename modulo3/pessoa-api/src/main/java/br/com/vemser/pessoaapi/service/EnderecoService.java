package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.entity.TipoMensagem;
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

    @Autowired
    private EmailService emailService;

    public EnderecoService() {

    }

    public List<EnderecoDTO> list() {
        log.info("Listar todos os endereços");
        return enderecoRepository.list().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Listar endereco por id");
        return convertToDTO(findByIdEndereco(idEndereco));
    }

    public List<EnderecoDTO> listEnderecoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Listar endereco por id de Pessoa");
        pessoaService.findByIdPessoa(idPessoa);
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(idPessoa))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        log.info("Criar endereco");
        Pessoa pessoaCadastrada = pessoaService.findByIdPessoa(idPessoa);
        Endereco enderecoEntity = convertToEntity(endereco);
        enderecoEntity.setIdPessoa(idPessoa);
        enderecoRepository.create(enderecoEntity);
        log.warn("Endereço com criado!");
        EnderecoDTO enderecoDTO = convertToDTO(enderecoEntity);
        String tipoDeMensagem = TipoMensagem.CREATE.getTipo();
        emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaCadastrada), enderecoDTO, tipoDeMensagem);
        return enderecoDTO;
    }

    public EnderecoDTO update(Integer idEndereco,
                              EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        log.info("Atualizar endereço");
        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        Endereco enderecoEntity = convertToEntity(enderecoAtualizar);
        Pessoa pessoaCadastrada = pessoaService.findByIdPessoa(enderecoEntity.getIdPessoa());
        enderecoRecuperado.setTipo(enderecoEntity.getTipo());
        enderecoRecuperado.setLogradouro(enderecoEntity.getLogradouro());
        enderecoRecuperado.setNumero(enderecoEntity.getNumero());
        enderecoRecuperado.setComplemento(enderecoEntity.getComplemento());
        enderecoRecuperado.setCep(enderecoEntity.getCep());
        enderecoRecuperado.setCidade(enderecoEntity.getCidade());
        enderecoRecuperado.setEstado(enderecoEntity.getEstado());
        enderecoRecuperado.setPais(enderecoEntity.getPais());
        log.warn("Endereço atualizado!");
        EnderecoDTO enderecoDTO = convertToDTO(enderecoRecuperado);
        String tipoDeMensagem = TipoMensagem.UPDATE.getTipo();
        emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaCadastrada),
                enderecoDTO, tipoDeMensagem);
        return enderecoDTO;
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Deletar endereço");
        Endereco enderecoRecuperado = findByIdEndereco(idEndereco);
        Pessoa pessoaCadastrada = pessoaService.findByIdPessoa(enderecoRecuperado.getIdPessoa());
        enderecoRepository.list().remove(enderecoRecuperado);
        log.warn("Endereço deletado!");
        String tipoDeMensagem = TipoMensagem.DELETE.getTipo();
        emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaCadastrada),
                convertToDTO(enderecoRecuperado), tipoDeMensagem);
    }

    public Endereco findByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoEncontrado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        return enderecoEncontrado;
    }

    public Endereco convertToEntity(EnderecoCreateDTO enderecoDTO) {
        return objectMapper.convertValue(enderecoDTO, Endereco.class);
    }

    public EnderecoDTO convertToDTO(Endereco endereco) {
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }
}
