package br.com.treinaweb.twprojetos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.treinaweb.twprojetos.repositories.ClienteRepository;
import br.com.treinaweb.twprojetos.repositories.FuncionarioRepository;
import br.com.treinaweb.twprojetos.validators.ClienteValidator;
import br.com.treinaweb.twprojetos.validators.FuncionarioValidator;
import br.com.treinaweb.twprojetos.validators.PessoaValidator;

@Configuration
public class ConfigValidator {

    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private FuncionarioRepository funcionarioRepositorio;

    @Bean
    public ClienteValidator clienteValidador() {
        return new ClienteValidator(clienteRepositorio);
    }

    @Bean
    public FuncionarioValidator funcionarioValidador() {
        return new FuncionarioValidator(funcionarioRepositorio);
    }

    @Bean
    public PessoaValidator pessoaValidador() {
        return new PessoaValidator();
    }

}
