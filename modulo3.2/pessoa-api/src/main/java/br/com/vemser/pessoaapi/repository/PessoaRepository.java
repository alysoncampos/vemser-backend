package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.dto.RelatorioPersonalizadoDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.enums.TipoContato;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

    List<PessoaEntity> findAllByNomeContainsIgnoreCase(String nome);

    PessoaEntity findByCpf(String cpf);

    List<PessoaEntity> findAllByDataNascimentoBetween(LocalDate dtInicial, LocalDate dtFinal);


    // jpql
    @Query(" select p " +
            "  from PESSOA p" +
            " where p.cpf = ?1")
    List<PessoaEntity> listPessoasByCpf(String cpf);


    @Query(value = " select p.* " +
            "         from PESSOA p " +
            "        where p.cpf = :cpf ", nativeQuery = true)
    List<PessoaEntity> listPessoasByCpfNative(@Param("cpf") String cpf);


    @Query(" select p " +
            "  from PESSOA p " +
            "  join p.contatos c " +
            " where c.tipoContato = :tipoContato")
    List<PessoaEntity> listPessoasByTipoContato(@Param("tipoContato")TipoContato tipoContato);


    @Query(value = " select new br.com.vemser.pessoaapi.dto.RelatorioPersonalizadoDTO( " +
            "        p.idPessoa, " +
            "        p.nome, " +
            "        p.email, " +
            "        c.numero, " +
            "        e.cep, " +
            "        e.cidade, " +
            "        e.estado, " +
            "        e.pais, " +
            "        pt.nome " +
            ")" +
            "        from PESSOA p " +
            "        left join p.contatos c " +
            "        left join p.enderecos e " +
            "        left join p.pet pt " +
            "        where (:idPessoa is null OR p.idPessoa = :idPessoa )")
    List<RelatorioPersonalizadoDTO> listRelatorioDTO(@Param("idPessoa") Integer idPessoa);

}
