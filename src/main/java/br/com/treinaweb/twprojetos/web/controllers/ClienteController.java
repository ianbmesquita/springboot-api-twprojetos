package br.com.treinaweb.twprojetos.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entities.Cliente;
import br.com.treinaweb.twprojetos.exceptions.ClientePossuiProjetosException;
import br.com.treinaweb.twprojetos.services.ClienteService;
import br.com.treinaweb.twprojetos.validators.ClienteValidator;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteValidator clienteValidator;

    @InitBinder("cliente")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(clienteValidator);
    }

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cliente/home");

        modelAndView.addObject("clientes", clienteService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/detalhes");

        modelAndView.addObject("cliente", clienteService.findById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cliente/formulario");

        modelAndView.addObject("cliente", new Cliente());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/formulario");

        modelAndView.addObject("cliente", clienteService.findById(id));

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(@Valid Cliente cliente, BindingResult resultado, RedirectAttributes attr, @PathVariable(required = false) Long id) {
        if (resultado.hasErrors()) {
            return "cliente/formulario";
        }

        if (cliente.getId() == null) {
            clienteService.save(cliente);
            attr.addFlashAttribute("alert", new AlertDTO("Cliente cadastrado com sucesso!", "alert-success"));
        } else {
            clienteService.update(cliente, id);
            attr.addFlashAttribute("alert", new AlertDTO("Cliente editado com sucesso!", "alert-success"));
        }

        return "redirect:/clientes";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        try {
            clienteService.deleteById(id);
            attrs.addFlashAttribute("alert", new AlertDTO("Cliente excluido com sucesso!", "alert-success"));
        } catch (ClientePossuiProjetosException e) {
            attrs.addFlashAttribute("alert", new AlertDTO("Cliente não pode ser excluido, pois possui projeto(s) relacionado(s)!", "alert-danger"));
        }

        return "redirect:/clientes";
    }

}
