package br.com.treinaweb.twprojetos.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.treinaweb.twprojetos.api.hateoas.ClienteAssembler;
import br.com.treinaweb.twprojetos.api.hateoas.ProjetoAssembler;
import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteApiController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired
    private ProjetoAssembler projetoAssembler;

    @Autowired
    private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<Cliente>> findAll(Pageable pageable) {
        Page<Cliente> clientes = clienteService.findAll(pageable);

        return pagedResourcesAssembler.toModel(clientes, clienteAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> findById(@PathVariable Long id) {
        return clienteAssembler.toModel(clienteService.findById(id));
    }

    @GetMapping("/{id}/projetos")
    public CollectionModel<EntityModel<Projeto>> findProjects(@PathVariable Long id) {
        List<Projeto> projetos = clienteService.findById(id).getProjetos();

        return projetoAssembler.toCollectionModel(projetos);
    }
    
}
