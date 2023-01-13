package contaBancaria.usuarioController.controller;

import contaBancaria.ContaBancariaApplication;
import contaBancaria.controller.UsuarioController;
import contaBancaria.entities.Usuario;
import contaBancaria.service.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContaBancariaApplication.class)
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;

    @Autowired
    UsuarioService usuarioService;

    Usuario usuario;

    @Before
    public void iniciar() {
        this.usuario = usuarioService.buscaCpf("44449343875").get();
    }

    @Test
    public void encontrarPorCpf() throws Exception {
        try {
            this.usuario = usuarioService.buscaCpf("44449343875").get();

            ResponseEntity<?> response = controller.encontrarUsuario("44449343875");

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void encontrarTodosOsUsuarios() throws Exception {
        try {

            ResponseEntity<?> response = controller.findAll();

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
