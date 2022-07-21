package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class PessoaCompletoDTO extends PessoaDTO {

    @Schema(description = "Endereços da Pessoa")
    private List<EnderecoDTO> enderecos;

    @Schema(description = "Contatos da Pessoa")
    private List<ContatoDTO> contatos;

    @Schema(description = "Pet da Pessoa")
    private PetDTO pet;

}
