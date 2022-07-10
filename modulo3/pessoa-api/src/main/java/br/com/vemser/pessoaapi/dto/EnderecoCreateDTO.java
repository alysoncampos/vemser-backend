package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.TipoEndereco;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class EnderecoCreateDTO {

    private Integer idPessoa;

    @NotNull(message = "Informe o tipo de Endereço")
    private TipoEndereco tipo;

    @NotEmpty(message = "Informe o logradouro")
    @Length(max = 250, message = "O logradouro pode ter no máximo {max} caracteres")
    private String logradouro;

    @NotNull(message= "Informe o número")
    @Min(0)
    private Integer numero;

    private String complemento;

    @NotBlank(message = "Informe um CEP válido")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 números")
    private String cep;

    @NotBlank(message = "Informe uma cidade válida")
    @Length(max = 250, message = "A cidade pode ter no máximo {max} caracteres")
    private String cidade;

    @NotNull(message = "Informe um Estado")
    private String estado;

    @NotNull(message = "Informe um País")
    private String pais;

}
