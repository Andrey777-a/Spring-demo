package org.example.spring.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserFilter;
import org.example.spring.model.entity.User;
import org.example.spring.querydsl.QPredicate;
import java.util.List;

import static org.example.spring.model.entity.QUser.user;

@RequiredArgsConstructor
public class QuerydslRepositoryImpl implements QuerydslRepository{

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {

        var predicate = QPredicate.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
