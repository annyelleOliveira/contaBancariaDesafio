package contaBancaria.exception;

public class ParametroInvalidoException extends Exception{


    private static final long serialVersionUID = 8180548842969074936L;

    public ParametroInvalidoException()
    {}

    public ParametroInvalidoException(String msg){
        super(msg);
    }

    public ParametroInvalidoException(Throwable cause){
        super(cause);
    }

    public ParametroInvalidoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
