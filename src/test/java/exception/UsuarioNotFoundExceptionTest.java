package exception;

public class UsuarioNotFoundExceptionTest extends Exception{

    public UsuarioNotFoundExceptionTest()
    {}

    public UsuarioNotFoundExceptionTest(String msg){
        super(msg);
    }

    public UsuarioNotFoundExceptionTest(Throwable cause){
        super(cause);
    }

    public UsuarioNotFoundExceptionTest(String msg, Throwable cause) {
        super(msg, cause);
    }
}
