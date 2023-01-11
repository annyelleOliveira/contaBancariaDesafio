package contaBancaria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "movimento")
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorMovimentacao;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoAtual;
    private String ds_movimento;
    private LocalDateTime dh_movimento;
    private String tipoMovimento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta")
    private Conta idConta;

    @ManyToOne()
    @JoinColumn(name = "usuario")
    private Usuario titular;

}
