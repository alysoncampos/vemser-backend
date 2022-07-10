package br.com.vemser.pessoaapi.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa {

    private Integer idPessoa;

    @NotBlank(message="O atributo Nome é obrigatório")
    private String nome;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @CPF
    @NotBlank
    private String cpf;

}