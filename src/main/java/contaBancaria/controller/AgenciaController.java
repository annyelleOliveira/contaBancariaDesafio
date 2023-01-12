package contaBancaria.controller;

import contaBancaria.entities.Agencia;
import contaBancaria.controllers.helpers.ResponseContaBancaria;
import contaBancaria.service.AgenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    private static final Logger LOG = LoggerFactory.getLogger(AgenciaController.class);
    @Autowired
    AgenciaService agenciaService;

    @PostMapping(path = {"/salvar"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Agencia> create(@RequestBody Agencia request) {
        Agencia agencia = agenciaService.criarAgencia(request);
        return new ResponseEntity<>(agencia, HttpStatus.OK);
    }

    @GetMapping(path = {"/buscarAgencia"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> encontrarAgencia(@RequestParam String localAgencia) {
        Optional<Agencia> agencia = agenciaService.buscarNumeroAgencia(localAgencia);
        if (!agencia.isEmpty() || agencia.isPresent()) {
            return Objects.nonNull(agencia) ? ResponseEntity.status(HttpStatus.OK).body(agencia) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<Agencia>(HttpStatus.NOT_FOUND.value(), "Agencia não encontrada em banco de acordo com o local informado: " + localAgencia + ".Favor, verificar e tentar novamente."));
        } else {
            LOG.error("Ocorreu um erro ao tentar recuperar o Agencia pela localidade(Estado): " + localAgencia);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Ocorreu um erro ao tentar recuperar Agencia pela localidade(Estado): " + localAgencia));
        }
    }

    @GetMapping(path = {"/buscarAgenciaId/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> encontrarAgencia(@PathVariable("id") Long id) {
        try {
            Optional<Agencia> agencia = agenciaService.buscarPorId(id);
            return Objects.nonNull(agencia) ? ResponseEntity.status(HttpStatus.OK).body(agencia) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<Agencia>(HttpStatus.NOT_FOUND.value(), "Agencia não encontrada em banco de acordo com o id informado: " + id + ".Favor, verificar e tentar novamente."));
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao tentar recuperar Agencia pelo id: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseContaBancaria<>(HttpStatus.BAD_REQUEST.value(), "Ocorreu um erro ao tentar recuperar Agencia através do id: " + id + ".Messagem: " + e.getMessage()));
        }
    }
}
