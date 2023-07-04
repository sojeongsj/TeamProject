package movie.kiosk;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MovieDto {
	
	String MovieNO;			// 영화 번호
	String MovieTitle;		// 영화 제목
	Date MovieDate;			// 개봉 날짜
	String MovieGrade;		// 영화 등급
	int MoviePrice;			// 영화 가격 
	

}
