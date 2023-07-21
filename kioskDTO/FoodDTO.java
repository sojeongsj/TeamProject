package kioskDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class FoodDTO {
	int foodNo;
	String foodCate;
	String foodName;
	int foodPrice;
}
