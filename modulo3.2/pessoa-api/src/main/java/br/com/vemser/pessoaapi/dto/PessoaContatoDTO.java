package br.com.vemser.pessoaapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaContatoDTO extends PessoaDTO {

    private List<ContatoDTO> contatos;

}
