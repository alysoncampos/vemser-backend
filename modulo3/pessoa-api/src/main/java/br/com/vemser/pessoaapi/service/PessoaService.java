package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
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

    public PessoaService() {

    }
    public List<PessoaDTO> list(){
        log.info("Chamou o listar pessoa");
        return pessoaRepository.list().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .collect(Collectors.toList());
    }

    public PessoaDTO listByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Chamou o listar pessoa por id");
        return objectMapper.convertValue(findById(idPessoa), PessoaDTO.class);
    }

    public List<PessoaDTO> listByName(String nome) throws RegraDeNegocioException {
        log.info("Chamou o listar pessoa por nome");
        if(findByName(nome).isEmpty()){
            log.info("Nome não encontrado");
            throw new RegraDeNegocioException("Nome não encontrado");
        }
        else {
            log.info("Nome encontrado");
            return findByName(nome).stream()
                    .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                    .collect(Collectors.toList());
        }
    }

    public PessoaDTO create(PessoaCreateDTO pessoa){
        log.info("Chamou criar pessoa");

        Pessoa pessoaEntity = objectMapper.convertValue(pessoa, Pessoa.class);
        pessoaRepository.create(pessoaEntity);

        log.info("Pessoa " + pessoaEntity.getNome() + " criada!");

        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }

    public PessoaDTO update(Integer idPessoa,
                         PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        log.info("Chamou atualizar pessoa");

        objectMapper.convertValue(pessoaAtualizar, Pessoa.class);

        Pessoa pessoaRecuperada = findById(idPessoa);
        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());

        log.info("Pessoa " + pessoaRecuperada.getNome() + " atualizada!");

        return objectMapper.convertValue(pessoaRecuperada, PessoaDTO.class);
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        log.info("Chamou deletar pessoa");

        Pessoa pessoaRecuperada = findById(id);
        pessoaRepository.list().remove(pessoaRecuperada);

        log.warn("Pessoa " + pessoaRecuperada.getNome() + " deletada!");
    }

    public Pessoa findById(Integer id) throws RegraDeNegocioException {
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
}
