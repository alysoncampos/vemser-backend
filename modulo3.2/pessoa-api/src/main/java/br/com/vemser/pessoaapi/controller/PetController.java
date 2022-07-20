package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.vemser.pessoaapi.dto.PetDTO;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.PetService;
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
@RequestMapping("/pet")
@Validated
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Listar pets", description = "Lista todos os pets do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna a lista de pets"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<PetDTO>> list() {
        return new ResponseEntity<>(petService.list(), HttpStatus.OK);
    }

    @Operation(summary = "Adicionar pet", description = "Adiciona o pet no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! O pet foi adicionado ao banco de dados"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<PetDTO> create(@PathVariable("idPessoa")Integer idPessoa,
                                         @RequestBody @Valid PetCreateDTO pet) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.create(idPessoa, pet), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar pet", description = "Atualiza o pet no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! O pet foi atualizado no banco de dados"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPet}")
    public ResponseEntity<PetDTO> update(@PathVariable("idPet") Integer idPet,
                                         @RequestBody @Valid PetDTO pet) throws RegraDeNegocioException {
        return new ResponseEntity<>(petService.update(idPet, pet), HttpStatus.OK);
    }

    @Operation(summary = "Deletar pet", description = "Deleta o pet do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! O pet foi deletado do banco de dados"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPet}")
    public ResponseEntity<Void> delete(@PathVariable("idPet") Integer idPet) throws RegraDeNegocioException {
        petService.delete(idPet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
