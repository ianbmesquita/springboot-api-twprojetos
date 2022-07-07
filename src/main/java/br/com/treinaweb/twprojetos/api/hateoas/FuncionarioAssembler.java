package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controllers.CargoApiController;
import br.com.treinaweb.twprojetos.api.controllers.FuncionarioApiController;
import br.com.treinaweb.twprojetos.entities.Funcionario;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario> {

    @Override
    public void addLinks(EntityModel<Funcionario> resource) {
        Long ifFuncionario = resource.getContent().getId();
        Long cargoId = resource.getContent().getCargo().getId();

        Link  selfLink = linkTo(methodOn(FuncionarioApiController.class).findById(ifFuncionario))
            .withSelfRel()
            .withType("GET")
            .withTitle("Exibe os detalhes do funcionário.");

        Link  cargoLink = linkTo(methodOn(CargoApiController.class).findById(cargoId))
            .withRel("cargo")
            .withType("GET")
            .withTitle("Exibe os detalhes do cargo do funcionário.");
        
        resource.add(selfLink, cargoLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
        Link selfLink = linkTo(methodOn(FuncionarioApiController.class).findAll(null))
            .withSelfRel()
            .withType("GET")
            .withTitle("Busca todos os funcionários cadastrados.");
        
        resources.add(selfLink);
        
    }
    
}
