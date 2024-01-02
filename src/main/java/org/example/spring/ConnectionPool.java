package org.example.spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component("pool")
@RequiredArgsConstructor
@Slf4j
@Getter
public class ConnectionPool {

    private final String name;
    private final Integer poolSize;

    @PostConstruct
    private void init() {
//        System.out.println("init");
        log.info("init");
    }

    @PreDestroy
    private void destroy() {
//        System.out.println("destroy");
        log.info("destroy");
    }


}
