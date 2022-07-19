package br.com.vemser.pessoaapi.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contato {

    private Integer idContato;

    private Integer idPessoa;

    private TipoContato tipoContato;

    private String numero;

    private String descricao;

}
