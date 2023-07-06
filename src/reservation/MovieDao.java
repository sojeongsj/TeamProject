package movie.kiosk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class MovieDao {
	
	// 관리자 기능 insert
	
	public int insert (MovieDto movie) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "insert into movie values(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, movie.getMovieNO());
		ps.setString(2, movie.getMovieTitle());
		ps.setTimestamp(3, movie.getMovieDate());
		ps.setString(4, movie.getMovieGrade());
		ps.setInt(5, movie.getMoviePrice());
		
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	// 관리자 기능 updatg
	
	public int update(MovieDto movie) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "update movie set MovieDate = ?, MovieGrade = ?, MoviePrice =?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setTimestamp(1, movie.getMovieDate());
		ps.setString(2, movie.getMovieGrade());
		ps.setInt(3, movie.getMoviePrice());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
		
	}
	
	// 관리자 기능 delete
	
	public int delete(String MovieNo) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "delete from movie where MovieNo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, MovieNo);
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
		
	}

	
	public List<MovieDto> MovieselectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		   String sql = "select * from movie ";
		   PreparedStatement ps = connection.prepareStatement(sql);
		   List<MovieDto> results = new ArrayList<>();
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()) {
			   results.add(new MovieDto(rs.getString(1),
					   					rs.getString(2),
					   					rs.getTimestamp(3),
					   					rs.getString(4),
					   					rs.getInt(5)
					   ));
		   }
		   return results;
	}

	
}
