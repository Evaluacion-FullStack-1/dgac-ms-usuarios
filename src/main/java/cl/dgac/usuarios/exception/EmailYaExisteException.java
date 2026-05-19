package cl.dgac.usuarios.exception;

public class EmailYaExisteException extends RuntimeException {

    public EmailYaExisteException(String mensaje) {
        super(mensaje);
    }
}