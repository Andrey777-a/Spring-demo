package org.example.spring.repository;

import org.example.spring.model.entity.Role;
import org.example.spring.model.entity.User;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.company where u.username like %:username%")
    List<User> findBy(String username);


    @Modifying(clearAutomatically = true)  //автоматически чистить persistence context
    @Query("update User u set u.role = :role where u.id in (:ids)")
    void updateRoleBy(Role role, Long ... ids);

}
