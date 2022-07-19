package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EnderecoRepository {

    private static List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER_END = new AtomicInteger();
    private AtomicInteger COUNTER_PES = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecos.add(new Endereco(COUNTER_END.incrementAndGet(), COUNTER_PES.incrementAndGet(), TipoEndereco.RESIDENCIAL,
                "Rua A", 12, "Casa", "552000001", "Recife", "Pernambuco", "Brasil" ));
        listaEnderecos.add(new Endereco(COUNTER_END.incrementAndGet(), COUNTER_PES.incrementAndGet(), TipoEndereco.COMERCIAL,
                "Rua B", 13, "Sala 113", "552000013", "Recife", "Pernambuco", "Brasil" ));
        listaEnderecos.add(new Endereco(COUNTER_END.incrementAndGet(), COUNTER_PES.incrementAndGet(), TipoEndereco.RESIDENCIAL,
                "Rua C", 14, "Casa", "552000014", "Recife", "Pernambuco", "Brasil" ));
        listaEnderecos.add(new Endereco(COUNTER_END.incrementAndGet(), COUNTER_PES.incrementAndGet(), TipoEndereco.COMERCIAL,
                "Rua D", 15, "Sala 115", "552000015", "Recife", "Pernambuco", "Brasil" ));
        listaEnderecos.add(new Endereco(COUNTER_END.incrementAndGet(), COUNTER_PES.incrementAndGet(), TipoEndereco.RESIDENCIAL,
                "Rua E", 16, "Casa", "552000016", "Recife", "Pernambuco", "Brasil" ));
    }

    public List<Endereco> list() {
        return listaEnderecos;
    }

    public Endereco create(Endereco endereco) {
        endereco.setIdEndereco(COUNTER_END.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }
}
