package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.microsoft.playwright.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiTest {

    // Shared between all tests in this class.
    private Playwright playwright;
    private APIRequestContext request;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createAPIRequestContext() {
        Map<String, String> headers = new HashMap<>();

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL("http://localhost:8081")
                .setExtraHTTPHeaders(headers));
    }

    @BeforeAll
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
    }

    void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @AfterAll
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright();
    }

    public class Song {
        public int id;
        public String title;
        public String artist;
        public String genre;
        public String album;
        public String albumImageUrl;
        public String youtubeId;
        public String lyrics;
        public String tab;
        public String createdAt;
        public Optional<String> updatedAt;
    };

    @Test
    void retrieveTheSongsFromTheAPI() throws JsonMappingException, JsonProcessingException {
        APIResponse issues = request.get("/songs");
        assertTrue(issues.ok());

        ObjectMapper mapper = new ObjectMapper(); // todo: move to before all
        mapper.registerModule(new Jdk8Module());

        List<Song> value = mapper.readValue(issues.text(), new TypeReference<List<Song>>() { } );
        Song expected = new Song();
        expected.id = 1;

        assertTrue(value.stream().anyMatch(s -> s.id == expected.id));
    }

}