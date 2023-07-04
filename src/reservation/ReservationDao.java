package reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReservationDao {
/*	
	2.좌석 예매
	3.예매하기
	4.예매번호 or 전화번호로 예매내역 검색(예매티켓출력)
	5.예매취소
*/
	private static ReservationDao dao = new ReservationDao();
	//ReservationDao 유형의 "dao"라는 개인 정적 변수를 선언합니다.
	private ReservationDao() {}	
	public static ReservationDao getDao() { 
	//getDao()메소드를 사용해 dao에 저장된 ReservationDao 객체를 얻을수 있다
	return dao;
	}

	        public int Update(String reserseat) throws SQLException { //2.좌석예매 메소드
	    		Connection connection = OracleUtility.getConnection();
	    		String sql = "UPDATE reservation SET ReserSeat = ?";
	    		PreparedStatement ps = connection.prepareStatement(sql);
	    		
	    		ps.setString(1, reserseat);		//메소드 인자(매개변수)값을 sql 쿼리에 전달
	    		int result = ps.executeUpdate();
	    		
	    		ps.close();
	    		connection.close();
	    		
	    		return result;
	    	}
	
	
	public int insert(ReservationDto reser) throws SQLException {//3.예매 메소드
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "INSERT INTO reservation VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, reser.getReserNo());
		ps.setDate(2, reser.getReserDate());
		ps.setString(3, reser.getReserSeat());
		ps.setString(4, reser.getMovieNo());
		ps.setInt(5, reser.getCustNo());
		ps.setInt(6, reser.getScreenNo());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result;
	}
	public ReservationDto ReservationOne(int search) throws SQLException {		//4.예매내역검색(예매번호) 
		Connection conn = OracleUtility.getConnection();
		String sql ="SELECT * FROM reservation WHERE ReserNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,search);
		ReservationDto result = null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String ReserNo = rs.getString(2);
			Date ReserDate = rs.getDate(3);
			String ReserSeat = rs.getString(4);
			String MoiveNo = rs.getString(5);
			int CustNo = rs.getInt(6);
			int ScreenNo = rs.getInt(7);
			result = new ReservationDto(ReserNo, ReserDate, ReserSeat, MoiveNo, CustNo, ScreenNo);
		}
		return result;
	}
	
	public CustomerDto CustomerOne(int search) throws SQLException {		//4.예매내역검색(전화번호) 
		Connection conn = OracleUtility.getConnection();
		String sql ="SELECT * FROM Customer WHERE CustNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,search);
		CustomerDto result = null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int CustNo = rs.getInt(2);
			String CustName = rs.getString(3);
			String CustPhone = rs.getString(4);
			int CustPoint = rs.getInt(5);
			result = new CustomerDto(CustNo, CustName, CustPhone, CustPoint);
		}
		return result;
	}
	
	
	
	
	
	public int delete(String reserno) throws SQLException { //5.예매취소 메소드
		Connection connection = OracleUtility.getConnection();
		String sql = "DELETE FROM reservation WHERE reserno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, reserno);		//메소드 인자(매개변수)값을 sql 쿼리에 전달
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	
	

}
