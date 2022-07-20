package br.com.vemser.pessoaapi.entity;

import br.com.vemser.pessoaapi.enums.TipoPet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PET")
public class PetEntity implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_SEQ")
    @SequenceGenerator(name = "PET_SEQ", sequenceName = "seq_pet", allocationSize = 1)
    @Column(name = "id_pet")
    private Integer idPet;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private TipoPet tipo;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaEntity pessoa;

}
