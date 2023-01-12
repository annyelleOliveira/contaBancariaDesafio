package contaBancariaTest;

import contaBancaria.entities.Usuario;
import contaBancaria.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    private static final String cpf = "12345678998";
    private static final String cpfInvalido = "1234567";

    @Autowired
    UsuarioRepository usuarioRepository;


//    @BeforeEach
//    void setup() {
//        this.em = JPAConfig.getEntityManager();
//        this.usuarioRepositoryTest = new UsuarioRepositoryTest(em);
//        em.getTransaction().begin();
//    }
//
//    @AfterEach
//    void afterEach() {
//        this.em.getTransaction().getRollbackOnly();
//    }
//
//    private Usuario persistirUsuario() {
//        Usuario usuario = new JPAConfig.UsuarioBuilder()
//                .comCpf(cpf)
//                .comNomeTitular("Annyelle")
//                .comDataNascimento(LocalDate.of(1996, 9, 8))
//                .criar();
////        em.persist(usuario);
//        return usuario;
//    }
//
//    @Test
//    public void testeDeveriaEncontrarUsuarioCadastroPeloCpf() {
//        Usuario usuario = persistirUsuario();
////        Usuario usuarioEncontrado = usuarioRepositoryTest.buscarUsuarioPorCpf(cpf);
////
////        Assert.assertNotNull(usuarioEncontrado);
//        Assert.assertEquals(usuario.getCpf(), persistirUsuario().getCpf());
//    }
//
//    @Test
//    public void testeComFalhaAoProcurarUsuarioQueNaoExisteCadastradoPeloCpf() {
//        persistirUsuario();
//
//        Assert.assertThrows(NoResultException.class, () -> usuarioRepositoryTest.buscarUsuarioPorCpf(cpf));
//    }


//    @BeforeEach
//    public void iniciar() throws Exception {
//        Usuario usuario = new Usuario();
//        usuario.setCpf(cpf);
//        usuario.setNomeTitular("Annyelle Oliveira");
//        usuario.setDataNascimento(LocalDate.of(1996, 9, 8));
//
////        mockMvc.perform(post("/usuario/salvar")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(asJsonStrring(usuario)))
//        usuarioRepository.save(usuario);
//    }
//

    public void deveCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeTitular("Annyelle Oliveira");
        usuario.setDataNascimento(LocalDate.of(1996, 9, 8));
        usuarioRepository.save(usuario);

    }

    @Test
    public void buscarUsuarioPorCpf() {
        Optional<Usuario> usuario = usuarioRepository.findByCpf("44449343875");

        Assert.assertEquals(usuario.get().getCpf(), cpf);
    }
//
//    @Test
//    public void buscarUsuarioPorIdTest() {
//        Usuario usuarioSalvo = this.usuarioRepository.save(this.usuario);
//
//        ResponseEntity<Usuario> response = this.testRestTemplate
//                .exchange("/buscarUsuario/{cpf}" + usuarioSalvo.getCpf(), HttpMethod.GET, null, Usuario.class);
//
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
//        Assert.assertEquals(response.getBody().getCpf(), cpf);
//    }
}
