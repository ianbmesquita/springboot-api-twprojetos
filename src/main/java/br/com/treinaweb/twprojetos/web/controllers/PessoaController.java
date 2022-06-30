package br.com.treinaweb.twprojetos.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.treinaweb.twprojetos.enums.UF;
import br.com.treinaweb.twprojetos.validators.PessoaValidator;

@ControllerAdvice(assignableTypes = {FuncionarioController.class, ClienteController.class})
public class PessoaController {

    @Autowired
    private PessoaValidator pessoaValidador;

    @InitBinder(value = {"funcionario", "cliente"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(pessoaValidador);
    }

    @ModelAttribute("ufs")
    public UF[] getUfs() {
        return UF.values();
    }

}
