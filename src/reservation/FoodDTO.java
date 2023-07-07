package movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
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
