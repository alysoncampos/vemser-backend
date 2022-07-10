package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    public ContatoService() {

    }

    public List<ContatoDTO> list(){
        log.info("Chamou o listar contato");
        return contatoRepository.list().stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO listByIdContato(Integer idContato) throws RegraDeNegocioException {
        log.info("Chamou o listar contato por id");
        return objectMapper.convertValue(findByIdContato(idContato), ContatoDTO.class);
    }

    public List<ContatoDTO> listContatoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Chamou o listar contato por idPessoa");
        pessoaService.findByIdPessoa(idPessoa);
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO create(Integer idPessoa, ContatoCreateDTO contato) throws RegraDeNegocioException {
        log.info("Chamou criar contato");

        pessoaService.findByIdPessoa(idPessoa);

        Contato contatoEntity = objectMapper.convertValue(contato, Contato.class);
        contatoEntity.setIdPessoa(idPessoa);
        contatoRepository.create(contatoEntity);

        log.info("Contato com id=" + contatoEntity.getIdContato() + " criado!");

        return objectMapper.convertValue(contatoEntity, ContatoDTO.class);
    }

    public ContatoDTO update(Integer idContato,
                          ContatoCreateDTO contatoAtualizar) throws RegraDeNegocioException{
        log.info("Chamou atualizar contato");

        pessoaService.findByIdPessoa(contatoAtualizar.getIdPessoa());

        objectMapper.convertValue(contatoAtualizar, Pessoa.class);

        Contato contatoRecuperado = findByIdContato(idContato);
        contatoRecuperado.setIdPessoa(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());

        log.warn("Contato id=" + contatoRecuperado.getIdContato() + " atualizado!");

        return objectMapper.convertValue(contatoRecuperado, ContatoDTO.class);
    }

    public void delete(Integer idContato) throws RegraDeNegocioException {
        log.info("Chamou deletar contato");

        Contato contatoRecuperado = findByIdContato(idContato);
        contatoRepository.list().remove(contatoRecuperado);

        log.warn("Contato id=" + contatoRecuperado.getIdContato() + " deletado!");
    }

    public Contato findByIdContato(Integer idContato) throws RegraDeNegocioException {
        Contato contatoEncontrado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(idContato))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        return contatoEncontrado;
    }
}
