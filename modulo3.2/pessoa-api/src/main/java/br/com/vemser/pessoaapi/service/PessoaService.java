package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.*;
import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.PetEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;

    public PessoaService() {

    }

    public List<PessoaDTO> list(){
        log.info("Listar todas as pessoas");

        return pessoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO listByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Listar pessoa por id");

        return convertToDTO(findByIdPessoa(idPessoa));
    }

    public List<PessoaDTO> findAllByName(String nome) {
        return pessoaRepository.findAllByNomeContainsIgnoreCase(nome).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO findByCpf(String cpf) {
        return convertToDTO(pessoaRepository.findByCpf(cpf));
    }

    public List<PessoaEnderecoDTO> listPessoaEnderecos(Integer idPessoa) {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaEnderecoDTO pessoaEnderecoDTO = convertToPessoaEndercoDTO(pessoaEntity);
                        pessoaEnderecoDTO.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(this::convertEnderecoToDTO)
                                .collect(Collectors.toList()));
                        return pessoaEnderecoDTO;
                    }).collect(Collectors.toList());
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaEnderecoDTO pessoaEnderecoDTO = convertToPessoaEndercoDTO(pessoaEntity);
                        pessoaEnderecoDTO.setEnderecos(pessoaEntity.getEnderecos().stream()
                                .map(this::convertEnderecoToDTO)
                                .collect(Collectors.toList()));
                        return pessoaEnderecoDTO;
                    }).collect(Collectors.toList());
        }
    }

    public List<PessoaContatoDTO> listPessoaContatos(Integer idPessoa) {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaContatoDTO pessoaContatoDTO = convertToPessoaContatoDTO(pessoaEntity);
                        pessoaContatoDTO.setContatos(pessoaEntity.getContatos().stream()
                                .map(this::convertContatoToDTO)
                                .collect(Collectors.toList()));
                        return pessoaContatoDTO;
                    }).collect(Collectors.toList());
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaContatoDTO pessoaContatoDTO = convertToPessoaContatoDTO(pessoaEntity);
                        pessoaContatoDTO.setContatos(pessoaEntity.getContatos().stream()
                                .map(this::convertContatoToDTO)
                                .collect(Collectors.toList()));
                        return pessoaContatoDTO;
                    }).collect(Collectors.toList());
        }
    }

    public List<PessoaPetDTO> listPessoaPet(Integer idPessoa) {
        if(idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(pessoaEntity -> {
                        PessoaPetDTO pessoaPetDTO = convertToPessoaPetDTO(pessoaEntity);
                        pessoaPetDTO.setPet(convertToPetDTO(pessoaEntity.getPet()));
                        return pessoaPetDTO;
                    }).collect(Collectors.toList());
        } else {
            return pessoaRepository.findById(idPessoa).stream()
                    .map(pessoaEntity -> {
                        PessoaPetDTO pessoaPetDTO = convertToPessoaPetDTO(pessoaEntity);
                        pessoaPetDTO.setPet(convertToPetDTO(pessoaEntity.getPet()));
                        return pessoaPetDTO;
                    }).collect(Collectors.toList());
        }
    }

    public PessoaDTO create(PessoaCreateDTO pessoaCriar) {
        log.info("Criando pessoa");

        PessoaEntity pessoaEntity = convertToEntity(pessoaCriar);
        pessoaRepository.save(pessoaEntity);

        log.warn("Pessoa criada!");

        //String tipoDeMensagem = TipoMensagem.CREATE.getTipo();
        //emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);

        return convertToDTO(pessoaEntity);
    }

    public PessoaDTO update(Integer idPessoa,
                            PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        log.info("Atualizando pessoa");
        PessoaEntity pessoaEntityRecuperada = findByIdPessoa(idPessoa);

        pessoaEntityRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaEntityRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        pessoaEntityRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaEntityRecuperada.setEmail(pessoaAtualizar.getEmail());

        pessoaRepository.save(pessoaEntityRecuperada);

        log.warn("Pessoa atualizada!");

        //String tipoDeMensagem = TipoMensagem.UPDATE.getTipo();
        //emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);

        return convertToDTO(pessoaEntityRecuperada);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        log.info("Deletando pessoa");

        PessoaEntity pessoaEntityRecuperada = findByIdPessoa(id);
        pessoaRepository.delete(pessoaEntityRecuperada);

        log.warn("Pessoa deletada!");

        //String tipoDeMensagem = TipoMensagem.DELETE.getTipo();
        //emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);
    }

    public PessoaEntity findByIdPessoa(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }

    public PessoaEntity convertToEntity(PessoaCreateDTO pessoaDTO) {
        return objectMapper.convertValue(pessoaDTO, PessoaEntity.class);
    }

    public PessoaDTO convertToDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }

    public PessoaEnderecoDTO convertToPessoaEndercoDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaEnderecoDTO.class);
    }

    public EnderecoDTO convertEnderecoToDTO(EnderecoEntity enderecoEntity) {
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public PessoaContatoDTO convertToPessoaContatoDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaContatoDTO.class);
    }

    public ContatoDTO convertContatoToDTO(ContatoEntity contatoEntity) {
        return objectMapper.convertValue(contatoEntity, ContatoDTO.class);
    }

    public PessoaPetDTO convertToPessoaPetDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaPetDTO.class);
    }

    public PetDTO convertToPetDTO(PetEntity petEntity) {
        return objectMapper.convertValue(petEntity, PetDTO.class);
    }
}
