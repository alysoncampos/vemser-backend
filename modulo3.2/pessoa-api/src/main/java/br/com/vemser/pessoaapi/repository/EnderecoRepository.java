package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {


    @Query(" select ep " +
            "  from ENDERECO_PESSOA ep " +
            " where ep.pais = :pais ")
    List<EnderecoEntity> listEnderecosByPais(@Param("pais") String pais);

}
