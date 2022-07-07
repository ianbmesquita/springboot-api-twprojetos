package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controllers.CargoApiController;
import br.com.treinaweb.twprojetos.entities.Cargo;

@Component
public class CargoAssembler implements SimpleRepresentationModelAssembler<Cargo> {

    @Override
    public void addLinks(EntityModel<Cargo> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(CargoApiController.class).findById(id))
            .withSelfRel()
            .withType("GET")
            .withTitle("Exibe os detalhes do cargo.");
        
        Link updateLink = linkTo(methodOn(CargoApiController.class).update(null, id))
            .withSelfRel()
            .withType("PUT")
            .withTitle("Atualiza os detalhes do cargo.");

        Link deleteLink = linkTo(methodOn(CargoApiController.class).delete(id))
            .withSelfRel()
            .withType("DELETE")
            .withTitle("Exclui o cargo.");
        
        resource.add(selfLink, updateLink, deleteLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cargo>> resources) {
        Link selfLink = linkTo(methodOn(CargoApiController.class).findAll(null))
            .withSelfRel()
            .withType("GET")
            .withTitle("Busca todos os cargos cadastrados.");

        Link saveLink = linkTo(methodOn(CargoApiController.class).save(null))
            .withSelfRel()
            .withType("POST")
            .withTitle("Cadastra um novo cargo.");
        
        resources.add(selfLink, saveLink);
    }
    
}
