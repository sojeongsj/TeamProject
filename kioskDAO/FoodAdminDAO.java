package kioskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.FoodAdminDTO;
import kioskDTO.FoodDto1;
import kioskDTO.OracleUtility;

public class FoodAdminDAO {
	// 음식 카테고리별로 보여주기
	// private static FoodDao dao = new FoodDao();

	// ReservationDao 유형의 "dao"라는 개인 정적 변수를 선언합니다.
	// FoodDao() {
	// }
	// public static FoodDao getDao() {
	// getDao()메소드를 사용해 dao에 저장된 ReservationDao 객체를 얻을수 있다
	// return dao;
	// }
	public static FoodAdminDAO dao = new FoodAdminDAO();

	public FoodAdminDAO() {
	}

	public static FoodAdminDAO getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public static List<FoodAdminDTO> FoodAdmin() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "SELECT FOODBUNO,FOODCATE,FOODNAME,FOODPRICE,FOODQUANTITY, FOODPRICE * FOODQUANTITY AS price FROM FOOD_BUY";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<FoodAdminDTO> foods = new ArrayList<>();
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int FOODBUNO = rs.getInt("FOODBUNO");
			String FOODCATE = rs.getString("FOODCATE");
			String FOODNAME = rs.getString("FOODNAME");
			int FOODPRICE = rs.getInt("FOODPRICE");
			int FOODQUANTITY = rs.getInt("FOODQUANTITY");
			int price = rs.getInt(6);

			FoodAdminDTO food = new FoodAdminDTO(FOODBUNO, FOODCATE, FOODNAME, FOODPRICE, FOODQUANTITY, price);
			foods.add(food);
		} // while 종료
		return foods;
	}

	// 관리자 기능 insert
	public int insert(FoodDto1 fd) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "insert into food values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, fd.getFOODBUNO());
		ps.setString(2, fd.getFOODCATE());
		ps.setString(3, fd.getFOODNAME());
		ps.setInt(4, fd.getFOODPRICE());

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}

	// 관리자 기능 update 테스트
	public int update(FoodDto1 food) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "update food set FoodCate = ?, FoodName = ?, FoodPrice = ? where FoodNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, food.getFOODCATE());
		ps.setString(2, food.getFOODNAME());
		ps.setInt(3, food.getFOODPRICE());
		ps.setInt(4, food.getFOODBUNO());

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;

	}

	// 관리자 기능 delete 테스트
	public int delete(int foodno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "delete from food where FoodNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, foodno);
		int result = ps.executeUpdate();
		ps.close();
		connection.close();

		return result;

	}
	
	

	// 관리자 기능 selectone 테스트
	public List<FoodDto1> Foodselectone(int FoodNo) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from food where FoodNo = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, FoodNo);

		List<FoodDto1> results = new ArrayList<>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new FoodDto1(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		ps.close();
		conn.close();
		return results;

	}

	// 관리자 기능 selectall 테스트 매출확인용
	public List<FoodAdminDTO> FoodselectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from food_buy";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<FoodAdminDTO> results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new FoodAdminDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getInt(6)));
		}

		ps.close();
		connection.close();
		return results;
	}

	// 키오스크 손님
	public List<FoodDto1> FoodselectAll1() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from food";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<FoodDto1> results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			results.add(new FoodDto1(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}

		ps.close();
		connection.close();
		return results;
	}

}
