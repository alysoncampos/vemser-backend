package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.DadosPessoaisDTO;
import br.com.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.dto.PessoasDadosPessoaisDTO;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoasDadosPessoaisService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DadosPessoaisService dadosPessoaisService;


    public List<PessoasDadosPessoaisDTO> list() {
        List<PessoasDadosPessoaisDTO> pessoasDadosPessoaisDTOS = new ArrayList<>();
        List<PessoaDTO> pessoaDTOS = pessoaService.list();

        for (int i = 0; i < pessoaDTOS.size(); i++) {
            pessoasDadosPessoaisDTOS.add(new PessoasDadosPessoaisDTO());
            pessoasDadosPessoaisDTOS.get(i).setNome(pessoaDTOS.get(i).getNome());
            pessoasDadosPessoaisDTOS.get(i).setCpf(pessoaDTOS.get(i).getCpf());
            pessoasDadosPessoaisDTOS.get(i).setDataNascimento(pessoaDTOS.get(i).getDataNascimento());
            pessoasDadosPessoaisDTOS.get(i).setEmail(pessoaDTOS.get(i).getEmail());
        }

        for (PessoasDadosPessoaisDTO pessoa : pessoasDadosPessoaisDTOS) {
            Optional<DadosPessoaisDTO> dadosPessoaisDTO = dadosPessoaisService.getAll()
                    .stream()
                    .filter(dadosPessoais -> dadosPessoais.getCpf().equals(pessoa.getCpf()))
                    .findAny();
            if(dadosPessoaisDTO.isPresent()){
                pessoa.setCnh(dadosPessoaisDTO.get().getCnh());
                pessoa.setNomeMae(dadosPessoaisDTO.get().getNomeMae());
                pessoa.setNomePai(dadosPessoaisDTO.get().getNomePai());
                pessoa.setRg(dadosPessoaisDTO.get().getRg());
                pessoa.setSexo(dadosPessoaisDTO.get().getSexo());
                pessoa.setTituloEleitor(dadosPessoaisDTO.get().getTituloEleitor());
            }
        }
        return pessoasDadosPessoaisDTOS;
    }

    public PessoasDadosPessoaisDTO post(PessoasDadosPessoaisDTO pessoasDadosPessoaisDTO) {
        dadosPessoaisService.post(convertToDadosPessoaisDTO(pessoasDadosPessoaisDTO));

        pessoaService.create(convertToPessoaCreateDTO(pessoasDadosPessoaisDTO));

        return pessoasDadosPessoaisDTO;
    }

    public PessoasDadosPessoaisDTO put(String cpf, PessoasDadosPessoaisDTO pessoasDadosPessoaisDTO) throws RegraDeNegocioException {
        dadosPessoaisService.put(cpf, convertToDadosPessoaisDTO(pessoasDadosPessoaisDTO));

        PessoaEntity pessoaEntityRecuperada = findByCpf(cpf);

        pessoaService.update(pessoaEntityRecuperada.getIdPessoa(), convertToPessoaCreateDTO(pessoasDadosPessoaisDTO));

        return pessoasDadosPessoaisDTO;
    }

    public void delete(String cpf) throws RegraDeNegocioException {
        dadosPessoaisService.delete(cpf);

        PessoaEntity pessoaEntityRecuperada = findByCpf(cpf);

        pessoaService.delete(pessoaEntityRecuperada.getIdPessoa());
    }

    public PessoaCreateDTO convertToPessoaCreateDTO(PessoasDadosPessoaisDTO pessoa) {
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();

        pessoaCreateDTO.setNome(pessoa.getNome());
        pessoaCreateDTO.setCpf(pessoa.getCpf());
        pessoaCreateDTO.setDataNascimento(pessoa.getDataNascimento());
        pessoaCreateDTO.setEmail(pessoa.getEmail());

        return pessoaCreateDTO;
    }

    public DadosPessoaisDTO convertToDadosPessoaisDTO(PessoasDadosPessoaisDTO pessoa){
        DadosPessoaisDTO dadosPessoaisDTO = new DadosPessoaisDTO();

        dadosPessoaisDTO.setCnh(pessoa.getCnh());
        dadosPessoaisDTO.setCpf(pessoa.getCpf());
        dadosPessoaisDTO.setNome(pessoa.getNome());
        dadosPessoaisDTO.setNomeMae(pessoa.getNomeMae());
        dadosPessoaisDTO.setNomePai(pessoa.getNomePai());
        dadosPessoaisDTO.setRg(pessoa.getRg());
        dadosPessoaisDTO.setSexo(pessoa.getSexo());
        dadosPessoaisDTO.setTituloEleitor(pessoa.getTituloEleitor());

        return dadosPessoaisDTO;
    }

    public PessoaEntity findByCpf(String cpf) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = pessoaRepository.findAll().stream()
                .filter(pessoaEntity -> pessoaEntity.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
        return pessoaEntityRecuperada;
    }
}
