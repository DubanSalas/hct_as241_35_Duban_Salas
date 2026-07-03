package hct.salas.duban.service.Impl;


import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import hct.salas.duban.model.Alquiler;
import hct.salas.duban.model.ClienteDTO;
import hct.salas.duban.repositorio.AlquilerRepository;
import hct.salas.duban.service.AlquilerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository repository;
    private final WebClient webClient;

    // Consulta los datos del cliente al ms-cliente
    private Mono<ClienteDTO> getCliente(Long clienteId) {
        return webClient.get()
                .uri("/v1/api/cliente/{id}", clienteId)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .onErrorReturn(new ClienteDTO());
    }

    @Override
    public Flux<Alquiler> findAll() {
        return repository.findAll()
                .flatMap(a -> getCliente(a.getClienteId())
                        .map(c -> { a.setCliente(c); return a; }))
                .doOnNext(a -> log.info("[Invocar] AlquilerID: {}, ClienteID: {}, Dias: {}, Fecha: {}, Fechafin: {}, Total: {}",
                        a.getId(), a.getClienteId(), a.getDias(), a.getFechainicio(), a.getFechafin(), a.getTotal()));
    }

    @Override
    public Mono<Alquiler> findById(Long id) {
        return repository.findById(id)
                .flatMap(a -> getCliente(a.getClienteId())
                        .map(c -> { a.setCliente(c); return a; }))
                .doOnNext(a -> log.info("[Invocar] AlquilerID: {}, ClienteID: {}, Dias: {}, Fecha: {}, Fechafin: {}, Total: {}",
                        a.getId(), a.getClienteId(), a.getDias(), a.getFechainicio(), a.getFechafin(), a.getTotal()));
    }

    @Override
    public Mono<Alquiler> save(Alquiler alquiler) {
        alquiler.setFechainicio(LocalDate.now());
        alquiler.setFechafin(alquiler.getFechainicio().plusDays(Long.parseLong(alquiler.getDias())));
        alquiler.setStatus("A");
        return repository.save(alquiler)
                .doOnNext(a -> log.info("[Registrar] ClienteID: {}, Dias: {}, Fecha: {}, Fechafin: {}, Total: {}",
                        a.getClienteId(), a.getDias(), a.getFechainicio(), a.getFechafin(), a.getTotal()));
    }

    @Override
    public Mono<Alquiler> update(Long id, Alquiler alquiler) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setClienteId(alquiler.getClienteId());
                    existing.setDias(alquiler.getDias());
                    existing.setFechainicio(alquiler.getFechainicio());
                    existing.setFechafin(alquiler.getFechafin());
                    existing.setTotal(alquiler.getTotal());
                    return repository.save(existing);
                })
                .doOnNext(a -> log.info("[Actualizar] ClienteID: {}, Dias: {}, Fecha: {}, Fechafin: {}, Total: {}",
                        a.getClienteId(), a.getDias(), a.getFechainicio(), a.getFechafin(), a.getTotal()));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .flatMap(a -> {
                    log.info("[Eliminar] AlquilerID: {}, ClienteID: {}", a.getId(), a.getClienteId());
                    a.setStatus("I");
                    return repository.save(a);
                }).then();
    }

    @Override
    public Mono<Void> restore(Long id) {
        return repository.findById(id)
                .flatMap(a -> {
                    log.info("[Restaurar] AlquilerID: {}, ClienteID: {}", a.getId(), a.getClienteId());
                    a.setStatus("A");
                    return repository.save(a);
                }).then();
    }
}