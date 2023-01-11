package contaBancaria.repository;

import contaBancaria.entities.Conta;
import contaBancaria.entities.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    @Query("SELECT m FROM Movimento m WHERE m.idConta=:idConta")
    List<Movimento> findByIdConta(Conta idConta);


}
