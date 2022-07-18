package co.com.sofka.usecase.buscarhero;

import co.com.sofka.model.pet.Hero;
import co.com.sofka.model.pet.gateways.HeroRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class BuscarHeroUseCase {
    private final HeroRepository repository;

    public Flux<Hero> buscarHero() {
        return repository.findAll();
    }
}
