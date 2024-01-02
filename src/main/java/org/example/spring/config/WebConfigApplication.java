package org.example.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("web")
@Configuration
public class WebConfigApplication {
}
