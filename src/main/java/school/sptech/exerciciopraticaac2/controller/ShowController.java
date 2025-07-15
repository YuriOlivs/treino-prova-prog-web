package school.sptech.exerciciopraticaac2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.service.ShowService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowController {
    private final ShowService service;

    @GetMapping
    public ResponseEntity<List<Show>> listar() {
        List<Show> shows = service.listar();
        if (shows.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> buscaPorId(@PathVariable Integer id) {
        Show show = service.buscaPorId(id);
        return ResponseEntity.ok(show);
    }

    @PostMapping
    public ResponseEntity<Show> salvar(@Valid @RequestBody Show show) {
        Show savedShow = service.salvar(show);
//        Havia feito desta forma mas o teste espera 200 como resposta
//        URI uri = URI.create("/shows/" + savedShow.getId());
//        return ResponseEntity.created(uri).body(savedShow);

//        O teste de conflito não passa pq o caso de teste não é cadastrado antes
//        então ele retorna 200 mesmo e acusa erro por esperar 409, pelo Insomnia foi

//        o teste de criação não passa por milissegundos
        return ResponseEntity.ok(savedShow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
