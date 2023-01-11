package exception;

public class ParametroInvalidoExceptionTest extends Exception{


    private static final long serialVersionUID = 8180548842969074936L;

    public ParametroInvalidoExceptionTest()
    {}

    public ParametroInvalidoExceptionTest(String msg){
        super(msg);
    }

    public ParametroInvalidoExceptionTest(Throwable cause){
        super(cause);
    }

    public ParametroInvalidoExceptionTest(String msg, Throwable cause) {
        super(msg, cause);
    }
}
