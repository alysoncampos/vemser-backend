package br.com.vemser.pessoaapi.dto;

import lombok.Data;

@Data
public class PessoaPetDTO extends PessoaDTO {

    private PetDTO pet;
}
