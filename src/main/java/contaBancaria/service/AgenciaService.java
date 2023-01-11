package contaBancaria.service;

import contaBancaria.entities.Agencia;
import contaBancaria.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgenciaService {

    @Autowired
    AgenciaRepository agenciaRepository;

    public Agencia criarAgencia(Agencia request){
        return agenciaRepository.save(request);
    }
    public Optional<Agencia> buscarNumeroAgencia(String localAgencia){
        return agenciaRepository.findByLocalAgencia(localAgencia);
    }

    public Optional<Agencia> buscarPorId(Long id){
        return agenciaRepository.findById(id);
    }
}
