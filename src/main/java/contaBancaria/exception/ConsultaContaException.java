package contaBancaria.exception;

public class ConsultaContaException extends Exception{

    public ConsultaContaException()
    {}

    public ConsultaContaException(String msg){
        super(msg);
    }

    public ConsultaContaException(Throwable cause){
        super(cause);
    }

    public ConsultaContaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
