package co.com.sofka.api;

import co.com.sofka.model.pet.Hero;
import co.com.sofka.model.pet.gateways.HeroRepository;
import co.com.sofka.usecase.buscarhero.BuscarHeroUseCase;
import co.com.sofka.usecase.createhero.CreateHeroUseCase;
import co.com.sofka.usecase.deletehero.DeleteHeroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final HeroRepository repository;
    private final CreateHeroUseCase createHeroUseCase;
    private final BuscarHeroUseCase buscarHeroUseCase;
    private final DeleteHeroUseCase deleteGETUseCase;

    public Mono<ServerResponse> createHeroPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Hero.class)
                .flatMap(createHeroUseCase::createHero)
                .flatMap(hero -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createHeroUseCase.createHero(hero), Hero.class));
    }

    public Mono<ServerResponse> findHeroGETUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(buscarHeroUseCase.buscarHero(), Hero.class);
    }

    public Mono<ServerResponse> findByIdUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findById(id), Hero.class);
    }

    public Mono<ServerResponse> deleteGETUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteGETUseCase.deleteHero(id), Hero.class);
    }

    public Mono<ServerResponse> updateHeroPOSTUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return serverRequest.bodyToMono(Hero.class)
                .flatMap(hero -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(repository.update(id, hero), Hero.class));
    }
}
