package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.enums.TipoSexo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoasDadosPessoaisDTO {

    @Schema(description = "Nome da Pessoa")
    private String nome;

    @Schema(description = "Data de Nascimento da Pessoa")
    private LocalDate dataNascimento;

    @Schema(description = "Número do CPF da Pessoa")
    private String cpf;

    @Schema(description = "Email da Pessoa")
    private String email;

    @Schema(description = "Número da CNH da Pessoa")
    private String cnh;

    @Schema(description = "Nome da mãe da Pessoa")
    private String nomeMae;

    @Schema(description = "Nome do pai da Pessoa")
    private String nomePai;

    @Schema(description = "Número do RG da Pessoa")
    private String rg;

    @Schema(description = "Sexo da Pessoa")
    private TipoSexo sexo;

    @Schema(description = "Número do título de eleitor da Pessoa")
    private String tituloEleitor;

}
