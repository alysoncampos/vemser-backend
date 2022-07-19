package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.PessoasDadosPessoaisDTO;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.PessoasDadosPessoaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas-dados-pessoais")
@Validated
public class PessoaDadosPessoaisController {

    @Autowired
    private PessoasDadosPessoaisService pessoasDadosPessoaisService;

    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas, com todos seus dados pessoais")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas, com seus dados pessoais"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<PessoasDadosPessoaisDTO>> list() {
        return new ResponseEntity<>(pessoasDadosPessoaisService.list(), HttpStatus.OK) ;
    }

    @Operation(summary = "Adicionar pessoa", description = "Adiciona pessoa, com todos seus dados pessoais")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna a pessoa criada, com seus dados pessoais"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<PessoasDadosPessoaisDTO> post(@RequestBody @Valid PessoasDadosPessoaisDTO pessoa) {
        return new ResponseEntity<>(pessoasDadosPessoaisService.post(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar pessoa", description = "Atualiza pessoa e seus dados pessoais, localizando-a pelo CPF")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa atualizada, com todos seus dados"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("{cpf}")
    public ResponseEntity<PessoasDadosPessoaisDTO> put(@PathVariable("cpf") String cpf,
                                                       @RequestBody @Valid PessoasDadosPessoaisDTO pessoaAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoasDadosPessoaisService.put(cpf, pessoaAtualizar), HttpStatus.OK);
    }

    @Operation(summary = "Deletar pessoa", description = "Deleta pessoa e seus dados pessoais, localizando-a pelo CPF")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta uma pessoa e retorna apenas o Status HTTP"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable("cpf") String cpf) throws RegraDeNegocioException {
        pessoasDadosPessoaisService.delete(cpf);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
