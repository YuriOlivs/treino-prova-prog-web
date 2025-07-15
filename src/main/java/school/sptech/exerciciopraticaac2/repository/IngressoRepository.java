package school.sptech.exerciciopraticaac2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exerciciopraticaac2.entity.Ingresso;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
    List<Ingresso> findByShowId(Integer showId);
}
