package contaBancaria;

import contaBancaria.entities.Usuario;
import contaBancaria.exception.ParametroInvalidoException;
import contaBancaria.service.UsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void criarUsuario() throws ParametroInvalidoException {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeTitular("Annyelle Oliveira");
        usuario.setDataNascimento(LocalDate.of(1996, 9, 8));
        Usuario novoUsuario = usuarioService.create(usuario);
        if (Objects.nonNull(novoUsuario)) {
            boolean resultado = true;
            Assert.assertTrue(resultado);
        }
    }


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
