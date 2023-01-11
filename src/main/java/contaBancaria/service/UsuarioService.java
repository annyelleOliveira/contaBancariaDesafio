package contaBancaria.service;

import contaBancaria.entities.Usuario;
import contaBancaria.exception.ParametroInvalidoException;
import contaBancaria.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario create(Usuario usuario) throws ParametroInvalidoException {
        validaCamposCriarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscaCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    private void validaCamposCriarUsuario(Usuario usuario) throws ParametroInvalidoException {
        LOG.info("Validando os campos para a criação do Usuário.");
        validaCpf(usuario.getCpf());
        validaNomeTitular(usuario.getNomeTitular());
        validaDataNascimento(usuario.getDataNascimento());
    }

    private void validaCpf(String cpf) throws ParametroInvalidoException {
        if (Objects.isNull(cpf) || cpf.length() != 11) {
            throw new ParametroInvalidoException("Cpf não informado ou inválido. Favor, verificar.");
        }
    }

    private void validaNomeTitular(String nomeTitular) throws ParametroInvalidoException {
        if (Objects.isNull(nomeTitular)) {
            throw new ParametroInvalidoException("Nome não informado. Favor, verificar.");
        }
    }

    private void validaDataNascimento(LocalDate dataNascimento) throws ParametroInvalidoException {
        if (Objects.isNull(dataNascimento)) {
            throw new ParametroInvalidoException("Data de nascimento não informada. Favor, verificar.");
        }
    }
}
