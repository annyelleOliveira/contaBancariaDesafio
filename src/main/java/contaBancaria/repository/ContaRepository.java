package contaBancaria.repository;

import contaBancaria.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional(readOnly = false)
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByIdConta(String idConta);

    Optional<Conta> findByNumeroConta(int numeroConta);


}
