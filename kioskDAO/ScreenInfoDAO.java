package kioskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.OracleUtility;
import kioskDTO.ScreenInfoDto;



public class ScreenInfoDAO {

   public List<ScreenInfoDto> getScreenInfo(String movieTitle, String screeningDate) throws SQLException{
      List<ScreenInfoDto> screeningDetails = new ArrayList<>();

      Connection conn = OracleUtility.getConnection();
       String sql = "SELECT m.MOVIEPRICE, s.SCREENNO, s.SCREENTHEATER, m.MOVIETITLE " +
                 "FROM ICLASS.MOVIE m " +
                 "JOIN ICLASS.SCREENING s ON m.MOVIETITLE = s.MOVIETITLE " +
                 "WHERE m.MOVIETITLE = ? AND s.SCREENDATE = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')";
       
      PreparedStatement ps = conn.prepareStatement(sql);
      
      ps.setString(1, movieTitle);
      ps.setString(2, screeningDate);
      ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int moviePrice = rs.getInt("MOVIEPRICE");
            int screenNo = rs.getInt("SCREENNO");
            String screenTheater = rs.getString("SCREENTHEATER");
            String title = rs.getString("MOVIETITLE");
            
            ScreenInfoDto screenInfoDto = new ScreenInfoDto(moviePrice, screenNo, screenTheater, title);
            screeningDetails.add(screenInfoDto);
        }
      return screeningDetails;
   }
}