package fact.it.customerservice.service;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @PostConstruct
    public void loadData() {
        if(customerRepository.count() <= 0){
            Customer customer = new Customer();
            customer.setFirstName("Thomas");
            customer.setLastName("Malecki");
            customer.setEmail("thomas.malecki@mail.be");
            customer.setPhone("+32 489 54 86");

            Customer customer1 = new Customer();
            customer1.setFirstName("Bo");
            customer1.setLastName("Dessent");
            customer1.setEmail("bo.dessent@mail.be");
            customer1.setPhone("+32 489 54 86");

            Customer customer2 = new Customer();
            customer2.setFirstName("Bert");
            customer2.setLastName("Dessent");
            customer2.setEmail("bo.dessent@mail.be");
            customer2.setPhone("+32 489 54 86");

            customerRepository.save(customer);
            customerRepository.save(customer1);
            customerRepository.save(customer2);
        }
    }

    public boolean updateCustomer(Long customerId, CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {

            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setFirstName(customerRequest.getFirstName());
            existingCustomer.setLastName(customerRequest.getLastName());
            existingCustomer.setEmail(customerRequest.getEmail());
            existingCustomer.setPhone(customerRequest.getPhone());

            customerRepository.save(existingCustomer);
            return true;
        } else {
            return false; // Customer with the specified ID not found
        }
    }
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> new CustomerResponse(
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        customer.getPhone()
                ))
                .collect(Collectors.toList());
    }

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
