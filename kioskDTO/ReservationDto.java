package kioskDTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder

public class ReservationDto {
   
   private String ReserNo;      //예약번호 
   private Date ReserDate;      //예약날짜
   private String ReserSeat;   //예약좌석
   private String MovieTitle;      //영화이름
   private int CustNo;         //손님번호
   private int ScreenTheater;
   private int ScreenNo;      //영화상영품번
   
   
   public ReservationDto(String movieTitle) {
       this.MovieTitle = movieTitle;
   }
    public ReservationDto() {
           // 기본 생성자
       }
    }
