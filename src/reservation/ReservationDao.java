package reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.FoodDTO;
import pack_CGV_Admin.OracleUtility;

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
		
		String sql = "INSERT INTO reservation VALUES(reserseq.nextval, sysdate, ?, cusseq.nextval, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, reser.getReserSeat());
		ps.setString(2, reser.getMovieTitle());//영화이름으로 바꾸기
//		Random random = new Random();
//      int screenNo = random.nextInt(15) + 1; // 1부터 15까지의 랜덤한 숫자 생성
        ps.setInt(3, reser.getScreenNo());
		
        int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result;
	}
	
	public ReservationDto ReservationOne(String ReserNo) throws SQLException {		//4.예매내역검색(예매번호) 
		Connection conn = OracleUtility.getConnection();
		String sql ="SELECT r1.screendate, r.ScreenNo, r.reserseat, r.MovieTitle \r\n"
				+ "FROM reservation r \r\n"
				+ "JOIN Screening r1 \r\n"
				+ "ON r.MovieTitle = r1.MovieTitle \r\n"
				+ "WHERE r.ReserNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,ReserNo);
		ReservationDto result = null;
		ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
			        Date reserDate = rs.getDate("ReserDate");
			        String reserSeat = rs.getString("ReserSeat");
			        String movieTitle = rs.getString("MovieTitle");
			        int custNo = rs.getInt("CustNo");
			        int screenNo = rs.getInt("ScreenNo");
			        result = ReservationDto.builder()
			                .ReserNo(ReserNo)
			                .ReserDate(reserDate)
			                .ReserSeat(reserSeat)
			                .MovieTitle(movieTitle)
			                .CustNo(custNo)
			                .ScreenNo(screenNo)
			                .build();
			 
			 }
		return result;
	}
		
	public List<ReservationAdminDTO> reservationTotal() throws SQLException {
	    Connection conn = OracleUtility.getConnection();
	    String sql = "SELECT r.MovieTitle, COUNT(R.MOVIETITLE), SUM(m.MoviePrice) AS MoviePrice\r\n"
	    		+ "FROM Reservation r\r\n"
	    		+ "JOIN Movie m ON r.MovieTitle = m.MovieTitle\r\n"
	    		+ "GROUP BY r.MovieTitle";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    List<ReservationAdminDTO> reservations = new ArrayList<>();

	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
	        String movieTitle = rs.getString("MovieTitle");
	        int count = rs.getInt("COUNT(R.MOVIETITLE)");
	        int movieprice = rs.getInt("MoviePrice");
	        
	        ReservationAdminDTO reservation = new ReservationAdminDTO(movieTitle, count, movieprice);
	        reservations.add(reservation);
	    }  
	    
	    ps.close();
        conn.close();

	    return reservations;
	}
	
	
//	
//	public CustomerDTO CustomerOne(int search) throws SQLException {		//4.예매내역검색(전화번호) 
//		Connection conn = OracleUtility.getConnection();
//		String sql ="SELECT * FROM Customer WHERE CustNo = ?";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1,search);
//		CustomerDTO result = null;
//		ResultSet rs = ps.executeQuery();
//		if(rs.next()) {
//			int CustNo = rs.getInt(2);
//			String CustName = rs.getString(3);
//			String CustPhone = rs.getString(4);
//			int CustPoint = rs.getInt(5);
//			result = new CustomerDTO(CustNo, CustName, CustPhone, CustPoint);
//		}
//		return result;
//	}
	
	
	
	
	
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