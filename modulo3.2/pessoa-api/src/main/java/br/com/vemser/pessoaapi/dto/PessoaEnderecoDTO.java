package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PessoaEnderecoDTO extends PessoaDTO {

    @Schema(description = "Endereços da Pessoa")
    private List<EnderecoDTO> enderecos;
}
