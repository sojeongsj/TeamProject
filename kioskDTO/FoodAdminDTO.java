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
public class FoodAdminDTO {
	
	// 관리자 기능 용 DTO 매출계산 fooddto
	
	int fOODBUNO;
	String fOODCATE;
	String fOODNAME;
	int fOODPRICE;
	int FOODQUANTITY;
	int price;
}