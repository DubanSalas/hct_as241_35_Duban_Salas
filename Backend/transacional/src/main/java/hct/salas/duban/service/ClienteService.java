package hct.salas.duban.service;

import hct.salas.duban.model.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
    Flux<Cliente> findAll();
    Mono<Cliente> findById(Long id);
    Mono<Cliente> save(Cliente cliente);
    Mono<Cliente> update(Long id, Cliente cliente);
    Mono<Void> delete(Long id);
    Mono<Void> restore(Long id);
}