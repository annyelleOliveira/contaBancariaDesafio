package contaBancaria.controller;

import contaBancaria.service.MovimentoService;
import contaBancaria.controllers.helpers.ResponseContaBancaria;
import contaBancaria.entities.Movimento;
import contaBancaria.entities.dto.MovimentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimento")
public class MovimentoController {

    private static final Logger LOG = LoggerFactory.getLogger(MovimentoController.class);
    @Autowired
    MovimentoService movimentoService;

    @PostMapping(path = {"/salvar"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody MovimentoDTO request) throws Exception {
        try {
            Movimento movimento = movimentoService.create(request);
            return new ResponseEntity<>(movimento, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao tentar realizar cadastro.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseContaBancaria<>(HttpStatus.BAD_REQUEST.value(), "Ocorreu um erro ao tentar realizar cadastro. Mensagem: " + e.getMessage()));
        }
    }

    @GetMapping(path = {"/buscarTodos"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Movimento>> findAll() {
        List<Movimento> movimento = movimentoService.listaMovimento();
        return new ResponseEntity<>(movimento, HttpStatus.OK);
    }
}
