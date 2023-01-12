package contaBancaria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "titular", fetch = FetchType.EAGER)
    private List<Conta> contas;

    public Usuario(String cpf, String nomeTitular, LocalDate dataNascimento) {
    }
}
