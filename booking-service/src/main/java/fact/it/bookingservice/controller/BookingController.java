package fact.it.bookingservice.controller;

import fact.it.bookingservice.dto.BookingRequest;
import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String placeBooking(@RequestBody BookingRequest bookingRequest) {
        boolean result = bookingService.placeBooking(bookingRequest);
        return (result ? "booking successfully" : "Booking failed");
    }
    @DeleteMapping("/byNumber/{bookingNbr}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBookingByNumber(@PathVariable String bookingNbr) {
        boolean result = bookingService.deleteBookingByNumber(bookingNbr);
        return (result ? "Booking deleted successfully" : "Booking deletion failed");
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
