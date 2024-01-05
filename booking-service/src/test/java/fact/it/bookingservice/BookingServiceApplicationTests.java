package fact.it.bookingservice;
import fact.it.bookingservice.dto.BookingRequest;
import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.dto.CustomerResponse;
import fact.it.bookingservice.model.Booking;
import fact.it.bookingservice.repository.BookingRepository;
import fact.it.bookingservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceApplicationTests {
	@InjectMocks
	private BookingService bookingService;

	@Mock
	private WebClient webClient; // Mock external dependencies


	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpec;

	@Mock
	private WebClient.ResponseSpec responseSpec;

	@Mock
	private BookingRepository bookingRepository; // Mock database repository



	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(bookingService, "customerServiceBaseUrl", "http://localhost:8082");
	}

	@Test
	public void placeBooking_ValidRequest_ReturnsTrue() throws Exception {
		BookingRequest bookingRequest = new BookingRequest(1L, 2L, 3);

		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setEmail("test@test.be");
		customerResponse.setFirstName("test");
		customerResponse.setLastName("testlast");
		customerResponse.setPhone("44848454989");

		String bookingNbr = UUID.randomUUID().toString();
		Booking booking = new Booking(1L, bookingNbr, 1L,2L, 3);

		when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString(),  any(Function.class))).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(CustomerResponse[].class)).thenReturn(Mono.just(new CustomerResponse[]{customerResponse}));

		// Act
		boolean result = bookingService.placeBooking(bookingRequest);

		// Assert
		assertTrue(result);

		verify(bookingRepository, times(1)).save(any(Booking.class));
	}

	@Test
	public void deleteBookingByNumber_ExistingBooking_ReturnsTrue() throws Exception {

		String bookingNbr = UUID.randomUUID().toString();
		Booking booking = new Booking(1L, bookingNbr, 1L,1L, 10);

		when(bookingRepository.findByBookingNbr(bookingNbr)).thenReturn(booking);

		boolean result = bookingService.deleteBookingByNumber(bookingNbr);

		assert result; // Check that deletion was successful

		verify(bookingRepository, times(1)).delete(booking); // Verify that delete method was called
	}

	@Test
	public void getAllBookings_ReturnsListOfBookings() throws Exception {
		//ARRANGE
		Booking booking1 = new Booking(1L, UUID.randomUUID().toString(), 1L,1L, 10);
		Booking booking2 = new Booking(1L, UUID.randomUUID().toString(), 1L,1L, 10);

		when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

		//ACT
		List<BookingResponse> result = bookingService.getAllBookings();

		//ASSERT
		assertEquals(2, result.size());

		verify(bookingRepository, times(1)).findAll();
	}
}