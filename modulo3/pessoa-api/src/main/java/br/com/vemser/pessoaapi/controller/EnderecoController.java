package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    public EnderecoController() {

    }

    @GetMapping
    public List<Endereco> list() {
        return enderecoService.list();
    }

    @GetMapping("/{idEndereco}")
    public Endereco listByIdEndereco(@PathVariable("idEndereco") Integer id) throws Exception {
        return enderecoService.listByIdEndereco(id);
    }

    @GetMapping("/{idPessoa}/pessoa")
    public List<Endereco> listEnderecoByIdPessoa(@PathVariable("idPessoa") Integer id) {
        return enderecoService.listEnderecoByIdPessoa(id);
    }

    @PostMapping("/{idPessoa}")
    public Endereco create(@PathVariable("idPessoa") Integer idPessoa,
                           @RequestBody Endereco endereco) throws Exception {
        return enderecoService.create(idPessoa, endereco);
    }

    @PutMapping("/{idEndereco}")
    public Endereco update(@PathVariable("idEndereco") Integer idEndereco,
                           @RequestBody Endereco enderecoAtualizar) throws Exception {
        return enderecoService.update(idEndereco, enderecoAtualizar);
    }

    @DeleteMapping("/{idEndereco}")
    public void delete(@PathVariable("idEndereco") Integer id) throws Exception {
        enderecoService.delete(id);
    }
}
