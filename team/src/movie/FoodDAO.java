package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
	//음식 카테고리별로 보여주기
	public List<FoodDTO> getFoodsByCategory(String foodCate) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FoodDTO> foods = new ArrayList<>();
		try {
			conn = OracleUtility.getConnection();
			String sql = "SELECT * FROM FOOD WHERE FOODCATE = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, foodCate);
			rs = ps.executeQuery();
			  while (rs.next()) {
	                int foodNo = rs.getInt("foodNo");
	                String foodName = rs.getString("foodName");
	                int foodPrice = rs.getInt("foodPrice");
	                
	                FoodDTO food = new FoodDTO(foodNo, foodCate,foodName,foodPrice);
	                foods.add(food);
	            }
	        } finally {
	            if (rs != null) {
	                rs.close();
	            }
	            
	            if (ps != null) {
	                ps.close();
	            }
	            
	            if (conn != null) {
	                conn.close();
	            }
	        }
	        
	        return foods;
	    }
	}