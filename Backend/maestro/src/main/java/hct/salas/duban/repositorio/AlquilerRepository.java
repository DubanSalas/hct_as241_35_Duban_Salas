package hct.salas.duban.repositorio;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import hct.salas.duban.model.Alquiler;


@Repository
public interface AlquilerRepository extends ReactiveCrudRepository<Alquiler, Long> {
}