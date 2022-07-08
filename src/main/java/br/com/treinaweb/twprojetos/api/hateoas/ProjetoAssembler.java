package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controllers.ClienteApiController;
import br.com.treinaweb.twprojetos.api.controllers.FuncionarioApiController;
import br.com.treinaweb.twprojetos.api.controllers.ProjetoApiController;
import br.com.treinaweb.twprojetos.entities.Projeto;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long projetoId = resource.getContent().getId();
        Long clienteId = resource.getContent().getCliente().getId();
        Long liderId = resource.getContent().getLider().getId();

        Link selfLink = linkTo(methodOn(ProjetoApiController.class).findById(projetoId))
            .withSelfRel()
            .withType("GET")
            .withTitle("Exibe os detalhes do projeto.");

        Link liderLink = linkTo(methodOn(FuncionarioApiController.class).findById(liderId))
            .withRel("lider")
            .withType("GET")
            .withTitle("Exibe os detalhes do líder do projeto.");
        
        Link clienteLink = linkTo(methodOn(ClienteApiController.class).findById(clienteId))
            .withRel("cliente")
            .withType("GET")
            .withTitle("Exibe os detalhes do cliente do projeto.");
        
        Link editarLink = linkTo(methodOn(ProjetoApiController.class).update(null, projetoId))
            .withSelfRel()
            .withType("PUT")
            .withTitle("Editar as informações do projeto.");
        
        Link excluirLink = linkTo(methodOn(ProjetoApiController.class).delete(projetoId))
            .withSelfRel()
            .withType("DELETE")
            .withTitle("Excluir o projeto.");

        Link equipeLink = linkTo(methodOn(ProjetoApiController.class).findEquipeByProjeto(projetoId))
            .withRel("equipe")
            .withType("GET")
            .withTitle("Exibe a equipe do projeto.");

        Link atualizarEquipeLink = linkTo(methodOn(ProjetoApiController.class).updateEquipe(projetoId, null))
            .withRel("equipe")
            .withType("PATCH")
            .withTitle("Atualiza a equipe do projeto.");
        
        resource.add(selfLink, liderLink, clienteLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoApiController.class).findAll(null))
            .withSelfRel()
            .withType("GET")
            .withTitle("Busca todos os funcionários cadastrados.");
        
        resources.add(selfLink);
        
    }
    
}
