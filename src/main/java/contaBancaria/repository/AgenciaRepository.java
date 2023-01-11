package contaBancaria.repository;

import contaBancaria.entities.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Transactional(readOnly = false)
@Repository
public interface AgenciaRepository extends JpaRepository <Agencia, Long>{

    Optional<Agencia> findByLocalAgencia (String localAgencia);

}
