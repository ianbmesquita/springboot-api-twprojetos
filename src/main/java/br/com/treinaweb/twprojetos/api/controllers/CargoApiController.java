package br.com.treinaweb.twprojetos.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.services.CargoService;

@RestController
@RequestMapping("/api/v1/cargos")
public class CargoApiController {
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> findAll() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    public Cargo findById(@PathVariable Long id) {
        return cargoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cargo save(@RequestBody @Valid CargoDTO cargoDTO) {
        return cargoService.save(cargoDTO);
    }

    @PutMapping("/{id}")
    public Cargo update(@RequestBody @Valid CargoDTO cargoDTO, @PathVariable Long id) {
        return cargoService.update(cargoDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cargoService.deleteById(id);
    }
    
}
