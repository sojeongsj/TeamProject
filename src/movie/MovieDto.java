package movie;

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
		this.movieNO = MovieNO;
	}
	    private String movieNO;
	    private String movieTitle;
	    private String movieDate;
	    private String movieGrade;
	    private int moviePrice;
		


}
