package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.Pessoa;
import br.com.vemser.pessoaapi.config.PropertieReader;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PropertieReader propertieReader;

    public PessoaController() {

    }

    @GetMapping("/ambiente")
    public String getAmbiente() {
        return propertieReader.getAmbiente();
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> list() {
        return new ResponseEntity<>(pessoaService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.listByIdPessoa(id), HttpStatus.OK);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<PessoaDTO>> listByName(@PathVariable("nome") String nome) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.listByName(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaCreateDTO pessoa) {
        return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.CREATED);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @RequestBody @Valid PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.update(id, pessoaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idPessoa}")
    public void delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        pessoaService.delete(id);
    }
}
