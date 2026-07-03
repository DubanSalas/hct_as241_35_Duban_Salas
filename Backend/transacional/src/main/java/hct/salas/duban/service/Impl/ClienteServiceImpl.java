package hct.salas.duban.service.Impl;

import org.springframework.stereotype.Service;

import hct.salas.duban.model.Cliente;
import hct.salas.duban.repository.ClienteRepository;
import hct.salas.duban.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public Flux<Cliente> findAll() {
        return repository.findAll()
                .doOnNext(c -> log.info("[Invocar] Dni: {}, Nombre: {}, Apellido: {}",
                        c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia()));
    }

    @Override
    public Mono<Cliente> findById(Long id) {
        return repository.findById(id)
                .doOnNext(c -> log.info("[Invocar] Doc: {}, Nombre: {}, Apellido: {}",
                        c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia()));
    }

    @Override
    public Mono<Cliente> save(Cliente cliente) {
        cliente.setStatus("A");
        return repository.save(cliente)
                .doOnNext(c -> log.info("[Registrar] Doc: {}, Nombre: {}, Apellido: {}",
                        c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia()));
    }

    @Override
    public Mono<Cliente> update(Long id, Cliente cliente) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setDni(cliente.getDni());
                    existing.setNombre(cliente.getNombre());
                    existing.setApellido(cliente.getApellido());
                    existing.setCorreo(cliente.getCorreo());
                    existing.setCelular(cliente.getCelular());
                    return repository.save(existing);
                })
                .doOnNext(c -> log.info("[Actualizar] Doc: {}, Nombre: {}, Apellido: {}",
                        c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia()));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .flatMap(c -> {
                    log.info("[Eliminar] Doc: {}, Nombre: {}, Apellido: {}",
                            c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia());
                    c.setStatus("I");
                    return repository.save(c);
                }).then();
    }

    @Override
    public Mono<Void> restore(Long id) {
        return repository.findById(id)
                .flatMap(c -> {
                    log.info("[Restaurar] Doc: {}, Nombre: {}, Apellido: {}",
                            c.getDni(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getCelular(), c.getLicencia());
                    c.setStatus("A");
                    return repository.save(c);
                }).then();
    }
}