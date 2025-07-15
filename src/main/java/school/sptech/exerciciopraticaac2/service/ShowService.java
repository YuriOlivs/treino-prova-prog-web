package school.sptech.exerciciopraticaac2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exerciciopraticaac2.entity.Ingresso;
import school.sptech.exerciciopraticaac2.entity.Show;
import school.sptech.exerciciopraticaac2.repository.IngressoRepository;
import school.sptech.exerciciopraticaac2.repository.ShowRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository repository;
    private final IngressoRepository ingressoRepository;

    public List<Show> listar() {
        List<Show> shows = repository.findAll();
        if (shows.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return repository.findAll();
    }

    public Show buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Show salvar(Show show) {
        Optional<Show> showOpt = repository.findByLocalizacaoAndData(show.getLocalizacao(), show.getData());
        if (showOpt.isEmpty()) {
            return repository.save(show);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Já há um evento cadastrado com este local e data");
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show não encontrado.");
        }
        List<Ingresso> ingressos = ingressoRepository.findByShowId(id);
        ingressoRepository.deleteAll(ingressos);
        repository.deleteById(id);
    }
}
