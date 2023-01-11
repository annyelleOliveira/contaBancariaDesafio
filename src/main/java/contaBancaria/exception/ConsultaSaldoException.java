package contaBancaria.exception;

public class ConsultaSaldoException extends Exception{

    public ConsultaSaldoException()
    {}

    public ConsultaSaldoException(String msg){
        super(msg);
    }

    public ConsultaSaldoException(Throwable cause){
        super(cause);
    }

    public ConsultaSaldoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
