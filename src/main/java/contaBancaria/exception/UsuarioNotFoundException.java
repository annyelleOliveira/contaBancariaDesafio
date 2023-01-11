package contaBancaria.exception;

public class UsuarioNotFoundException extends Exception{

    public UsuarioNotFoundException()
    {}

    public UsuarioNotFoundException(String msg){
        super(msg);
    }

    public UsuarioNotFoundException(Throwable cause){
        super(cause);
    }

    public UsuarioNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
