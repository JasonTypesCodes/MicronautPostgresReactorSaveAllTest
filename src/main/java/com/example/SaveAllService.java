package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller("/")
public class SaveAllService {

    private final FriendRepository friendRepository;

    public SaveAllService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Get("/saveAll")
    public Flux<UUID> saveAll() {
        return friendRepository.saveAll(
                List.of(new Friend("Larry", new Date()),
                        new Friend("Moe", new Date()),
                        new Friend("Curly", new Date()))).map(Friend::getId);
    }
}
