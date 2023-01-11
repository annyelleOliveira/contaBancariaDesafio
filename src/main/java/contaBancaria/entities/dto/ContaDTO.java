package contaBancaria.entities.dto;

import contaBancaria.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    private Integer numero_conta;
    private Integer digito_conta;
    private String localidade;
    private String cpf;

}
