package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.enums.TipoPet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PetCreateDTO {

    @Schema(description = "Nome do Pet")
    @NotBlank
    private String nome;

    @Schema(description = "Tipo do Pet")
    @NotNull
    private TipoPet tipo;

}
