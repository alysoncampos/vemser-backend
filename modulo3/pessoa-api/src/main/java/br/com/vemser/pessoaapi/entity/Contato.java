package br.com.vemser.pessoaapi.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contato {

    private Integer idContato;

    private Integer idPessoa;

    @NotNull(message = "Informe o tipo de contato")
    private TipoContato tipoContato;

    @NotBlank(message = "Informe o número")
    @Size(max=13, message = "O número pode ter no máximo 13 caracteres")
    private String numero;

    @NotBlank
    private String descricao;

}
