package org.example.spring.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.annotation.IT;
import org.example.spring.model.entity.Role;
import org.example.spring.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Commit
    @Test
    void checkUpdateAuditUsers(){

        var id = userRepository.findById(24L).get();

        id.setLastname("Sidorov2");

        userRepository.save(id);
        System.out.println();


    }

    @Commit
    @Test
    void checkCreatedAuditUsers(){

        var company = companyRepository.findById(1).get();

        var user = User.builder()
                .username("Test5@Gmail.com")
                .firstname("Ivan")
                .lastname("Sidorov")
                .birthdate(LocalDate.of(2001, 1, 30))
                .company(company)
                .role(Role.ADMIN)
                .build();

        userRepository.saveAndFlush(user);


    }

    @Test
    void checkModifiedAuditUsers(){

//        var ivan = userRepository.findById(1L).get();
//        ivan.setRole(Role.USER);
//        userRepository.flush();
//        System.out.println();

        var id = userRepository.findById(1L).get();
        id.setRole(Role.USER);


        userRepository.flush();
        System.out.println();
    }


    @Test
    void checkUsers(){

        Pageable sortId = PageRequest.of(1, 2, Sort.by("id").descending());
//        var allBy = userRepository.findAllBy(sortId);

        var allBy = userRepository.findAll();
        System.out.println(allBy);
    }

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