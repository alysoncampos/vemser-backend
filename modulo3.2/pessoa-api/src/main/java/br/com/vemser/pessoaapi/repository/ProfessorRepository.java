package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.ProfessorEntity;
import br.com.vemser.pessoaapi.entity.pk.ProfessorPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, ProfessorPK> {

}
