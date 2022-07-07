package br.com.treinaweb.twprojetos.exceptions;

public class FuncionarioEhLiderDeProjetoException extends RuntimeException {

    public FuncionarioEhLiderDeProjetoException(Long id) {
        super(String.format("Funcionario com ID %d é lider de projeto(s)", id));
    }

}
