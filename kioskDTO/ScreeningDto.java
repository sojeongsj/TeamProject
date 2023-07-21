package kioskDTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ScreeningDto {
   
   public ScreeningDto(String MovieTitle, Date date) {
      this.MovieTitle = MovieTitle;
      this.ScreenDate = date;
   }
   
   
   public ScreeningDto(int ScreenNo) {
      this.ScreenNO = ScreenNo;
   }
   
   public ScreeningDto(String MovieTitle) {
	   this.MovieTitle = MovieTitle;
	   }
   
   public ScreeningDto(Date ScreenDate) {
	   this.ScreenDate = ScreenDate;
	   }


   int ScreenNO;         // 영화 상영 품번
   Date ScreenDate;      // 상영 시간
   String ScreenTheater;   // 상영관 
   String MovieTitle;      // 영화 제목
   
   public ScreeningDto(Date ScreenDate,String ScreenTheater,String MovieTitle) {
	   this.ScreenDate = ScreenDate;
	   this.ScreenTheater=ScreenTheater;
	   this.MovieTitle=MovieTitle;
	   }
   
   
public String getScreenTime() {
	// TODO Auto-generated method stub
	 SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	 String test = format1.format(ScreenDate);
	 //System.out.println(test);
	 String newString = new SimpleDateFormat("H:mm").format(ScreenDate); // 9:00
	return newString;
}
   
}
