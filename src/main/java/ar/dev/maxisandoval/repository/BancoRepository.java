package ar.dev.maxisandoval.repository;

import ar.dev.maxisandoval.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    boolean existsByNombreIgnoreCaseAndPaisIgnoreCase(String nombre, String pais);
    List<Banco> findByActivoTrue();
}