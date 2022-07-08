package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@Validated
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    public ContatoController() {

    }

    @GetMapping //localhost:8080/contato
    public List<Contato> list(){
        return contatoService.list();
    }

    @GetMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public List<Contato> listByIdContato(@PathVariable("idContato") Integer id) throws Exception {
        return contatoService.listByIdContato(id);
    }

    @GetMapping("/byIdPessoa/{idPessoa}") //localhost:8080/contato/byIdPessoa/{idPessoa}
    public List<Contato> listContatoByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) {
        return contatoService.listContatoByIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}") //localhost:8080/contato
    public ResponseEntity<Contato> create(@PathVariable("idPessoa") Integer idPessoa,
                                          @RequestBody @Valid Contato contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.create(idPessoa, contato), HttpStatus.OK);
    }

    @PutMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<Contato> update(@PathVariable("idContato") Integer id,
                                          @RequestBody @Valid Contato contatoAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.update(id, contatoAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.delete(id);
    }
}
