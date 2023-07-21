package kioskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kioskDTO.FoodBuyDTO;
import kioskDTO.OracleUtility;

public class FoodBuyDAO {
	
	
	public int InsertCart(List<FoodBuyDTO> carts) {
		Connection con = OracleUtility.getConnection();
		String sql="INSERT INTO FOOD_BUY VALUES (FOODSEQ.nextval,?,?,?,?)";
		int count = 0;
		try {
			for(FoodBuyDTO f : carts) {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,f.getFoodCate());
				ps.setString(2,f.getFoodName());
				ps.setInt(3,f.getFoodPrice());
				ps.setInt(4,f.getFoodQuantity());
				count+=ps.executeUpdate();
			}
		} catch(SQLException e) {
			System.out.println("장바구니 상품 구매하기 예외 : " + e.getMessage());
			System.out.println("장바구니 상품 구매를 취소합니다.");
		}
	
		return count;
	}
}
