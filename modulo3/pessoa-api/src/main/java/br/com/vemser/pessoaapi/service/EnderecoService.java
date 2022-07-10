package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PessoaService pessoaService;

    public EnderecoService() {

    }

    public List<Endereco> list() {
        return enderecoRepository.list();
    }

    public List<Endereco> listByIdEndereco(Integer idEndereco) {
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .collect(Collectors.toList());
    }

    public List<Endereco> listEnderecoByIdPessoa(Integer idPessoa) {
        return enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdPessoa().equals(idPessoa))
                .collect(Collectors.toList());
    }

    public Endereco create(Integer idPessoa, Endereco endereco) throws RegraDeNegocioException {
        pessoaService.findByIdPessoa(idPessoa);
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.create(endereco);
    }

    public Endereco update(Integer idEndereco,
                           Endereco enderecoAtualizar) throws RegraDeNegocioException {
        pessoaService.findByIdPessoa(enderecoAtualizar.getIdPessoa());
        Endereco enderecoRecuperado = findById(idEndereco);
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

    public void delete(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = findById(idEndereco);
        enderecoRepository.list().remove(enderecoRecuperado);
    }

    public Endereco findById(Integer idEndereco) throws RegraDeNegocioException {
        Endereco enderecoEncontrado = enderecoRepository.list().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        return enderecoEncontrado;
    }
    
}
