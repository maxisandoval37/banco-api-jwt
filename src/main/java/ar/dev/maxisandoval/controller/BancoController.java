package ar.dev.maxisandoval.controller;

import ar.dev.maxisandoval.entity.Banco;
import ar.dev.maxisandoval.service.BancoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.*;

@RestController
@RequestMapping("/bancos")
@AllArgsConstructor
public class BancoController {

    private final BancoService bancoService;
    private final HttpServletRequest request;

    @GetMapping("/all")
    public List<Banco> getAll() {
        return bancoService.getAll();
    }

    @GetMapping("/{id}")
    public Banco getById(@PathVariable Long id) {
        return bancoService.getById(id);
    }

    @PostMapping("/create")
    public Banco create(@RequestBody Banco banco) {
        banco.setId(null);
        return bancoService.save(banco);
    }

    @PutMapping("/{id}")
    public Banco update(@PathVariable Long id, @RequestBody Banco banco) {
        return bancoService.update(id, banco);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bancoService.deleteById(id);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Banco>> getActiveBanks() {
        return ResponseEntity.ok(bancoService.getActivos());
    }

    //endpoint que se llama a otro endpoint de la misma app
    @GetMapping("/activos/count")
    public ResponseEntity<Map<String, Object>> countBancosActivos() {

        RestTemplate restTemplate = new RestTemplate();
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/bancos/activos").toUriString();

        // Reutilizar baerer token de la request original
        HttpHeaders headers = new HttpHeaders();
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            headers.set(HttpHeaders.AUTHORIZATION, authHeader);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Banco[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Banco[].class);

        Banco[] bancos = Objects.requireNonNullElseGet(response.getBody(), () -> new Banco[0]);

        return ResponseEntity.ok(Map.of("cantidadBancosActivos", bancos.length));
    }
}