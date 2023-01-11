package contaBancaria.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    private String idConta;
    private Integer numeroConta;
    private Integer digitoConta;
    private LocalDateTime dataCriacaoConta;
    protected BigDecimal saldo;

    @ManyToOne()
    @JoinColumn(name = "conta_usuario")
    private Usuario titular;

    @ManyToOne()
    @JoinColumn(name = "conta_agencia")
    private Agencia agencia;


}