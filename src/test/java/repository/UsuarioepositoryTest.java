package repository;


import contaBancaria.entities.Usuario;
import contaBancaria.repository.UsuarioRepository;
import contaBancaria.service.UsuarioService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsuarioService.class)
@ActiveProfiles("test")
public class UsuarioepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    @Before
    public void setDados() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeTitular("Annyelle Oliveira");
        usuario.setDataNascimento(LocalDate.of(1996,9,8));
        usuarioRepository.save(usuario);
    }

    @Test
    public void testBuscarPorCpfValido(){
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
        Assertions.assertEquals(cpf, usuario.get().getCpf());
    }

    @Test
    public void testBuscarPorCpfInvalido(){
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
        Assertions.assertEquals(cpfInvalido, usuario.get().getCpf());
    }

    @After
    public final void limparBase(){
        this.usuarioRepository.deleteAll();
    }

}
