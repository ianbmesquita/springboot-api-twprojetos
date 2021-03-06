package br.com.treinaweb.twprojetos.exceptions;

public class CargoPossuiFuncionariosException extends RuntimeException {

    public CargoPossuiFuncionariosException(Long id) {
        super(String.format("Cargo com o ID %s possui funcionario(s) realacionado(s)", id));
    }

}
