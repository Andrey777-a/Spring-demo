package org.example.spring;

import org.example.spring.integration.repository.annotation.IT;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@Sql(scripts = {
        "classpath:sql/data.sql"
})
@IT
public abstract class IntegrationTestBase {

    private static PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres");


    @BeforeAll
    static void beforeAll(){
        container.start();
    }

//    @AfterAll
//    static void afterAll(){
//        container.stop();
//    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}
