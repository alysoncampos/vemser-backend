package br.com.vemser.pessoaapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaEnderecoDTO extends PessoaDTO {

    private List<EnderecoDTO> enderecos;
}
