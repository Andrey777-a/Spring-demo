package org.example.spring.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "chat")
@Entity
public class Chat extends AbstractEntity<Long> {


    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private List<UserChat> userChats = new ArrayList<>();

}
