package org.example.spring.config;


import org.example.spring.bpp.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Import(WebConfigApplication.class)
//@Profile("prod")
@Configuration
//@PropertySource("classpath:application123.properties")
//@ComponentScan(basePackages = "org.example.spring")
public class ApplicationConfig {


//    @Bean("pool")
//    public ConnectionPool connectionPool(@Value("${db.name}") String name,
//                                         @Value("${db.pool.size}") Integer pool){
//        return new ConnectionPool(name, pool);
//    }

}
