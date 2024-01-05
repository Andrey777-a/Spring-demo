package org.example.spring.repository;

import org.example.spring.model.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @EntityGraph(attributePaths = {"receiver"/*, "receiver.company"*/})
    @Query(value = "select p from Payment p",
    countQuery = "select count(p.id) from Payment p")
    Page<Payment> findAllBy(Pageable pageable);

}
