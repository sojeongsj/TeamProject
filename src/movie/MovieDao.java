package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pack_CGV_Admin.OracleUtility;
import reservation.ReservationDao;




public class MovieDao {
	public static MovieDao dao = new MovieDao();
	public MovieDao() {}	
	public static MovieDao getDao() { 
	//getDao()메소드를 사용해 dao에 저장된 ReservationDao 객체를 얻을수 있다
	return dao;
	}
	
	// 관리자 기능 insert
	
	public int insert (MovieDto movie) throws SQLException {
		Connection connection =   OracleUtility.getConnection();
		String sql = "insert into movie values(?,?,to_timestamp(?,'yyyy-mm-dd'),?,?)";
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
	    Connection connection = OracleUtility.getConnection();
	    String sql = "update movie set MovieDate = ?, MovieGrade = ?, MoviePrice = ? where MovieNo = ?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setTimestamp(1, movie.getMovieDate());
	    ps.setString(2, movie.getMovieGrade());
	    ps.setInt(3, movie.getMoviePrice());
	    ps.setString(4, movie.getMovieNO());
	    int result = ps.executeUpdate();
	    
	    ps.close();
	    connection.close();
	    return result;
	}
	
	// 관리자 기능 delete
	
	public int delete(String MovieNo) throws SQLException {
	    Connection connection = OracleUtility.getConnection();
	    String sql = "delete from movie where MovieNo = ?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, MovieNo);
	    int result = ps.executeUpdate();
	    
	    ps.close();
	    connection.close();
	    
	    return result;
	}


	// 관리자 기능 selectAll
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