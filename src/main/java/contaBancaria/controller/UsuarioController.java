package contaBancaria.controller;

import contaBancaria.controllers.helpers.ResponseContaBancaria;
import contaBancaria.entities.Usuario;
import contaBancaria.exception.ParametroInvalidoException;
import contaBancaria.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    UsuarioService usuarioService;

    @PostMapping(path = {"/salvar"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody Usuario usuario) throws ParametroInvalidoException {
        try {
            Usuario newUsuario = usuarioService.create(usuario);
            return new ResponseEntity<>(newUsuario, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Ocorreu um erro ao tentar realizar cadastro.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseContaBancaria<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),"Ocorreu um erro ao tentar realizar cadastro. Mensagem: " + e.getMessage()));
        }
    }

    @GetMapping(path = {"/buscarUsuario/{cpf}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> encontrarUsuario(@PathVariable("cpf") String cpf) {
        Optional<Usuario> usuario = usuarioService.buscaCpf(cpf);
        if (!usuario.isEmpty() || usuario.isPresent()) {
            return ResponseEntity.ok(new ResponseContaBancaria<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), usuario));
        } else {
            LOG.error("Ocorreu um erro ao tentar recuperar o usuario pelo cpf: " + cpf);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseContaBancaria<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Ocorreu um erro ao tentar recuperar usuário através do cpf: " + cpf));
        }
    }

    @GetMapping(path = {"/buscarTodos"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
