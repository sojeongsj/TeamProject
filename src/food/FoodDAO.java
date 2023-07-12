package food;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.FoodDTO.FoodDTOBuilder;
import pack_CGV_Admin.Food_Admin_Panel;
import pack_CGV_Admin.OracleUtility;
import reservation.ReservationDao;
import reservation.ReservationDto;

public class FoodDAO {
	// 음식 카테고리별로 보여주기
	private static FoodDAO dao = new FoodDAO();

	// ReservationDao 유형의 "dao"라는 개인 정적 변수를 선언합니다.
	private FoodDAO() {
	}
	public static FoodDAO getDao() { 
		//getDao()메소드를 사용해 dao에 저장된 ReservationDao 객체를 얻을수 있다
		return dao;
		}

	/*
	 * public List<FoodDTO> getFoodsByCategory(String foodCate) throws SQLException
	 * { Connection conn = null; PreparedStatement ps = null; ResultSet rs = null;
	 * List<FoodDTO> foods = new ArrayList<>(); try { conn =
	 * OracleUtility.getConnection(); String sql =
	 * "SELECT * FROM FOOD WHERE FOODCATE = ?"; ps = conn.prepareStatement(sql);
	 * ps.setString(1, foodCate); rs = ps.executeQuery(); while (rs.next()) { int
	 * foodNo = rs.getInt("foodNo"); String foodName = rs.getString("foodName"); int
	 * foodPrice = rs.getInt("foodPrice");
	 * 
	 * FoodDTO food = new FoodDTO(foodNo, foodCate, foodName, foodPrice);
	 * foods.add(food); } } finally { if (rs != null) { rs.close(); }
	 * 
	 * if (ps != null) { ps.close(); }
	 * 
	 * if (conn != null) { conn.close(); } }
	 * 
	 * return foods; }
	 */
	/*
	 * public int update(FoodDTO fdto) throws SQLException { Connection conn =
	 * OracleUtility.getConnection(); String sql =
	 * "update food set foodcate = ?, foodname = ?, foodprice = ? where foodno = ?";
	 * PreparedStatement ps = conn.prepareStatement(sql); ps.setString(1,
	 * fdto.foodCate); ps.setString(2, fdto.foodName); ps.setInt(3, fdto.foodPrice);
	 * ps.setInt(4, fdto.foodPrice); int result = ps.executeUpdate();
	 * 
	 * ps.close(); conn.close(); return result; }
	 */
	public List<FoodDTO> FoodAdmin() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "SELECT FOODBUNO,FOODCATE,FOODNAME,FOODPRICE,FOODQUANTITY, FOODPRICE * FOODQUANTITY AS price FROM FOOD_BUY";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<FoodDTO> foods = new ArrayList<>();
		ResultSet rs = ps.executeQuery();

		    while (rs.next()){
		        int FOODBUNO = rs.getInt("FOODBUNO");
		        String FOODCATE = rs.getString("FOODCATE");
		        String FOODNAME = rs.getString("FOODNAME");
		        int FOODPRICE = rs.getInt("FOODPRICE");
		        int FOODQUANTITY = rs.getInt("FOODQUANTITY");
		        int price = rs.getInt(6);

		        FoodDTO food = new FoodDTO(FOODBUNO, FOODCATE, FOODNAME, FOODPRICE, FOODQUANTITY,price);
		        foods.add(food);
	}// while 종료
		    return foods;
	}

	
}	

	
	
	
	
	