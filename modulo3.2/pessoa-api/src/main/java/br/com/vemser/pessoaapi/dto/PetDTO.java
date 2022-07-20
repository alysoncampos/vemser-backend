package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetDTO extends PetCreateDTO {

    @Schema(description = "Id do Pet")
    private Integer idPet;

    @Schema(description = "Dono do Pet")
    private PessoaDTO pessoa;
}
