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
		   String sql = "select * from Screeening ";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   List<ScreeningDto> results = new ArrayList<>();
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()) {
			   results.add(new ScreeningDto(rs.getString(3),
					   rs.getDate(4)));
		   }
		   return results;
	}
	
	// 상영 정보 업데이트
	public int Screeningupdate(ScreeningDto screen) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "update Screening set ScreenNo = ?, MovieNO = ?, MovieTitle = ?, ScreenDate = ?, ScreenTheater =?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, screen.getScreenNO());
		ps.setString(2, screen.getMovieNO());
		ps.setString(3, screen.getMovieTitle());
		ps.setDate(4, screen.getScreenDate());
		ps.setString(5, screen.getScreenTheater());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
		
	}
	
	// 상영 정보 인서트
	public int Screeninginsert (ScreeningDto screen) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "insert into Screening values(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, screen.getScreenNO());
		ps.setString(2, screen.getMovieNO());
		ps.setString(3, screen.getMovieTitle());
		ps.setDate(4, screen.getScreenDate());
		ps.setString(5, screen.getScreenTheater());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	// 상영 정보 딜리트
	public int Screeningdelete(ScreeningDto screen) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "delete movie where MovieNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, screen.getScreenNO());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
		
	}
	
}
