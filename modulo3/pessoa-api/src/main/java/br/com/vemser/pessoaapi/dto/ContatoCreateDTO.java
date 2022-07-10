package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.TipoContato;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {

    private Integer idPessoa;

    @NotNull(message = "Informe o tipo de contato")
    private TipoContato tipoContato;

    @NotBlank(message = "Informe o número")
    @Size(max=13, message = "O número pode ter no máximo 13 caracteres")
    private String numero;

    @NotBlank
    private String descricao;

}
