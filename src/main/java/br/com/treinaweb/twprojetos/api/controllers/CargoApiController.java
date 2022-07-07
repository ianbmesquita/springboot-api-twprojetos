package br.com.treinaweb.twprojetos.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.dtos.CargoDTO;
import br.com.treinaweb.twprojetos.api.hateoas.CargoAssembler;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.services.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoApiController {
    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoAssembler cargoAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Cargo>> findAll() {
        return cargoAssembler.toCollectionModel(cargoService.findAll());
    }

    @GetMapping("/{id}")
    public EntityModel<Cargo> findById(@PathVariable Long id) {
        return cargoAssembler.toModel(cargoService.findById(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<Cargo> save(@RequestBody @Valid CargoDTO cargoDTO) {
        return cargoAssembler.toModel(cargoService.save(cargoDTO));
    }

    @PutMapping("/{id}")
    public EntityModel<Cargo> update(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        return cargoAssembler.toModel(cargoService.update(cargoDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cargoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
