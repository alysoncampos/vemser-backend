package br.com.vemser.pessoaapi.service;

import br.com.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.vemser.pessoaapi.dto.ContatoDTO;
import br.com.vemser.pessoaapi.dto.PessoaDTO;
import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ObjectMapper objectMapper;

    public ContatoService() {

    }

    public List<ContatoDTO> list(){
        log.info("Listando todos os contatos");

        return contatoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContatoDTO listByIdContato(Integer idContato) throws RegraDeNegocioException {
        log.info("Listaando contato por id");

        return convertToDTO(findByIdContato(idContato));
    }

    public List<ContatoDTO> listContatoByIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        log.info("Listando contato por idPessoa");

        pessoaService.findByIdPessoa(idPessoa);

        return contatoRepository.findAll().stream()
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContatoDTO create(Integer idPessoa,
                             ContatoCreateDTO contato) throws RegraDeNegocioException {
        log.info("Criando contato");

        pessoaService.findByIdPessoa(idPessoa);

        ContatoEntity contatoEntity = convertToEntity(contato);
        contatoEntity.setIdPessoa(idPessoa);

        contatoRepository.save(contatoEntity);

        log.warn("Contato com criado!");

        return convertToDTO(contatoEntity);
    }

    public ContatoDTO update(Integer idContato,
                             ContatoCreateDTO contatoAtualizar) throws RegraDeNegocioException{
        log.info("Atualizando contato");

        pessoaService.findByIdPessoa(contatoAtualizar.getIdPessoa());

        ContatoEntity contatoEntityRecuperado = findByIdContato(idContato);

        contatoEntityRecuperado.setTipoContato(contatoAtualizar.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoEntityRecuperado.setDescricao(contatoAtualizar.getDescricao());

        contatoRepository.save(contatoEntityRecuperado);

        log.warn("Contato atualizado!");

        return convertToDTO(contatoEntityRecuperado);
    }

    public void delete(Integer idContato) throws RegraDeNegocioException {
        log.info("Deletar contato");

        ContatoEntity contatoEntityRecuperado = findByIdContato(idContato);

        contatoRepository.delete(contatoEntityRecuperado);

        log.warn("Contato deletado!");
    }

    public ContatoEntity findByIdContato(Integer idContato) throws RegraDeNegocioException {
        return contatoRepository.findById(idContato)
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));

    }

    public ContatoEntity convertToEntity(ContatoCreateDTO contatoCreateDTO) {
        return objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
    }

    public ContatoDTO convertToDTO(ContatoEntity contatoEntity) {
        return objectMapper.convertValue(contatoEntity, ContatoDTO.class);
    }
}
