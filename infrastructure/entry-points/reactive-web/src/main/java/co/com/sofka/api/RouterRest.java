package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {

        return route(POST("/api/hero"), handler::createHeroPOSTUseCase)
                .and(route(GET("/api/hero"), handler::findHeroGETUseCase))
                .and(route(GET("/api/hero/{id}"), handler::findByIdUseCase))
                .and(route(DELETE("/api/hero/{id}"), handler::deleteGETUseCase))
                .and(route(POST("/api/hero/{id}"), handler::updateHeroPOSTUseCase));
    }
}
