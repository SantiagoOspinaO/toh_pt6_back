package co.com.sofka.usecase.deletehero;

import co.com.sofka.model.pet.gateways.HeroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteHeroUseCase {

    private final HeroRepository repository;

    public Mono<Void> deleteHero(String id) {
        return repository.delete(id);
    }
}
