package br.com.treinaweb.twprojetos.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.hateoas.FuncionarioAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Funcionario;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.FuncionarioService;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioApiController {

    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private FuncionarioAssembler funcionarioAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Funcionario>> findAll(Pageable pageable) {
        Page<Funcionario> funcionarios = funcionarioService.findAll(pageable);

        return pagedResourcesAssembler.toModel(funcionarios, funcionarioAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Funcionario> findById(@PathVariable Long id) {
        return funcionarioAssembler.toModel(funcionarioService.findById(id));
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> findProjects(@PathVariable Long id) {
        List<Projeto> projetos = funcionarioService.findById(id).getProjetos();
        
        return projetoAssembler.toCollectionModel(projetos);
    }
}
