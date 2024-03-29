package org.example.spring.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractEntity <E extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private E id;

}
