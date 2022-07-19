package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {

    @Schema(description = "Id da Pessoa")
    private Integer idPessoa;

    @Schema(description = "Tipo do Contato")
    @NotNull(message = "Informe o tipo de contato")
    private TipoContato tipoContato;

    @Schema(description = "Número de Contato")
    @NotBlank(message = "Informe o número")
    @Size(max=13, message = "O número pode ter no máximo 13 caracteres")
    private String numero;

    @Schema(description = "Descrição do contato")
    @NotBlank
    private String descricao;

}
