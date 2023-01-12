package contaBancaria.utils;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoRequest {

    @NotNull
    private String topic;

    @NotNull
    private List<String> destinatarios;

}
