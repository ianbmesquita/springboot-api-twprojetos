package br.com.treinaweb.twprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import br.com.treinaweb.twprojetos.api.annotations.ApiPageable;
import br.com.treinaweb.twprojetos.api.dtos.CargoDTO;
import br.com.treinaweb.twprojetos.api.exceptions.ApiError;
import br.com.treinaweb.twprojetos.api.exceptions.ApiErrorValidation;
import br.com.treinaweb.twprojetos.entities.Cargo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Cargos", description = "CargoController")
public interface CargoApiControllerDoc {

    @ApiOperation(value = "Listar todos os cargos.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargos listados com sucesso."),
    })
    @ApiPageable
    CollectionModel<EntityModel<Cargo>> findAll(@ApiIgnore Pageable pageable);

    @ApiOperation(value = "Buscar cargo por ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso."),
        @ApiResponse(code = 404, message = "Cargo não encontrado.", response = ApiError.class)
    })
    EntityModel<Cargo> findById(Long id);

    @ApiOperation(value = "Cadastrar um novo cargo.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cargo cadastrado com sucesso."),
        @ApiResponse(code = 400, message = "Houveram erros de validação.", response = ApiErrorValidation.class)
    })
    EntityModel<Cargo> save(CargoDTO cargoDTO);

    @ApiOperation(value = "Atualizar um cargo existente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo atualizado com sucesso."),
        @ApiResponse(code = 400, message = "Houveram erros de validação.", response = ApiErrorValidation.class),
        @ApiResponse(code = 404, message = "Cargo não encontrado.", response = ApiError.class)
    })
    EntityModel<Cargo> update(CargoDTO cargoDTO, Long id);

    @ApiOperation(value = "Excluir um cargo existente.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo excluído com sucesso."),
        @ApiResponse(code = 404, message = "Cargo não encontrado.", response = ApiError.class)
    })
    ResponseEntity<?> delete(Long id);
    
}
