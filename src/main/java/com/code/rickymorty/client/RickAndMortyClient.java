package com.code.rickymorty.client;

import com.code.rickymorty.response.CharacterResponse;
import com.code.rickymorty.response.EpisodeResponse;
import com.code.rickymorty.response.ListOfEpisodesResponse;
import com.code.rickymorty.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findCharacterById(String id) {
        log.info("buscando personagem por id: [{}]", id);
        return webClient.get()
                .uri("/character/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Personagem não encontrado!")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findLocationById(String id) {
        log.info("buscando localização por id: [{}]", id);
        return webClient.get()
                .uri("/location/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Localização não encontrado!")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findEpisodeById(String id) {
        log.info("buscando episódio por id: [{}]", id);
        return webClient.get()
                .uri("/episode/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Episódio não encontrado!")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> findAllEpisodes() {
        return webClient.get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Episódio não encontrado!")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}
