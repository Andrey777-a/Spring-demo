package org.example.spring.config;


import lombok.extern.slf4j.Slf4j;
import org.example.spring.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfigApplication.class)
//@Profile("prod")
@Configuration
//@PropertySource("classpath:application123.properties")
//@ComponentScan(basePackages = "org.example.spring")
public class ApplicationConfig {


    @Bean("pool")
    public ConnectionPool connectionPool(@Value("${db.name}") String name,
                                         @Value("${db.pool.size}") Integer pool){
        return new ConnectionPool(name, pool);
    }

}
