package fact.it.hotelservice.service;
import fact.it.hotelservice.dto.HotelRequest;
import fact.it.hotelservice.dto.HotelResponse;
import fact.it.hotelservice.model.Hotel;
import fact.it.hotelservice.repository.HotelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    @PostConstruct
    public void loadData() {
        if(hotelRepository.count() <= 0){
            Hotel hotel = Hotel.builder()
                    .name("Lidner Hotel antwerp")
                    .location("Belgium")
                    .pricePerNight(BigDecimal.valueOf(120))
                    .description("Located steps from the historic Belfry of Antwerp, an iconic bell tower dating back to the 13th-century, Martin's Brugge features a lobby with a fireplace, ticket/tour assistance, and a garden. Decorated in warm tones, the unfussy guest rooms come with air conditioning, a flat-screen HDTV, in-room safe, minibar, and an en-suite bathroom. Selected units add a seating area and king-size bed.")
                    .build();

            Hotel hotel1 = Hotel.builder()
                    .name("Radisson Collection Grand Place Brussels")
                    .location("Belgium")
                    .pricePerNight(BigDecimal.valueOf(179.99))
                    .description("Within a 5-minute walk from Brussels-Central Train Station and the Grand Place, the NH Grand Place Arenberg offers upscale accommodations at an affordable price in an ideal location for both business and leisure travellers to the city.")
                    .build();

            hotelRepository.save(hotel);
            hotelRepository.save(hotel1);
        }
    }

    public boolean createHotel(HotelRequest hotelRequest){
        Hotel hotel = Hotel.builder()
                .name(hotelRequest.getName())
                .location(hotelRequest.getLocation())
                .description(hotelRequest.getDescription())
                .pricePerNight(hotelRequest.getPricePerNight())
                .build();
        try {
            hotelRepository.save(hotel);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(this::mapToHotelResponse).toList();
    }

    public List<HotelResponse> getAllHotelsByLocation(List<String> location) {
        List<Hotel> hotels = hotelRepository.findByLocationIn(location);

        return hotels.stream().map(this::mapToHotelResponse).toList();
    }

    private HotelResponse mapToHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .pricePerNight(hotel.getPricePerNight())
                .build();
    }

}