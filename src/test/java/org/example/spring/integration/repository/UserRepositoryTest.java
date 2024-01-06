package org.example.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.IntegrationTestBase;
import org.example.spring.model.entity.QUser;
import org.example.spring.model.entity.Role;
import org.example.spring.model.entity.User;
import org.example.spring.querydsl.QPredicate;
import org.example.spring.repository.CompanyRepository;
import org.example.spring.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.history.Revision;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;

//@IT
@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindUserQuerydsl() {

        var predicate = QPredicate.builder()
                .add("Ivan", QUser.user.firstname::containsIgnoreCase)
                .add("kate@gmail.com", QUser.user.username::containsIgnoreCase)
                .buildOr();
        var predicate2 = QPredicate.builder()
                .add("Ivan", QUser.user.firstname::containsIgnoreCase)
                .add("kate@gmail.com", QUser.user.username::containsIgnoreCase)
                .build();

        var all = userRepository.findAll(predicate);

        all.forEach(System.out::println);

    }


    //    @Commit
    @Test
//    @Disabled
    void checkUpdateAuditUsers() {

        var user = userRepository.findById(1L).get();

        user.setLastname("Sidorov3");
//        userRepository.save(user);
        userRepository.saveAndFlush(user);
        userRepository.findRevisions(1L);


        System.out.println();


    }

    @Commit
    @Test
    void checkCreatedAuditUsers() {

        var company = companyRepository.findById(1).get();

        var user = User.builder()
                .username("Test8@Gmail.com")
                .firstname("Ivan")
                .lastname("Sidorov")
                .birthdate(LocalDate.of(2001, 1, 30))
                .company(company)
                .role(Role.ADMIN)
                .build();

        userRepository.saveAndFlush(user);

        var byId = userRepository.findById(4L);
        Assertions.assertTrue(byId.isPresent());

    }

    @Test
    void checkModifiedAuditUsers() {

//        var ivan = userRepository.findById(1L).get();
//        ivan.setRole(Role.USER);
//        userRepository.flush();
//        System.out.println();

        var id = userRepository.findById(1L).get();
        id.setRole(Role.USER);


        userRepository.flush();

        var id2 = userRepository.findById(1L).get();

        Assertions.assertNotNull(id2.getModifiedAt());

        System.out.println();
    }


    @Test
    void checkUsers() {

        Pageable sortId = PageRequest.of(1, 2, Sort.by("id").descending());
//        var allBy = userRepository.findAllBy(sortId);

        var allBy = userRepository.findAll();
        System.out.println(allBy);
    }

    @Test
    void checkUsername() {
        var userList = userRepository.findBy("et");

        userList.forEach(System.out::println);
    }

    @Test
    void updateUserRole() {

        var id = userRepository.getById(5L);
        assertSame(Role.ADMIN, id.getRole());

        System.out.println(id);
        userRepository.updateRoleBy(Role.USER, 5L);

//        id.getCompany().getName();

        var theSameIvan = userRepository.getById(5L);
        assertSame(Role.USER, theSameIvan.getRole());


    }

}