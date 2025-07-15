package school.sptech.exerciciopraticaac2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.service.IngressoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService service;

    @GetMapping
    public ResponseEntity<List<Ingresso>> listar() {
        List<Ingresso> ingressos = service.listar();
        if (ingressos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingressos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingresso> buscarPorId(@PathVariable Integer id) {
        Ingresso ingresso = service.buscarPorId(id);
        return ResponseEntity.ok(ingresso);
    }

    @PostMapping("/shows/{showId}")
    public ResponseEntity<Ingresso> salvar(
            @PathVariable Integer showId,
            @Valid @RequestBody Ingresso ingresso
    ) {
        Ingresso savedIngresso = service.salvar(ingresso, showId);
        URI uri = URI.create("/ingressos/" + savedIngresso.getId());
        return ResponseEntity.created(uri).body(savedIngresso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
