package br.com.treinaweb.twprojetos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.api.dtos.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dtos.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.mappers.ProjetoMapper;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.exceptions.ProjetoNaoEncontradoException;
import br.com.treinaweb.twprojetos.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ProjetoMapper projetoMapper;

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Page<Projeto> findAll(Pageable pageable) {
        return projetoRepository.findAll(pageable);
    }

    public Projeto findById(Long id) {
        return projetoRepository.findById(id)
            .orElseThrow(() -> new ProjetoNaoEncontradoException(id));
    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto save(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoMapper.convertDTOToEntity(projetoDTO);

        return projetoRepository.save(projeto);
    }

    public Projeto update(Projeto projeto, Long id) {
        findById(id);

        return projetoRepository.save(projeto);
    }

    public Projeto update(ProjetoDTO projetoDTO, Long id) {
        findById(id);

        Projeto projeto = projetoMapper.convertDTOToEntity(projetoDTO);
        projeto.setId(id);

        return projetoRepository.save(projeto);
    }

    public void deleteById(Long id) {
        Projeto projetoEncontrado = findById(id);

        projetoRepository.delete(projetoEncontrado);
    }

    public List<Funcionario> updateEquipe(EquipeDTO equipeDTO, Long id) {
        Projeto projeto = findById(id);

        List<Funcionario> equipe = new ArrayList<>();

        equipeDTO.getEquipe().forEach(funcionarioId -> {
            Funcionario funcionario = funcionarioService.findById(funcionarioId);

            equipe.add(funcionario);
        });

        projeto.setEquipe(equipe);
        projetoRepository.save(projeto);

        return equipe;
    }

}
