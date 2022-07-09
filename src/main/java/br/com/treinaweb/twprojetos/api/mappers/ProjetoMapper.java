package br.com.treinaweb.twprojetos.api.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.dtos.ProjetoDTO;
import br.com.treinaweb.twprojetos.entities.Projeto;
import br.com.treinaweb.twprojetos.services.ClienteService;
import br.com.treinaweb.twprojetos.services.FuncionarioService;

@Component
public class ProjetoMapper {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    public Projeto convertDTOToEntity(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setGastos(projetoDTO.getGastos());
        
        projeto.setLider(funcionarioService.findById(projetoDTO.getLiderId()));
        projeto.setCliente(clienteService.findById(projetoDTO.getClienteId()));

        return projeto;
    }
}
