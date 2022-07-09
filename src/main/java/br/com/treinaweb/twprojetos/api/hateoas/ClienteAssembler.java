package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controllers.ClienteApiController;
import br.com.treinaweb.twprojetos.entities.Cliente;

@Component
public class ClienteAssembler implements SimpleRepresentationModelAssembler<Cliente> {

    @Override
    public void addLinks(EntityModel<Cliente> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(ClienteApiController.class).findById(id))
            .withSelfRel()
            .withType("GET")
            .withTitle("Exibe os detalhes do cliente.");
        
        Link projectsLink = linkTo(methodOn(ClienteApiController.class).findProjects(id))
            .withRel("projetos")
            .withType("GET")
            .withTitle("Exibe os projetos do cliente");
        
        resource.add(selfLink, projectsLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cliente>> resources) {
        Link selfLink = linkTo(methodOn(ClienteApiController.class).findAll(null))
            .withSelfRel()
            .withType("GET")
            .withTitle("Busca todos os clientes cadastrados.");
        
        resources.add(selfLink);
    }

    
}
