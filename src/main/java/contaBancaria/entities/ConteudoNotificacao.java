package contaBancaria.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConteudoNotificacao {

    private String numero_agencia;
    private String numero_conta;
    private String digito_conta;
    private String valor_movimento;

    @JsonCreator
    public ConteudoNotificacao(@JsonProperty("agencia") String numero_agencia,
                               @JsonProperty("numero_conta") String numero_conta,
                               @JsonProperty("digito_conta") String digito_conta,
                               @JsonProperty("valor_movimento") String valor_movimento) {
        this.numero_agencia = numero_agencia;
        this.numero_conta = numero_conta;
        this.digito_conta = digito_conta;
        this.valor_movimento = valor_movimento;
    }
}
