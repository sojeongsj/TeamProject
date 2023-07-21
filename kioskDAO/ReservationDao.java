package kioskDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.OracleUtility;
import kioskDTO.ReservationAdminDTO;
import kioskDTO.ReservationDto;

public class ReservationDao {
/*	
	2.좌석 예매
	3.예매하기
	4.예매번호 or 전화번호로 예매내역 검색(예매티켓출력)
	5.예매취소
*/
	private static ReservationDao dao = new ReservationDao();
	//ReservationDao 유형의 "dao"라는 개인 정적 변수를 선언합니다.
	public ReservationDao() {}	
	public static ReservationDao getDao() { 
	//getDao()메소드를 사용해 dao에 저장된 ReservationDao 객체를 얻을수 있다
	return dao;
	}

	public int update(String reserSeat, String reserNo) throws SQLException {
	    Connection connection = OracleUtility.getConnection();
	    String sql = "UPDATE reservation SET ReserSeat = ? WHERE ReserNo = ?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, reserSeat);
	    ps.setString(2, reserNo);
	    int result = ps.executeUpdate();
	    ps.close();
	    connection.close();
	    return result;
	}
	
	
	public int insert(ReservationDto reser) throws SQLException {
	    Connection connection = OracleUtility.getConnection();
	    String sql = "INSERT INTO reservation VALUES(reserseq.nextval, sysdate, ?, ?, NULL, ?, ?)";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, reser.getReserSeat());
	    ps.setString(2, reser.getMovieTitle());
	    ps.setInt(3, reser.getScreenTheater());
	    ps.setInt(4, reser.getScreenNo());
	    int result = ps.executeUpdate();
	    
	    ps.close();
	    connection.close();        
	    return result;
	}
	
	public static ReservationDto ReservationOne(String ReserNo) throws SQLException {
	    Connection conn = OracleUtility.getConnection();
	    String sql = "SELECT r.ScreenTheater, r.ReserSeat, r.MovieTitle, s.ScreenDate " +
	            "FROM reservation r " +
	            "JOIN Screening s ON r.ScreenNo = s.ScreenNo " +
	            "WHERE r.ReserNo = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, ReserNo);
	    ReservationDto result = null;
	    
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        String reserSeat = rs.getString("ReserSeat");
	        String movieTitle = rs.getString("MovieTitle");
	        int ScreenTheater = rs.getInt("ScreenTheater");
	        Date screenDate = rs.getDate("ScreenDate");
	        result = ReservationDto.builder()
	                .ReserNo(ReserNo)
	                .ReserSeat(reserSeat)
	                .MovieTitle(movieTitle)
	                .CustNo(0) // 여기에 회원 번호를 설정해주세요
	                .ScreenTheater(ScreenTheater)
	               // .ScreenDate(screenDate) // ScreenDate 값을 설정합니다.
	                .build();
	    }
	    rs.close();
	    ps.close();
	    conn.close();
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
	 public List<ReservationDto> selectAll() throws SQLException {
	        Connection conn = OracleUtility.getConnection();
	        String sql = "SELECT * FROM Reservation";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        List<ReservationDto> reservations = new ArrayList<>();
	        while (rs.next()) {
	            String reserNo = rs.getString("ReserNo");
	            Date reserDate = rs.getDate("ReserDate");
	            String reserSeat = rs.getString("ReserSeat");
	            String movieTitle = rs.getString("MovieTitle");
	            int custNo = rs.getInt("CustNo");
	            int screenNo = rs.getInt("ScreenNo");
	            ReservationDto reservation = ReservationDto.builder()
	                    .ReserNo(reserNo)
	                    .ReserDate(reserDate)
	                    .ReserSeat(reserSeat)
	                    .MovieTitle(movieTitle)
	                    .CustNo(custNo)
	                    .ScreenNo(screenNo)
	                    .build();
	            reservations.add(reservation);
	        }
	        rs.close();
	        ps.close();
	        conn.close();
	        return reservations;
	    }
	 
	 public String getLatestReservationNumber() throws SQLException {
	        Connection conn = OracleUtility.getConnection();
	        String sql = "SELECT RESERNO FROM RESERVATION " +
	                     "WHERE ROWNUM = 1 " +
	                     "ORDER BY RESERNO DESC";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        String reservationNumber = null;
	        if (rs.next()) {
	            reservationNumber = rs.getString("RESERNO");
	        }
	        rs.close();
	        ps.close();
	        conn.close();
	        return reservationNumber;
	    }
	

}