package fact.it.customerservice.repository;

import fact.it.customerservice.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmailIn(List<String> email);
}
