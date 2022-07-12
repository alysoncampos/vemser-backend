package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.entity.TipoMensagem;
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
        return pessoaRepository.list().stream()
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

    public PessoaDTO create(PessoaCreateDTO pessoaCriar){
        log.info("Criar pessoa");
        Pessoa pessoaEntity = convertToEntity(pessoaCriar);
        pessoaRepository.create(pessoaEntity);
        log.warn("Pessoa criada!");
        PessoaDTO pessoaDTO = convertToDTO(pessoaEntity);
        String tipoDeMensagem = TipoMensagem.CREATE.getTipo();
        emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);
        return pessoaDTO;
    }

    public PessoaDTO update(Integer idPessoa,
                            PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        log.info("Atualizar pessoa");
        Pessoa pessoaEntity = convertToEntity(pessoaAtualizar);
        Pessoa pessoaRecuperada = findByIdPessoa(idPessoa);
        pessoaRecuperada.setCpf(pessoaEntity.getCpf());
        pessoaRecuperada.setNome(pessoaEntity.getNome());
        pessoaRecuperada.setDataNascimento(pessoaEntity.getDataNascimento());
        pessoaRecuperada.setEmail(pessoaEntity.getEmail());
        log.warn("Pessoa atualizada!");
        PessoaDTO pessoaDTO = convertToDTO(pessoaRecuperada);
        String tipoDeMensagem = TipoMensagem.UPDATE.getTipo();
        emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);
        return pessoaDTO;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        log.info("Deletar pessoa");
        Pessoa pessoaRecuperada = findByIdPessoa(id);
        pessoaRepository.list().remove(pessoaRecuperada);
        log.warn("Pessoa deletada!");
        PessoaDTO pessoaDTO = convertToDTO(pessoaRecuperada);
        String tipoDeMensagem = TipoMensagem.DELETE.getTipo();
        emailService.sendEmailPessoa(pessoaDTO, tipoDeMensagem);
    }

    public Pessoa findByIdPessoa(Integer id) throws RegraDeNegocioException {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));
    }

    public List<Pessoa> findByName(String nome) {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }

    public Pessoa convertToEntity(PessoaCreateDTO pessoaDTO) {
        return objectMapper.convertValue(pessoaDTO, Pessoa.class);
    }

    public PessoaDTO convertToDTO(Pessoa pessoa) {
        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }
}
