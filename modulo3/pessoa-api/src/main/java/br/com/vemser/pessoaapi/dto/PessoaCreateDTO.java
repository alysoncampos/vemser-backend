package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @Schema(description = "Nome da Pessoa")
    @NotBlank(message="O atributo Nome é obrigatório")
    private String nome;

    @Schema(description = "Data de Nascimento")
    @NotNull
    @Past
    private LocalDate dataNascimento;

    @Schema(description = "Número do CPF")
    @CPF
    @NotBlank
    private String cpf;

    @Schema(description = "Email da Pessoa")
    @NotBlank
    private String email;

}
