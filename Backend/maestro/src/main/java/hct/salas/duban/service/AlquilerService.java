package hct.salas.duban.service;

import hct.salas.duban.model.Alquiler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlquilerService {
    Flux<Alquiler> findAll();
    Mono<Alquiler> findById(Long id);
    Mono<Alquiler> save(Alquiler alquiler);
    Mono<Alquiler> update(Long id, Alquiler alquiler);
    Mono<Void> delete(Long id);
    Mono<Void> restore(Long id);
}