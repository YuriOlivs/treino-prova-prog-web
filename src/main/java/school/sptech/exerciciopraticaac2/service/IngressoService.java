package school.sptech.exerciciopraticaac2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.repository.IngressoRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class IngressoService {
    private final IngressoRepository repository;
    private final ShowService showService;

    public List<Ingresso> listar() {
        return repository.findAll();
    }

    public Ingresso buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Ingresso salvar(Ingresso ingresso, Integer showId) {
        Show show = showService.buscaPorId(showId);
        ingresso.setShow(show);

        return repository.save(ingresso);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado.");
        }

        repository.deleteById(id);
    }
}
