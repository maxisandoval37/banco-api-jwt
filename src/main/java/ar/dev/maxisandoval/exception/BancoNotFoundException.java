package ar.dev.maxisandoval.exception;

public class BancoNotFoundException extends RuntimeException {

    public BancoNotFoundException(Long id) {
        super("Banco no encontrado con id: " + id);
    }
}