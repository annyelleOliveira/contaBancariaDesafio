package contaBancaria.movimentoController.controller;

import contaBancaria.ContaBancariaApplication;
import contaBancaria.controller.MovimentoController;
import contaBancaria.entities.Movimento;
import contaBancaria.service.MovimentoService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContaBancariaApplication.class)
public class MovimentoControllerTest {

    @Autowired
    private MovimentoController controller;

    @Autowired
    MovimentoService service;

    Movimento movimento;


    @Test
    public void encontrarTodosMovimentos() throws Exception {
        try {

            ResponseEntity<?> response = controller.findAll();

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
