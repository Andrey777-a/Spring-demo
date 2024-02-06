package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.IntegrationTestBase;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.model.dto.UserReadDto;
import org.example.spring.model.entity.Role;
import org.example.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;


    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(6);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                LocalDate.now(),
                "Test",
                "Test2",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthdate(), actualResult.getBirthdate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "Test",
                LocalDate.now(),
                "Test",
                "Test2",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthdate(), user.getBirthdate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }



}
