package ar.dev.maxisandoval.service;

import ar.dev.maxisandoval.entity.Banco;
import ar.dev.maxisandoval.repository.BancoRepository;
import ar.dev.maxisandoval.exception.BancoDuplicadoException;
import ar.dev.maxisandoval.exception.BancoNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BancoService {

    private final BancoRepository bancoRepository;

    @Cacheable("bancos")
    public List<Banco> getAll() {
        return bancoRepository.findAll();
    }

    @Cacheable(value = "banco", key = "#id")
    public Banco getById(Long id) {
        return bancoRepository.findById(id)
                .orElseThrow(() -> new BancoNotFoundException(id));
    }

    @CachePut(value = "banco", key = "#result.id")
    @CacheEvict(value = "bancos", allEntries = true)
    public Banco save(Banco banco) {

        boolean existeDuplicado = bancoRepository
                .existsByNombreIgnoreCaseAndPaisIgnoreCase(banco.getNombre(), banco.getPais());

        if (existeDuplicado) {
            throw new BancoDuplicadoException(banco.getNombre(), banco.getPais());
        }

        return bancoRepository.save(banco);
    }

    @CachePut(value = "banco", key = "#id")
    @CacheEvict(value = "bancos", allEntries = true)
    public Banco update(Long id, Banco updatedBanco) {
        Banco existingBanco = getById(id);
        existingBanco.setNombre(updatedBanco.getNombre());
        existingBanco.setPais(updatedBanco.getPais());
        existingBanco.setActivo(updatedBanco.getActivo());
        return bancoRepository.save(existingBanco);
    }

    // Elimina toda la caché
    @CacheEvict(value = "bancos", allEntries = true)
    public void deleteById(Long id) {
        bancoRepository.deleteById(id);
    }

    @CacheEvict(value = "bancos", allEntries = true)
    public void deleteAll() {
        bancoRepository.deleteAll();
    }

    public List<Banco> getActivos() {
        return bancoRepository.findByActivoTrue();
    }
}