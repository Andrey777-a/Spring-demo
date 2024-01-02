package org.example.spring.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity<Long> {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();


}
