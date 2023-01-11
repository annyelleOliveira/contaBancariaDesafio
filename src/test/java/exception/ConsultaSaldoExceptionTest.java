package exception;

public class ConsultaSaldoExceptionTest extends Exception{

    public ConsultaSaldoExceptionTest()
    {}

    public ConsultaSaldoExceptionTest(String msg){
        super(msg);
    }

    public ConsultaSaldoExceptionTest(Throwable cause){
        super(cause);
    }

    public ConsultaSaldoExceptionTest(String msg, Throwable cause) {
        super(msg, cause);
    }
}
