package hct.salas.duban.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import hct.salas.duban.model.Cliente;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Long> {
}