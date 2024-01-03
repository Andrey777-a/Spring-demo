package org.example.spring.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.annotation.IT;
import org.example.spring.model.entity.Role;
import org.example.spring.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;


    @Test
    void checkUsername(){
        var userList = userRepository.findBy("et");

        userList.forEach(System.out::println);
    }

    @Test
    void updateUserRole(){

        var id = userRepository.getById(5L);
        assertSame(Role.ADMIN, id.getRole());

        System.out.println(id);
        userRepository.updateRoleBy(Role.USER, 5L);

        id.getCompany().getName();

        var theSameIvan = userRepository.getById(5L);
        assertSame(Role.USER, theSameIvan.getRole());



    }

}