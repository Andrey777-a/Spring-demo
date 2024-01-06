package org.example.spring.repository;

import org.example.spring.model.dto.UserFilter;
import org.example.spring.model.entity.User;

import java.util.List;

public interface QuerydslRepository {

    List<User> findAllByFilter(UserFilter filter);


}
