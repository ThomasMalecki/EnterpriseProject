package fact.it.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private String bookingNbr;
    private Long hotelId;
    private Long customerId;
    private int amountOfNights;

}
