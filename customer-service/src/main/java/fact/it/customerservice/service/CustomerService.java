package fact.it.customerservice.service;

import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            customer.setFirstName("Bo");
            customer.setLastName("Dessent");
            customer.setEmail("bo.dessent@mail.be");
            customer.setPhone("+32 489 54 86");

            customerRepository.save(customer);
            customerRepository.save(customer1);
        }
    }
}
