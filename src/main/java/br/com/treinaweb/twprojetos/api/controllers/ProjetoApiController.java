package br.com.treinaweb.twprojetos.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.dtos.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dtos.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.hateoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ProjetoService;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoApiController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private PagedResourcesAssembler<Projeto> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Projeto>> findAll(Pageable pageable) {
        Page<Projeto> projetos = projetoService.findAll(pageable);

        return pagedResourcesAssembler.toModel(projetos, projetoAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Projeto> findById(@PathVariable Long id) {
        Projeto projeto = projetoService.findById(id);

        return projetoAssembler.toModel(projeto);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Projeto> saveNew(@RequestBody @Valid ProjetoDTO projetoDTO) {
        return projetoAssembler.toModel(projetoService.save(projetoDTO));
    }

    @PutMapping("/{id}")
    public EntityModel<Projeto> update(@RequestBody @Valid ProjetoDTO projetoDTO, @PathVariable Long id) {
        return projetoAssembler.toModel(projetoService.update(projetoDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        projetoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> findEquipeByProjeto(@PathVariable Long id) {
        return funcionarioAssembler.toCollectionModel(projetoService.findById(id).getEquipe());
    }
    
    @PatchMapping("/{id}/equipe")
    public CollectionModel<EntityModel<Funcionario>> updateEquipe(
        @PathVariable Long id, @RequestBody @Valid EquipeDTO equipeDTO) {
            return funcionarioAssembler.toCollectionModel(projetoService.updateEquipe(equipeDTO, id));
    }
}
