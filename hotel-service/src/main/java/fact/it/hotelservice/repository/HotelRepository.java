package fact.it.hotelservice.repository;

import fact.it.hotelservice.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findByLocationIn(List<String> location);
}
