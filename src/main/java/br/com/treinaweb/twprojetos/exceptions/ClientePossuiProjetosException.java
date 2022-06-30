package br.com.treinaweb.twprojetos.exceptions;

public class ClientePossuiProjetosException extends RuntimeException {

    public ClientePossuiProjetosException(Long id) {
        super(String.format("Cliente com o ID %s possui projeto(s) relacionado(s)", id));
    }

}
