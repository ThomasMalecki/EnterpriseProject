package fact.it.customerservice;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import fact.it.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceApplicationTests {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test
	public void updateCustomer_CustomerExists_ReturnsTrue() {
		// Arrange
		Long customerId = 1L;
		CustomerRequest customerRequest = new CustomerRequest("bob", "marly","bob@malry.be", "0545842446");
		Customer existingCustomer = new Customer(customerId,"bob", "marly","bob@malry.be", "0545842446");
		when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

		// Act
		boolean result = customerService.updateCustomer(customerId, customerRequest);

		// Assert
		assertTrue(result);
		verify(customerRepository, times(1)).findById(customerId);
		verify(customerRepository, times(1)).save(existingCustomer);
	}

	@Test
	public void updateCustomer_CustomerDoesNotExist_ReturnsFalse() {
		// Arrange
		Long customerId = 1L;
		CustomerRequest customerRequest = new CustomerRequest("bob", "marly","bob@malry.be", "0545842446");
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

		// Act
		boolean result = customerService.updateCustomer(customerId, customerRequest);

		// Assert
		assertFalse(result);
		verify(customerRepository, times(1)).findById(customerId);
		verify(customerRepository, never()).save(any());
	}

	@Test
	public void getAllCustomers_ReturnsListOfCustomerResponses() {
		// Arrange
		Customer customer1 = new Customer(1L,"bob", "marly","bob@malry.be", "0545842446");
		Customer customer2 = new Customer(2L,"bob2", "marly","bob@malry.be", "0545842446");
		Customer customer3 = new Customer(3L,"bob3", "marly","bob@malry.be", "0545842446");

		List<Customer> customers = Arrays.asList(customer1,customer2,customer3);
		when(customerRepository.findAll()).thenReturn(customers);

		// Act
		List<CustomerResponse> result = customerService.getAllCustomers();

		// Assert
		assertEquals(customers.size(), result.size());
		// Add more specific assertions based on your data and mapping logic
	}
}


