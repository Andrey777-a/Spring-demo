package org.example.spring.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NamedQuery(name = "Company.findByName",
        query = "select c from Company c where lower(c.name) = lower(:name)")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "company")
@Entity
public class Company extends AbstractEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales",
    joinColumns = @JoinColumn(name = "company_id")
    )
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> companyLocales = new HashMap<>();

}
