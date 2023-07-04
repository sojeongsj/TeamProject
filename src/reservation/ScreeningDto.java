package movie.kiosk;

import java.sql.Date;

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
	
	public ScreeningDto(String MovieTitle, Date ScreenDate) {
		this.MovieTitle = MovieTitle;
		this.ScreenDate = ScreenDate;
	}
	int ScreenNO;			// 영화 상영 품번
	String MovieNO;			// 영화 품번
	String MovieTitle;		// 영화 제목
	Date ScreenDate;		// 상영 시간
	String ScreenTheater;	// 상영관 
	
}
