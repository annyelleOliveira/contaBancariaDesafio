package contaBancaria.agenciaController.controller;

import contaBancaria.ContaBancariaApplication;
import contaBancaria.controller.AgenciaController;
import contaBancaria.entities.Agencia;
import contaBancaria.service.AgenciaService;
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

import java.util.Optional;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContaBancariaApplication.class)
public class AgenciaControllerTest {

    @Autowired
    private AgenciaController controller;

    @Autowired
    AgenciaService service;

    Agencia agencia;

    @Before
    public void iniciar() {
        this.agencia = service.buscarNumeroAgencia("Igarassu-PE").get();
    }

    @Test
    public void encontrarAgenciaPorLocalidade() throws Exception {
        try {
            Optional<Agencia> optionalAgencia = service.buscarNumeroAgencia("Igarassu-PE");

            ResponseEntity<?> response = controller.encontrarAgencia("Igarassu-PE");

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void criarAgencia() throws Exception {
        try {
            this.agencia = new Agencia(10L, "Rio de Janeiro", 2022);

            ResponseEntity<?> response = controller.create(this.agencia);

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
