package io.github.mrspock182.rest.application.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MongodbTestContainerConfig {
    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017);

    @DynamicPropertySource
    public static void mongodbProperties(DynamicPropertyRegistry registry) {
        String uri = "mongodb://" + mongoDBContainer.getHost() +
                ":" + mongoDBContainer.getFirstMappedPort() +
                "/rest?maxPoolSize=9999&serverSelectionTimeoutMS=15000&connectTimeoutMS=15000";

//        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
//        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
//        registry.add("spring.data.mongodb.database", () -> "rest");

        registry.add("spring.data.mongodb.uri", () -> uri);
        registry.add("spring.data.mongodb.auto-index-creation", () -> true);
    }

    @BeforeAll
    public static void beforeAll() {
        mongoDBContainer.start();
    }
}