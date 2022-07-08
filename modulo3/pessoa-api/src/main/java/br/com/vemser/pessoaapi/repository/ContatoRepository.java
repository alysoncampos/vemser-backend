package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.TipoContato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository(){
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, TipoContato.RESIDENCIAL, "8196235241", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, TipoContato.COMERCIAL, "81962123456", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, TipoContato.RESIDENCIAL, "8258296523", "whatsapp" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 3, TipoContato.RESIDENCIAL, "8147578456", "telegram" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 4, TipoContato.COMERCIAL, "8196235241", "telegram" ));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 5, TipoContato.RESIDENCIAL, "8196235241", "telegram" ));
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
