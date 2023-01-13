package contaBancaria;

import contaBancaria.entities.Conta;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaTest {

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    public Conta contaCadastroTest() {
        Conta conta = new Conta();
//        conta.set(cpf);
//        conta.setNomeTitular("Annyelle Oliveira");
        return conta;
    }

    @Test
    public void deveBuscarUsuarioPorCpf() {
        Conta conta = contaCadastroTest();

//        Assert.assertEquals(conta.getCpf(), cpf);
    }

    @Test
    public void deveFalharBuscaPorCpf() {
//        Conta conta = usuarioCadastroTest();
//        Assert.assertEquals(conta.getCpf(), cpfInvalido);
    }

}
