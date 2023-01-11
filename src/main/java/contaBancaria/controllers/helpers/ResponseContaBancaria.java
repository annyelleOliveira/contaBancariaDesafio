package contaBancaria.controllers.helpers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseContaBancaria<T> {
    public static final String SUCESS_MESSAGE = "Success";
    public static final String NOT_FOUND_MESSAGE = "Not Found";
    public static final int SUCCESS_CODE = 1;
    public static final int ERROR_CODE = -1;
    public static final int NOT_FOUND_CODE = -2;

    private int code;
    private String message;
    private T body;

    public ResponseContaBancaria(int value, String s) {
    }

}
