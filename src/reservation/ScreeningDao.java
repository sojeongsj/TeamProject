package movie.kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day5.OracleUtility;


public class ScreeningDao {
	
	// 현재 상영작 보여주기
	
	public List<ScreeningDto> selectAll() throws SQLException {
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
	
	
}
