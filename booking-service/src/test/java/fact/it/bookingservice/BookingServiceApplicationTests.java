package fact.it.bookingservice;
import fact.it.bookingservice.dto.BookingRequest;
import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.model.Booking;
import fact.it.bookingservice.repository.BookingRepository;
import fact.it.bookingservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookingService bookingService;

	@MockBean
	private WebClient webClient; // Mock external dependencies

	@MockBean
	private BookingRepository bookingRepository; // Mock database repository

	@Test
	public void placeBooking_ValidRequest_ReturnsTrue() throws Exception {
		BookingRequest bookingRequest = new BookingRequest(1L, 2L, 3);

		when(bookingRepository.save(any())).thenReturn(new Booking());

		boolean result = bookingService.placeBooking(bookingRequest);

		assert result; // Check that booking was successful

		verify(bookingRepository, times(1)).save(any()); // Verify that save method was called
	}

	@Test
	public void deleteBookingByNumber_ExistingBooking_ReturnsTrue() throws Exception {
		String bookingNbr = UUID.randomUUID().toString();
		Booking booking = new Booking();
		when(bookingRepository.findByBookingNbr(bookingNbr)).thenReturn(booking);

		boolean result = bookingService.deleteBookingByNumber(bookingNbr);

		assert result; // Check that deletion was successful

		verify(bookingRepository, times(1)).delete(booking); // Verify that delete method was called
	}

	@Test
	public void getAllBookings_ReturnsListOfBookings() throws Exception {
		List<Booking> bookings = Arrays.asList(
				new Booking(),
				new Booking()
		);
		when(bookingRepository.findAll()).thenReturn(bookings);

		List<BookingResponse> response = bookingService.getAllBookings();

		assert response.size() == 2; // Check that the response has the expected size

		// Add additional assertions based on your business logic
	}
}