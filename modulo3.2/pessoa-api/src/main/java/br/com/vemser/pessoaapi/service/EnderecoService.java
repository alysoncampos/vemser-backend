package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.enums.TipoMensagem;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
        log.info("Listando todos os endereços");
        return enderecoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Listando endereco por id");

        return convertToDTO(findByIdEndereco(idEndereco));
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        log.info("Criando endereco");
        EnderecoEntity enderecoEntity = convertToEntity(endereco);

        PessoaEntity pessoaEntity = pessoaService.findByIdPessoa(idPessoa);

        enderecoEntity.setPessoas(Set.of(pessoaEntity));

        EnderecoDTO enderecoDTO = convertToDTO(enderecoRepository.save(enderecoEntity));
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity,PessoaDTO.class);
        enderecoDTO.setPessoa(pessoaDTO);

        log.warn("Endereço com criado!");

        //String tipoDeMensagem = TipoMensagem.CREATE.getTipo();
        //emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaEntityCadastrada), enderecoDTO, tipoDeMensagem);

        return enderecoDTO;
    }

    public EnderecoDTO update(Integer idEndereco,
                              EnderecoCreateDTO enderecoAtualizar) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.findByIdPessoa(enderecoAtualizar.getIdPessoa());

        EnderecoEntity enderecoEntityRecuperado = findByIdEndereco(idEndereco);
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoAtualizar,EnderecoEntity.class);
        enderecoEntity.setIdEndereco(idEndereco);

        log.info("Atualizando endereço");

        enderecoEntity.setPessoas(Set.of(pessoaEntity));

        EnderecoDTO enderecoDTO = convertToDTO(enderecoRepository.save(enderecoEntity));
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity,PessoaDTO.class);
        enderecoDTO.setPessoa(pessoaDTO);

        log.warn("Endereço atualizado!");

        //String tipoDeMensagem = TipoMensagem.UPDATE.getTipo();
        //emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaEntityCadastrada);

        return enderecoDTO;
    }

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        log.info("Deletar endereço");

        EnderecoEntity enderecoEntityRecuperado = findByIdEndereco(idEndereco);

        enderecoRepository.delete(enderecoEntityRecuperado);

        log.warn("Endereço deletado!");

        //String tipoDeMensagem = TipoMensagem.DELETE.getTipo();
        //emailService.sendEmailEndereco(pessoaService.convertToDTO(pessoaEntityCadastrada),
    }

    public EnderecoEntity findByIdEndereco(Integer idEndereco) throws RegraDeNegocioException {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
    }

    public EnderecoEntity convertToEntity(EnderecoCreateDTO enderecoDTO) {
        return objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);
    }

    public EnderecoDTO convertToDTO(EnderecoEntity enderecoEntity) {
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }
}
