package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    public EnderecoService() {

    }

    public List<Endereco> list() {
        return enderecoRepository.list();
    }

    public Endereco listByIdEndereco(Integer id) throws Exception {
        Endereco enderecoEncontrado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não encontrado"));
        return enderecoEncontrado;
    }

    public List<Endereco> listEnderecoByIdPessoa(Integer id) {
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Endereco create(Integer idPessoa, Endereco endereco) throws Exception {
        Pessoa pessoaEncontrada = pessoaRepository.list().stream()
                .filter(p -> p.getIdPessoa().equals(idPessoa))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));
        endereco.setIdPessoa(pessoaEncontrada.getIdPessoa());
        return enderecoRepository.create(endereco);
    }

    public Endereco update(Integer id,
                           Endereco enderecoAtualizar) throws Exception {
        Endereco enderecoRecuperado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereço não encontrado"));
        enderecoRecuperado.setIdPessoa(enderecoAtualizar.getIdPessoa());
        enderecoRecuperado.setTipo(enderecoAtualizar.getTipo());
        enderecoRecuperado.setLogradouro(enderecoAtualizar.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAtualizar.getNumero());
        enderecoRecuperado.setComplemento(enderecoAtualizar.getComplemento());
        enderecoRecuperado.setCep(enderecoAtualizar.getCep());
        enderecoRecuperado.setCidade(enderecoAtualizar.getCidade());
        enderecoRecuperado.setEstado(enderecoAtualizar.getEstado());
        enderecoRecuperado.setPais(enderecoAtualizar.getPais());
        return enderecoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Endereco enderecoRecuperado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Endereco não enocntrado"));
        enderecoRepository.list().remove(enderecoRecuperado);
    }
}
