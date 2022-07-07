package br.com.treinaweb.twprojetos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.api.dtos.CargoDTO;
import br.com.treinaweb.twprojetos.api.mappers.CargoMapper;
import br.com.treinaweb.twprojetos.entities.Cargo;
import br.com.treinaweb.twprojetos.exceptions.CargoNaoEncontradoException;
import br.com.treinaweb.twprojetos.exceptions.CargoPossuiFuncionariosException;
import br.com.treinaweb.twprojetos.repositories.CargoRepository;
import br.com.treinaweb.twprojetos.repositories.FuncionarioRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoMapper cargoMapper;

    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    public Page<Cargo> findAll(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }

    public Cargo findById(Long id) {
        Cargo cargoEncontrado = cargoRepository.findById(id)
            .orElseThrow(() -> new CargoNaoEncontradoException(id));

        return cargoEncontrado;
    }

    public Cargo save(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Cargo save(CargoDTO cargoDTO) {
        Cargo cargo = cargoMapper.convertDTOToEntity(cargoDTO);

        return cargoRepository.save(cargo);
    }

    public Cargo update(Cargo cargo, Long id) {
        findById(id);

        return cargoRepository.save(cargo);
    }

    public Cargo update(CargoDTO cargoDTO, Long id) {
        findById(id);

        Cargo cargo = cargoMapper.convertDTOToEntity(cargoDTO);
        cargo.setId(id);

        return cargoRepository.save(cargo);
    }

    public void deleteById(Long id) {
        Cargo cargoEncontrado = findById(id);

        if (funcionarioRepository.findByCargo(cargoEncontrado).isEmpty()) {
            cargoRepository.delete(cargoEncontrado);
        } else {
            throw new CargoPossuiFuncionariosException(id);
        }

    }

}
