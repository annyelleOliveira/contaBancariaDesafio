package contaBancaria;

import contaBancaria.entities.Agencia;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgenciaTest {

    private static final String localAgencia = "Sao Paulo";
    private static final int numeroAgencia = 2023;





    public Agencia agenciaCadastroTest() {
        Agencia agencia = new Agencia();
        agencia.setLocalAgencia(localAgencia);
        agencia.setNumeroAgencia(numeroAgencia);
        return agencia;
    }

    @Test
    public void deveBuscarAgenciaPorNumeroAgencia() {
        Agencia agencia = agenciaCadastroTest();

        Assert.assertEquals(Optional.ofNullable(agencia.getNumeroAgencia()), numeroAgencia);
    }

    @Test
    public void deveFalharBuscaPorNumeroAgencia() {
        Agencia agencia = agenciaCadastroTest();
        Assert.assertEquals(Optional.ofNullable(agencia.getNumeroAgencia()), 2024);
    }

}
