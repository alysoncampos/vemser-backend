package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Contato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository(){
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, "RESIDENCIAL", "8196235241", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, "COMERCIAL", "81962123456", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, "RESIDENCIAL", "8258296523", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 3, "COMERCIAL", "8147578456", "telegram" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 4, "RESIDENCIAL", "8196235241", "telegram" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 5, "COMERCIAL", "8196235241", "telegram" ));
    }

    public List<Contato> list() {
        return listaContatos;
    }

    public Contato create(Contato contato){
        contato.setIdContato(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }
}
