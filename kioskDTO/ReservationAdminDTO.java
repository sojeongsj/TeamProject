package kioskDTO;

import kioskDTO.ReservationDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReservationAdminDTO extends ReservationDto {
    private int count;
    private int moviePrice;

    public ReservationAdminDTO(String movieTitle, int count, int moviePrice) {
        
    	super(movieTitle);
        this.count = count;
        this.moviePrice = moviePrice;
    }
}