package movie.kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ScreeningDao {
	
	// 현재 상영작 보여주기
	
	public List<ScreeningDto> ScreeningselectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		   String sql = "select * from Screening ";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   List<ScreeningDto> results = new ArrayList<>();
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()) {
			   results.add(new ScreeningDto(rs.getInt(1),
					   					rs.getTimestamp(2),
					   					rs.getString(3),
					   					rs.getString(4)
					   ));
		   }
		   return results;
	}
	
	// 상영 정보 업데이트
	public int Screeningupdate(ScreeningDto screen) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "update Screening set ScreenDate = ?, ScreenTheater =?,  MovieTitle = ? where ScreenNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setTimestamp(1, screen.getScreenDate());
		ps.setString(2, screen.getScreenTheater());
		ps.setString(3, screen.getMovieTitle());
		ps.setInt(4, screen.getScreenNO());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
		
	}
	
	// 상영 정보 인서트
	public int Screeninginsert (ScreeningDto screen) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "insert into Screening values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, screen.getScreenNO());
		ps.setTimestamp(2, screen.getScreenDate());
		ps.setString(3, screen.getScreenTheater());
		ps.setString(4, screen.getMovieTitle());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	// 상영 정보 딜리트
	public int Screeningdelete(int ScreenNo) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "delete from screening where ScreenNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, ScreenNo);
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
		
	}
	
}
