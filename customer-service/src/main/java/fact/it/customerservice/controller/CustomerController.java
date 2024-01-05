package fact.it.customerservice.controller;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerRequest customerRequest
    ) {
        boolean result = customerService.updateCustomer(customerId, customerRequest);
        return result ? "Customer updated successfully" : "Customer update failed";
    }

    @GetMapping("/by-id/{customerId}")
    public Optional<Customer> checkCustomerExistsById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }
}