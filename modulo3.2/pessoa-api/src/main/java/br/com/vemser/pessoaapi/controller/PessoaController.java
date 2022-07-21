package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.dto.*;
import br.com.vemser.pessoaapi.config.PropertieReader;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.service.EmailService;
import br.com.vemser.pessoaapi.service.PessoaService;
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
@RequestMapping("/pessoa")
@Validated
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PropertieReader propertieReader;

    @Autowired
    private EmailService emailService;

    public PessoaController() {

    }

    @GetMapping("/ambiente")
    public String getAmbiente() {
        return propertieReader.getAmbiente();
    }

    @GetMapping("/email")
    public String email() {
        emailService.sendSimpleMessage();
        return "Enviando um email...";
    }

    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> list() {
        return new ResponseEntity<>(pessoaService.list(), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas por id", description = "Lista pessoa do banco por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna pessoa por id"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.listByIdPessoa(id), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas pelo nome", description = "Lista pessoa do banco pelo nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna pessoa pelo nome"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-name/{nome}")
    public ResponseEntity<List<PessoaDTO>> findAllByNome(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(pessoaService.findAllByName(nome), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas com enderecos", description = "Lista pessoas do banco com seus endereços")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna Lista de pessoas com seus endereços"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-com-enderecos")
    public ResponseEntity<List<PessoaEnderecoDTO>> listPessoaEnderecos(@RequestParam(required = false) Integer idPessoa) {
        return new ResponseEntity<>(pessoaService.listPessoaEnderecos(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas com contatos", description = "Lista pessoas do banco com seus contatos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna Lista de pessoas com seus contatos"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-com-contatos")
    public ResponseEntity<List<PessoaContatoDTO>> listPessoaContatos(@RequestParam(required = false) Integer idPessoa) {
        return new ResponseEntity<>(pessoaService.listPessoaContatos(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas com pets", description = "Lista pessoas do banco com seus pets")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna Lista de pessoas com seus pets"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-com-pets")
    public ResponseEntity<List<PessoaPetDTO>> listPessoaPet(@RequestParam(required = false) Integer idPessoa) {
        return new ResponseEntity<>(pessoaService.listPessoaPet(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas completas", description = "Lista pessoas do banco com todos os dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna Lista de pessoas com todos os dados"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/pessoa-completo")
    public ResponseEntity<List<PessoaCompletoDTO>> listPessoaCompleto(@RequestParam(required = false) Integer idPessoa) {
        return new ResponseEntity<>(pessoaService.listPessoaCompleto(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar pessoas pelo cpf", description = "Lista pessoa do banco pelo cpf")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna pessoa pelo nome"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-cpf/{cpf}")
    public ResponseEntity<PessoaDTO> findByCpf(@PathVariable("cpf") String cpf) {
        return new ResponseEntity<>(pessoaService.findByCpf(cpf), HttpStatus.OK);
    }

    @Operation(summary = "Listar relatório personalizado", description = "Lista relatório personalizado do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Retorna Relatório personalziado"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @GetMapping("/relatorio-personalizado")
    public List<RelatorioPersonalizadoDTO> getRelatorioPersonalizado(@RequestParam(required = false) Integer idPessoa) {
        return pessoaService.listRelatorioDTO(idPessoa);
    }

    @Operation(summary = "Adicionar pessoa", description = "Adiciona pessoa ao banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Sucesso! Cria uma pessoa"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaCreateDTO pessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar pessoa", description = "Atualiza pessoa do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Atualiza uma pessoa"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                         @RequestBody @Valid PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.update(id, pessoaAtualizar), HttpStatus.OK);
    }

    @Operation(summary = "Deletar pessoa", description = "Deleta pessoa do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso! Deleta uma pessoa"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro! Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Void> delete(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
