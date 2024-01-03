package fact.it.bookingservice.service;
import fact.it.bookingservice.dto.*;
import fact.it.bookingservice.model.Booking;
import fact.it.bookingservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;
    private final WebClient webClient;

    @Value("${productservice.baseurl}")
    private String hotelServiceBaseUrl;

    @Value("${inventoryservice.baseurl}")
    private String customerServiceBaseUrl;

    public boolean placeBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setBookingNbr("2");
        booking.setCustomerId(bookingRequest.getCustomerId());
        booking.setHotelId(bookingRequest.getHotelId());
        booking.setAmountOfNights(bookingRequest.getAmountOfNights());
        try {
            bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(booking -> new BookingResponse(
                        booking.getBookingNbr(),
                        booking.getCustomerId(),
                        booking.getHotelId(),
                        booking.getAmountOfNights()
                ))
                .collect(Collectors.toList());
    }

}
