package ar.dev.maxisandoval.exception;

public class BancoDuplicadoException extends RuntimeException {

    public BancoDuplicadoException(String nombre, String pais) {
        super("Ya existe un banco con nombre '" + nombre + "' y país '" + pais + "'");
    }
}