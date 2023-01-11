package contaBancaria.controller;

import contaBancaria.controllers.helpers.ResponseContaBancaria;
import contaBancaria.entities.Conta;
import contaBancaria.entities.Movimento;
import contaBancaria.entities.dto.ContaDTO;
import contaBancaria.exception.ConsultaContaException;
import contaBancaria.service.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private static final Logger LOG = LoggerFactory.getLogger(ContaController.class);
    @Autowired
    ContaService contaService;

    @PostMapping(path = {"/salvar"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody ContaDTO conta) throws Exception {
        try {
            Conta newConta = contaService.criar(conta);
            return new ResponseEntity<>(newConta, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao tentar realizar cadastro.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseContaBancaria<>(HttpStatus.BAD_REQUEST.value(), "Ocorreu um erro ao tentar realizar cadastro. Mensagem: " + e.getMessage()));

        }
    }

    @GetMapping(path = {"/buscarConta/{idConta}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarConta(@PathVariable("idConta") String idConta) {
        Optional<Conta> conta = contaService.consultarSaldo(idConta);
        if (!conta.isEmpty() || conta.isPresent()) {
            return ResponseEntity.ok(new ResponseContaBancaria<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), conta));
        } else {
            LOG.error("Ocorreu um erro ao tentar recuperar a conta pelo número: " + idConta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Ocorreu um erro ao tentar recuperar informações da conta : " + idConta));
        }
    }

    @GetMapping(path = {"/buscarTodos"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Conta>> findAll() {
        List<Conta> contas = contaService.listarContas();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @GetMapping(path = {"/{idConta}/saldos"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> extratoDetalhado(@PathVariable("idConta") String idConta) throws ConsultaContaException {
        List<Movimento> extrato = contaService.extratoDetalhadoConta(idConta);
        if (!extrato.isEmpty() || Objects.nonNull(extrato)) {
            return new ResponseEntity<>(extrato, HttpStatus.OK);
        } else {
            LOG.error("Ocorreu um erro ao tentar recuperar : " + idConta);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Ocorreu um erro ao tentar recuperar conta: " + idConta));
        }
    }
}
