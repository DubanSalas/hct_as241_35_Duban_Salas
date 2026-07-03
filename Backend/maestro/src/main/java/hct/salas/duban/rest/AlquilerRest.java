package hct.salas.duban.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import hct.salas.duban.model.Alquiler;
import hct.salas.duban.service.AlquilerService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/alquiler")
@RequiredArgsConstructor
public class AlquilerRest {

    private final AlquilerService service;

    @GetMapping
    public Flux<Alquiler> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Alquiler> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Alquiler> create(@RequestBody Alquiler alquiler) {
        return service.save(alquiler);
    }

    @PutMapping("/{id}")
    public Mono<Alquiler> update(@PathVariable Long id, @RequestBody Alquiler alquiler) {
        return service.update(id, alquiler);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PatchMapping("/{id}/restore")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> restore(@PathVariable Long id) {
        return service.restore(id);
    }
}