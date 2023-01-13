package contaBancaria;

import contaBancaria.entities.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovimentoTest {

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    public Usuario usuarioCadastroTest() {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeTitular("Annyelle Oliveira");
        usuario.setDataNascimento(LocalDate.of(1996, 9, 8));
        return usuario;
    }

    @Test
    public void deveBuscarUsuarioPorCpf() {
        Usuario usuario = usuarioCadastroTest();

        Assert.assertEquals(usuario.getCpf(), cpf);
    }

    @Test
    public void deveFalharBuscaPorCpf() {
        Usuario usuario = usuarioCadastroTest();
        Assert.assertEquals(usuario.getCpf(), cpfInvalido);
    }

}
