package food;

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
public class FoodDTO {
	
	int fOODBUNO;
	String fOODCATE;
	String fOODNAME;
	int fOODPRICE;
	int FOODQUANTITY;
	int price;
}
