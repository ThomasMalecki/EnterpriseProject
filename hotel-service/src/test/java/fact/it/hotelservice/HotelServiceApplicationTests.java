package fact.it.hotelservice;

import fact.it.hotelservice.dto.HotelRequest;
import fact.it.hotelservice.dto.HotelResponse;
import fact.it.hotelservice.model.Hotel;
import fact.it.hotelservice.repository.HotelRepository;
import fact.it.hotelservice.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceApplicationTests {
	@InjectMocks
	private HotelService hotelService;

	@Mock
	private HotelRepository hotelRepository; // Mock database repository

	@Test
	public void createHotel_Success() {
		HotelRequest HotelRequest = new HotelRequest("Big hotel", "Belgium", "Grandplace hotel", BigDecimal.valueOf(120));

		when(hotelRepository.save(any())).thenReturn(new Hotel());

		boolean result = hotelService.createHotel(HotelRequest);

		assert result; // Check that booking was successful

		verify(hotelRepository, times(1)).save(any()); // Verify that save method was called
	}
	@Test
	public void getAllHotels_ReturnsListOfHotels() throws Exception {
		//ARRANGE
		Hotel hotel1 = new Hotel("1","Big hotel", "Belgium", "Grandplace hotel", BigDecimal.valueOf(120));
		Hotel hotel2 = new Hotel("2","small hotel", "Belgium", "smallplace hotel", BigDecimal.valueOf(100));

		when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotel1, hotel2));
		//ACT
		List<HotelResponse> result = hotelService.getAllHotels();

		//ASSERT
		assertEquals(2, result.size());

		verify(hotelRepository, times(1)).findAll();
	}
}
