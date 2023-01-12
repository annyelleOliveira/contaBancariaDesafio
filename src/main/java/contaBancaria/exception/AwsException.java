package contaBancaria.exception;

public class AwsException extends Exception{


    private static final long serialVersionUID = 8180548842969074936L;

    public AwsException()
    {}

    public AwsException(String msg){
        super(msg);
    }

    public AwsException(Throwable cause){
        super(cause);
    }

    public AwsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
