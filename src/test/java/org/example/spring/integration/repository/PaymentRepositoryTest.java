package org.example.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.IntegrationTestBase;
import org.example.spring.integration.repository.annotation.IT;
import org.example.spring.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@RequiredArgsConstructor
class PaymentRepositoryTest extends IntegrationTestBase {

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