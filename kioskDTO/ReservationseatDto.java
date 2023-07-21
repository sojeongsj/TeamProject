package kioskDTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Builder

public class ReservationseatDto {
	private String ReserNo;
	private Date ReserDate;
	private String ReserSeat;
	private String MovieTitle;
	private int CustNo;
	private int ScreenTheater;
	private int ScreenNo;
	
    @Override
    public String toString() {
        return "ReservationDto{" +
                "reserNo='" + ReserNo + '\'' +
                ", reserDate=" + ReserDate +
                ", reserSeat='" + ReserSeat + '\'' +
                ", movieTitle='" + MovieTitle + '\'' +
                ", custNo=" + CustNo +
                ", screenNo=" + ScreenNo +
                '}';
    }
}
