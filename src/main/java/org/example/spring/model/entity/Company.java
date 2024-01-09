package org.example.spring.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.HashMap;
import java.util.Map;

//@NamedQuery(name = "Company.findByName",
//        query = "select c from Company c where lower(c.name) = lower(:name)")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "company")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Entity
public class Company extends AbstractEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    public Company(String name) {
        this.name = name;
    }

    @NotAudited
    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales",
    joinColumns = @JoinColumn(name = "company_id")
    )
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> companyLocales = new HashMap<>();

}
