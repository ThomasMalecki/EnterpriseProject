package fact.it.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String description;
    private BigDecimal pricePerNight;
}
