package kioskDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class FoodDto1 {
	
	//관리자 기능 fooddto1 정보 수정용 
	
	public FoodDto1(int fooDBUNO) {
		this.fOODBUNO = fooDBUNO;
	}
	int fOODBUNO;
	String fOODCATE;
	String fOODNAME;
	int fOODPRICE;
	
}