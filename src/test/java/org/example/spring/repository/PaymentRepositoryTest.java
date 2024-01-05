package org.example.spring.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.annotation.IT;
import org.example.spring.model.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class PaymentRepositoryTest {

    private final PaymentRepository paymentRepository;

    @Test
    void checkPayment(){

        var pageable = PageRequest.of(0, 3, Sort.by("id"));

        var page = paymentRepository.findAllBy(pageable);

//        page.forEach(pay -> System.out.println(pay.getAmount()));

        while (page.hasNext()){
            page = paymentRepository.findAllBy(page.nextPageable());
            page.forEach(pay -> System.out.println(pay.getReceiver().getCompany()));
        }


    }


}