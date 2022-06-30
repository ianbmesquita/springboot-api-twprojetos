package br.com.treinaweb.twprojetos.api.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.dtos.CargoDTO;
import br.com.treinaweb.twprojetos.entities.Cargo;

@Component
public class CargoMapper {
    public Cargo convertDTOToEntity(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setNome(cargoDTO.getNome());

        return cargo;
    }
}
