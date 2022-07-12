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

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

}
