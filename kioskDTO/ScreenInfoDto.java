package kioskDTO;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor

public class ScreenInfoDto {
    private int moviePrice;
    private int screenNo;
    private String screenTheater;
    private String movieTitle;
    
    public ScreenInfoDto(int moviePrice, int screenNo, String screenTheater, String movieTitle) {
        this.moviePrice = moviePrice;
        this.screenNo = screenNo;
        this.screenTheater = screenTheater;
        this.movieTitle = movieTitle;
    }
    
    // getter와 setter 메서드 생략
    
    // 필요에 따라 toString(), equals(), hashCode() 등의 메서드를 추가할 수 있습니다.
}