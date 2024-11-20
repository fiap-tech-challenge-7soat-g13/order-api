package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer")
interface CustomerApiFeignClient {

    @GetMapping("/{customerId}")
    GetCustomerResponse getCustomer(@PathVariable Long customerId);

}
