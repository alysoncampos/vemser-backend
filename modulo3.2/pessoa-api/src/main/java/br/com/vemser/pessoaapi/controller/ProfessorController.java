package br.com.vemser.pessoaapi.controller;

import br.com.vemser.pessoaapi.entity.ProfessorEntity;
import br.com.vemser.pessoaapi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<ProfessorEntity> list(){
        return professorRepository.findAll();
    }

    @PostMapping()
    public ProfessorEntity create(@RequestBody ProfessorEntity professor){
        return professorRepository.save(professor);
    }

}
