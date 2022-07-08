package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaService pessoaService;

    public ContatoService() {

    }

    public List<Contato> list(){
        return contatoRepository.list();
    }

    public List<Contato> listByIdContato(Integer idContato) throws Exception {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdContato().equals(idContato))
                .collect(Collectors.toList());
    }

    public List<Contato> listContatoByIdPessoa(Integer idPessoa) {
        return contatoRepository.list().stream()
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
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
