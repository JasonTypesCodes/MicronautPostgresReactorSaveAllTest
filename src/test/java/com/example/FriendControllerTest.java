package com.example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
@Testcontainers
class FriendControllerTest {


    @Container
    private static final PostgreSQLContainer DB_CONTAINER = new PostgreSQLContainer("postgres:12");

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testSaveOne() {
        HttpResponse<List<Friend>> r = client.toBlocking().exchange(HttpRequest.GET("/saveOne"), Argument.listOf(Friend.class));
        assertEquals(HttpStatus.OK, r.status());

        List<Friend> result = r.getBody().get();

        assertEquals(1, result.size());
        assertEquals("Shemp", result.get(0).getName());
    }

    @Test
    void testSaveAll() {
        HttpResponse<List<Friend>> r = client.toBlocking().exchange(HttpRequest.GET("/saveAll"), Argument.listOf(Friend.class));
        assertEquals(HttpStatus.OK, r.status());

        List<Friend> result = r.getBody().get();

        assertEquals(3, result.size());
        assertEquals("Larry", result.get(0).getName());
        assertEquals("Moe", result.get(0).getName());
        assertEquals("Curly", result.get(0).getName());
    }

}
