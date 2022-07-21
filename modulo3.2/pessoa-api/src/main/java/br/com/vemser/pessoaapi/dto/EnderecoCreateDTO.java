package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class EnderecoCreateDTO {

    @NotNull
    private Integer idPessoa;

    @Schema(description = "Tipo do Endereço")
    @NotNull(message = "Informe o tipo de Endereço")
    private TipoEndereco tipo;

    @Schema(description = "Logradouro")
    @NotEmpty(message = "Informe o logradouro")
    @Length(max = 250, message = "O logradouro pode ter no máximo {max} caracteres")
    private String logradouro;

    @Schema(description = "Nùmero")
    @NotNull(message= "Informe o número")
    @Min(0)
    private Integer numero;

    @Schema(description = "Complemento")
    private String complemento;

    @Schema(description = "CEP")
    @NotBlank(message = "Informe um CEP válido")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 números")
    private String cep;

    @Schema(description = "Nome da Cidade")
    @NotBlank(message = "Informe uma cidade válida")
    @Length(max = 250, message = "A cidade pode ter no máximo {max} caracteres")
    private String cidade;

    @Schema(description = "Nome do Estado")
    @NotNull(message = "Informe um Estado")
    private String estado;

    @Schema(description = "Nome do País")
    @NotNull(message = "Informe um País")
    private String pais;

}
