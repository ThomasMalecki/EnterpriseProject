package fact.it.customerservice.controller;

import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // http://localhost:8082/api/customer?skuCode=tube6in&skuCode=beam10ft
    //@GetMapping
    //@ResponseStatus(HttpStatus.OK)
    //public List<CustomerResponse> isInStock (@RequestParam List<String> skuCode) {
        //return customerService.isInStock(skuCode);
    //}
}