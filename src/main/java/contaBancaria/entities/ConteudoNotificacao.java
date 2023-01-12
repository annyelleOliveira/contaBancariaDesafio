package contaBancaria.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConteudoNotificacao {

    private int agencia;
    private int numero_conta;
    private int digito_conta;
    private BigDecimal valor_movimento;

}
