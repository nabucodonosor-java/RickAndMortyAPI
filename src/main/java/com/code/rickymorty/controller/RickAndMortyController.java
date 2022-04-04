package com.code.rickymorty.controller;

import com.code.rickymorty.client.RickAndMortyClient;
import com.code.rickymorty.response.CharacterResponse;
import com.code.rickymorty.response.EpisodeResponse;
import com.code.rickymorty.response.ListOfEpisodesResponse;
import com.code.rickymorty.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findEpisodeById(id);
    }

    @GetMapping("/episode")
    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        return rickAndMortyClient.findAllEpisodes();
    }
}
