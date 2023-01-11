package exception;

public class ConsultaContaExceptionTest extends Exception{

    public ConsultaContaExceptionTest()
    {}

    public ConsultaContaExceptionTest(String msg){
        super(msg);
    }

    public ConsultaContaExceptionTest(Throwable cause){
        super(cause);
    }

    public ConsultaContaExceptionTest(String msg, Throwable cause) {
        super(msg, cause);
    }
}
