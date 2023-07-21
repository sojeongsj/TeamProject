package kioskDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.OracleUtility;
import kioskDTO.ScreeningDto;



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
		
		// 영화 제목을 중복없이 보여주기 기능 
		public List<ScreeningDto> screentitle() throws SQLException {
			Connection connection = OracleUtility.getConnection();
			   String sql = "select DISTINCT MovieTitle from Screening";
			   PreparedStatement ps = connection.prepareStatement(sql);
			   List<ScreeningDto> results = new ArrayList<>();
			   ResultSet rs = ps.executeQuery();
			   while(rs.next()) {
				   results.add(new ScreeningDto(rs.getString(1)));
			   }
			   return results;
		}
		
		
		//사용자가 예매내역 조회할때 필요한 Screendate 기능
		public ScreeningDto search(String reserno) throws SQLException {
		    Connection connection = OracleUtility.getConnection();
		    String sql = "SELECT ScreenDate\r\n"
		            + "FROM Screening S\r\n"
		            + "JOIN RESERVATION R\r\n"
		            + "ON S.ScreenNo = R.ScreenNo \r\n"
		            + "WHERE ReserNo = ?";
		    PreparedStatement ps = connection.prepareStatement(sql);
		    ps.setString(1, reserno);
		    ResultSet rs = ps.executeQuery();
		    ScreeningDto result = null;
		    if (rs.next()) {
		        Date  screenDate = rs.getDate("ScreenDate");
		        
		        result = ScreeningDto.builder()
		                .ScreenDate(screenDate)
		                .build();
		    }
		    rs.close();
		    ps.close();
		    connection.close();
		    return result;
		}
		

			
			// 영화 제목으로 영화 상영 시간대 보여주기 기능
		    public ArrayList<Timestamp> getScreeningTimes(String movieTitle) throws SQLException {
		    	Connection connection = OracleUtility.getConnection();

		        String sql = "SELECT ScreenDate FROM Screening WHERE MovieTitle = ?";
		        ArrayList<Timestamp> screeningTimes = new ArrayList<>();
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, movieTitle);
		        ResultSet rs = statement.executeQuery();

		        while (rs.next()) {
		        	Timestamp timestamp = rs.getTimestamp(1);

		            screeningTimes.add(timestamp);
		        }

		        rs.close();
		        statement.close();

		        return screeningTimes;
		    }
		}

