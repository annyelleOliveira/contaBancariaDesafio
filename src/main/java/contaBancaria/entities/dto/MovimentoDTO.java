package contaBancaria.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovimentoDTO {

    private String cpf;
    private int numeroConta;
    private BigDecimal valorMovimentacao;
    private String ds_movimento;
    private String tipoMovimento;

}
