package br.com.vemser.pessoaapi.entity;

import lombok.*;

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
