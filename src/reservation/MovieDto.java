package movie.kiosk;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter

public class MovieDto {
	
	public MovieDto(String MovieNO) {
		this.MovieNO = MovieNO;
	}
	
	String MovieNO;			// 영화 번호
	String MovieTitle;		// 영화 제목
	Timestamp MovieDate;	// 개봉 날짜
	String MovieGrade;		// 영화 등급
	int MoviePrice;			// 영화 가격 
	
	


}
