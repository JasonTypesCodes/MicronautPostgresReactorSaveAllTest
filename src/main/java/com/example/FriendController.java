package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller("/")
public class FriendController {

    private final FriendRepository friendRepository;

    public FriendController(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Get("/saveAll")
    public Flux<Friend> saveAll() {
        return friendRepository.saveAll(
                List.of(new Friend("Larry", new Date()),
                        new Friend("Moe", new Date()),
                        new Friend("Curly", new Date())));
    }

    @Get("/saveOne")
    public Flux<Friend> saveOne() {
        return Flux.from(friendRepository.save(new Friend("Shemp", new Date())));
    }
}
