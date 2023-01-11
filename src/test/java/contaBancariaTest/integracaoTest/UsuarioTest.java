package contaBancariaTest.integracaoTest;

import contaBancaria.entities.Usuario;
import contaBancaria.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.time.LocalDate;

public class UsuarioTest {

    @Mock
    private Usuario usuario;

    @Mock
    private UsuarioRepository usuarioRepository;

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    @BeforeEach
    public void setDados() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeTitular("Annyelle Oliveira");
        usuario.setDataNascimento(LocalDate.of(1996, 9, 8));
        usuarioRepository.save(usuario);
    }
}
