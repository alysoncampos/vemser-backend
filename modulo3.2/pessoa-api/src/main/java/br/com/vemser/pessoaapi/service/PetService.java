package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.dto.PetCreateDTO;
import br.com.vemser.pessoaapi.dto.PetDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.PetEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<PetDTO> list() {
        log.info("Listando todos os pets");

        return petRepository.findAll().stream()
                .map(petEntity -> {
                    PetDTO petDTO = convertToDTO(petEntity);
                    petDTO.setPessoa(convertPessoaToDTO(petEntity.getPessoa()));
                    return petDTO;
                })
                .toList();
    }

    public PetDTO create(Integer idPessoa, PetCreateDTO petCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.findByIdPessoa(idPessoa);
        log.info("Adicionando pet");

        PetEntity petEntity = convertToEntity(petCreateDTO);
        petEntity.setPessoa(pessoaEntity);
        petRepository.save(petEntity);

        log.info("Pet adicionado!");

        PetDTO petDTO = convertToDTO(petEntity);
        petDTO.setPessoa(pessoaService.convertToDTO(pessoaEntity));

        return petDTO;
    }

    public PetDTO update(Integer idPet, PetDTO petDTO) throws RegraDeNegocioException {

        PetEntity petEntity = findByIdPet(idPet);

        PessoaEntity pessoaEntity = pessoaService.findByIdPessoa(petDTO.getPessoa().getIdPessoa());

        log.info("Atualizando pet");

        petEntity.setPessoa(pessoaEntity);
        petEntity.setNome(petDTO.getNome());
        petEntity.setTipo(petDTO.getTipo());

        petRepository.save(petEntity);

        log.info("Pet atualizado!");

        petDTO.setPessoa(pessoaService.convertToDTO(pessoaEntity));

        return petDTO;
    }

    public void delete(Integer idPet) throws RegraDeNegocioException {
        PetEntity petEntity = findByIdPet(idPet);
        petRepository.delete(petEntity);
    }

    public PetEntity findByIdPet(Integer idPed) throws RegraDeNegocioException {
        return petRepository.findById(idPed)
                .orElseThrow(() -> new RegraDeNegocioException("Pet não encontrado"));
    }

    public PetEntity convertToEntity(PetCreateDTO petCreateDTO) {
        return objectMapper.convertValue(petCreateDTO, PetEntity.class);
    }

    public PetDTO convertToDTO(PetEntity petEntity) {
        return objectMapper.convertValue(petEntity, PetDTO.class);
    }

    public PessoaDTO convertPessoaToDTO(PessoaEntity pessoaEntity) {
        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }
}
