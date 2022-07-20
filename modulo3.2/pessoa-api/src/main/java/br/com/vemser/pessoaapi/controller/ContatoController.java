package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.ContatoService;
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
@RequestMapping("/contato")
@Validated
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    public ContatoController() {

    }

    @Operation(summary = "listar contatos", description = "Lista todos os contatos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de contatos"),
                    @ApiResponse(responseCode = "400", description = "Contato não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping //localhost:8080/contato
    public ResponseEntity<List<ContatoDTO>> list(){
        return new ResponseEntity<>(contatoService.list(), HttpStatus.OK);
    }

    @Operation(summary = "listar contato por id", description = "Lista contato do banco por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna contato por id"),
                    @ApiResponse(responseCode = "400", description = "Contato não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<ContatoDTO> listByIdContato(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listByIdContato(id), HttpStatus.OK);
    }

    @Operation(summary = "listar contato pelo id da pessoa", description = "Lista contato do banco pelo id da pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna contato pelo id da pessoa"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}/pessoa") //localhost:8080/contato/{idPessoa}/pessoa
    public ResponseEntity<List<ContatoDTO>> listContatoByIdPessoa(@PathVariable("idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.listContatoByIdPessoa(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "adicionar contato", description = "Adiciona contato ao banco passando o id da pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Cria um contato"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}") //localhost:8080/contato
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer idPessoa,
                                             @RequestBody @Valid ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.create(idPessoa, contato), HttpStatus.CREATED);
    }

    @Operation(summary = "atualizar contato", description = "Atualiza contato do banco por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza o contato"),
                    @ApiResponse(responseCode = "400", description = "Contato não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer idContato,
                                          @RequestBody @Valid ContatoCreateDTO contatoAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.update(idContato, contatoAtualizar), HttpStatus.OK);
    }

    @Operation(summary = "deletar contato", description = "Deleta contato do banco por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta o contato"),
                    @ApiResponse(responseCode = "400", description = "Contato não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idContato}") //localhost:8080/contato/{idContato}
    public ResponseEntity<Void> delete(@PathVariable("idContato") Integer id) throws RegraDeNegocioException {
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
