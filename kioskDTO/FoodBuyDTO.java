package kioskDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FoodBuyDTO {
	private int FoodBuNo;
	private String FoodCate;
	private String FoodName;
	private int FoodPrice;
	private int FoodQuantity;
	
	
	public FoodBuyDTO(String FoodCate,String FoodName, int FoodPrice,int FoodQuantity) {
		this.FoodCate=FoodCate;
		this.FoodName=FoodName;
		this.FoodPrice=FoodPrice;
		this.FoodQuantity=FoodQuantity;
	}
	
}
