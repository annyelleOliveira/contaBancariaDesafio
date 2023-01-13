package contaBancaria.contaController.controller;

import contaBancaria.ContaBancariaApplication;
import contaBancaria.controller.ContaController;
import contaBancaria.entities.Agencia;
import contaBancaria.entities.Conta;
import contaBancaria.entities.dto.ContaDTO;
import contaBancaria.service.AgenciaService;
import contaBancaria.service.ContaService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContaBancariaApplication.class)
public class ContaControllerTest {

    @Autowired
    private ContaController controller;

    @Autowired
    ContaService service;

    @Autowired
    AgenciaService agenciaService;

    @Autowired
    UsuarioService usuarioService;

    Optional<Conta> conta;

    @Before
    public void iniciar() {
        this.conta = service.buscarPorIdConta("20225061122023");
    }

    @Test
    public void encontrarContaPorIdConta() throws Exception {
        try {

            ResponseEntity<?> response = controller.buscarConta("20225061122023");

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void criarConta() {
        try {

            Conta newConta = new Conta();
            newConta.setSaldo(BigDecimal.valueOf(0));
            newConta.setDataCriacaoConta(LocalDateTime.now());
            newConta.setAgencia(agenciaService.buscarNumeroAgencia("Igarassu-PE").get());
            newConta.setTitular(usuarioService.buscaCpf("44449343870").get());
            newConta.setNumeroConta(121);
            newConta.setDigitoConta(1);
            newConta.setIdConta("202312101012023");

            ContaDTO contaDTO = new ContaDTO(121, 1, "Igarassu-PE", "44449343870");
            ResponseEntity<?> response = controller.create(contaDTO);

            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
