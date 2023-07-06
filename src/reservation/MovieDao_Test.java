package movie.kiosk;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class MovieDao_Test {

	public static void main(String[] args) {
		
		//SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		//Date date = date1.("2022-03-19");
		
		
		
		String  day1 = "2022-03-15 00:00:00.000"; // 형식을 지켜야 함
		Timestamp d1 = java.sql.Timestamp.valueOf(day1);
		
		System.out.println(d1);
		
		MovieDao dao = new MovieDao();
		
		// MovieDao insert 테스트
	  System.out.println("1. insert 테스트");
		 System.out.println("'as1234' ,'마션임파서블_데드레코닝(4DX)' ,'2022-03-19','15세' ,26000");
		 MovieDto dto = new MovieDto("as1234", "마션임파서블_데드레코닝(4DX)", d1 , "15세",26000);
		 	try {
		 		int count = dao.insert(dto);
		 		System.out.println(count);
		  }catch(SQLException e) { 
			  System.out.println(e); }
		 
		 	
		 // MovieDao update 테스트  
		  System.out.println("2. update 테스트");
		  System.out.println("'2022-03-14','17세' ,25000  - 데이터 수정" ); 
		  dto = MovieDto.builder() 
				  .MovieDate(d1) 
				  .MovieGrade("17") 
				  .MoviePrice(25000)
				  .build(); 
		  try { 
			  int count = dao.update(dto); 
			  System.out.println(count);
		  }catch (SQLException e) {
 			  System.out.println(e); }
		  
		  
		// MovieDao delete 테스트
		System.out.println("3. delete 테스트");
		System.out.println("as1234 - 데이터 삭제");
		 dto = new MovieDto("as1234"); 
		try {
			int count = dao.delete("as1234");
			System.out.println(count);
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	
}
}
