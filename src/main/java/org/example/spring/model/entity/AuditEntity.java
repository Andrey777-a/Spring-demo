package org.example.spring.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity <T extends Serializable> extends AbstractEntity<T> {

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant modifiedAt;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;


}
