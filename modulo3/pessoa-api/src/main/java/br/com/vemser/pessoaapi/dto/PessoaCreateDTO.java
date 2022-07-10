package br.com.vemser.pessoaapi.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @NotBlank(message="O atributo Nome é obrigatório")
    private String nome;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @CPF
    @NotBlank
    private String cpf;

}
