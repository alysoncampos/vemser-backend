package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
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
    public ResponseEntity<List<ContatoDTO>> list(){
        return new ResponseEntity<>(contatoService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<ContatoDTO> listByIdContato(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listByIdContato(id), HttpStatus.OK);
    }

    @GetMapping("/byIdPessoa/{idPessoa}") //localhost:8080/contato/byIdPessoa/{idPessoa}
    public ResponseEntity<List<ContatoDTO>> listContatoByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listContatoByIdPessoa(idPessoa), HttpStatus.OK);
    }

    @PostMapping("/{idPessoa}") //localhost:8080/contato
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer idPessoa,
                                          @RequestBody @Valid ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.create(idPessoa, contato), HttpStatus.CREATED);
    }

    @PutMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id,
                                          @RequestBody @Valid ContatoCreateDTO contatoAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.update(id, contatoAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public void delete(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        contatoService.delete(id);
    }
}
