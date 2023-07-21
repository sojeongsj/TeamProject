package kioskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.OracleUtility;
import kioskDTO.ReservationseatDto;

public class ReservationseatDAO {
	
	private static final int ScreenNo = 0;

	public List<String> getReserSeat(int ScreenNo) throws SQLException{
		List<String> reserSeats = new ArrayList<>();
		Connection conn = OracleUtility.getConnection();
		String sql = "SELECT ReserSeat FROM Reservation WHERE ScreenNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, ScreenNo);
		ResultSet rs = ps.executeQuery();
		ReservationseatDto result = null;
		while(rs.next()){
			String tmp = rs.getString("ReserSeat");
			reserSeats.add(tmp);
			
		}
		return reserSeats;
	}
}
