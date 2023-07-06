package movie.kiosk;

import java.sql.Date;
import java.sql.Timestamp;

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
	
	public ScreeningDto(String MovieTitle, Timestamp ScreenDate) {
		this.MovieTitle = MovieTitle;
		this.ScreenDate = ScreenDate;
	}
	
	
	public ScreeningDto(int ScreenNo) {
		this.ScreenNO = ScreenNo;
	}


	int ScreenNO;			// 영화 상영 품번
	Timestamp ScreenDate;		// 상영 시간
	String ScreenTheater;	// 상영관 
	String MovieTitle;		// 영화 제목
	
}
