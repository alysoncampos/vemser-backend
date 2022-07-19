package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
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

    public List<PessoaDTO> listByName(String nome) throws RegraDeNegocioException {
        log.info("Listar pessoa por nome");

        if(findByName(nome).isEmpty()){
            log.info("Nome não encontrado");
            throw new RegraDeNegocioException("Nome não encontrado");
        }
        else {
            log.info("Nome encontrado");
            return findByName(nome).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
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
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
    }

    public List<PessoaEntity> findByName(String nome) {
        return pessoaRepository.findAll().stream()
                .filter(pessoaEntity -> pessoaEntity.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public PessoaEntity convertToEntity(PessoaCreateDTO pessoaDTO) {
        return objectMapper.convertValue(pessoaDTO, PessoaEntity.class);
    }

    public PessoaDTO convertToDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }
}
