package org.example.spring.bpp;

import org.example.spring.ConnectionPool;
import org.springframework.stereotype.Component;

@Component
public class InjectExample {

    @Inject
    ConnectionPool connectionPool;

}
