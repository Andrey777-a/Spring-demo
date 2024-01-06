package org.example.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        var context = SpringApplication.run(Main.class);

//        var bean = context.getBean("pool", ConnectionPool.class);
//        System.out.println(bean.getName() + " " + bean.getPoolSize());


//        Objects.equals();

//        var definitionNames = Arrays.stream(context.getBeanDefinitionNames());
//        definitionNames.forEach(System.out::println);

    }
    }
