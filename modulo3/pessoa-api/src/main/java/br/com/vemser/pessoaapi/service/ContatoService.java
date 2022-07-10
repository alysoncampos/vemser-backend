package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.entity.Contato;
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
        return objectMapper.convertValue(findById(idContato), ContatoDTO.class);
    }

    public List<ContatoDTO> listContatoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Chamou o listar contato por id de pessoa");
        pessoaService.findById(idPessoa);
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public Contato create(Integer idPessoa, Contato contato) throws RegraDeNegocioException {
        pessoaService.findById(idPessoa);
        contato.setIdPessoa(idPessoa);
        return contatoRepository.create(contato);
    }

    public Contato update(Integer idContato,
                          Contato contatoAtualizar) throws RegraDeNegocioException{
        pessoaService.findById(contatoAtualizar.getIdPessoa());
        Contato contatoRecuperado = findById(idContato);
        contatoRecuperado.setIdPessoa(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        return contatoRecuperado;
    }

    public void delete(Integer idContato) throws Exception {
        Contato contatoRecuperado = findById(idContato);
        contatoRepository.list().remove(contatoRecuperado);
    }

    public Contato findById(Integer idContato) throws RegraDeNegocioException {
        Contato contatoEncontrado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(idContato))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        return contatoEncontrado;
    }
}
