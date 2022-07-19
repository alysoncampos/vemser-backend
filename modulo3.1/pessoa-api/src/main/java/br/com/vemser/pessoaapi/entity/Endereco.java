package br.com.vemser.pessoaapi.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endereco {

    private Integer idEndereco;
    
    private Integer idPessoa;

    private TipoEndereco tipo;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String cep;

    private String cidade;

    private String estado;

    private String pais;

}
