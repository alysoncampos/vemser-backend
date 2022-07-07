package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoService() {
        //contatoRepository = new ContatoRepository();
    }

    public List<Contato> list(){
        return contatoRepository.list();
    }

    public Contato listContatoById(Integer id) throws Exception {
        Contato contatoEncontrado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));
        return contatoEncontrado;
    }

    public List<Contato> listByIdPessoa(Integer id) {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Contato create(Integer idPessoa, Contato contato) throws Exception {
        Pessoa pessoa = pessoaRepository.list().stream()
                .filter(p -> p.getIdPessoa().equals(idPessoa))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));
        contato.setIdPessoa(pessoa.getIdPessoa());
        return contatoRepository.create(contato);
    }

    public Contato update(Integer id,
                          Contato contatoAtualizar) throws Exception {
        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));
        contatoRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        return contatoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));
        contatoRepository.list().remove(contatoRecuperado);
    }
}
