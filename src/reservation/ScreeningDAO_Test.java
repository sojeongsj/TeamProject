package movie.kiosk;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ScreeningDAO_Test {

	public static void main(String[] args) {
		
		String  day1 = "2022-03-15 00:00:00.000"; // 형식을 지켜야 함
		Timestamp d1 = java.sql.Timestamp.valueOf(day1);
		
		
		// ScreeningDao Select all 테스트
				System.out.println("1. selectall 테스트");
				ScreeningDao dao1 = new ScreeningDao();
				try {
					List<ScreeningDto> screening = dao1.ScreeningselectAll();
					for(ScreeningDto scr : screening) 
						System.out.println(scr);
				}catch(SQLException e) {
					System.out.println(e);
				}
				
				
				// ScreeningDao insert 테스트
				System.out.println("2. insert 테스트");
				System.out.println("132,2022-03-14 00:00:00.000,3,미션임파서블");
				ScreeningDto dto1 = new ScreeningDto(133,d1,"4","미션임파서블_데드레코닝(4DX)");
				 	try {
				 		int count = dao1.Screeninginsert(dto1);
				 		System.out.println(count);
				  }catch(SQLException e) { 
					  System.out.println(e); }
				
				 	
				// ScreeningDao update 테스트
				System.out.println("3. update 테스트");
				System.out.println("'2022-03-15', 4,   - 데이터 수정" ); 
				dto1 = new ScreeningDto(132,d1,"6","미션임파서블_데드레코닝(4DX)");
					  try { 
						  int count = dao1.Screeningupdate(dto1); 
						  System.out.println(count);
					  }catch (SQLException e) {
						  System.out.println(e); 
						  }
					  
					  System.out.println("4. delete 테스트");
						System.out.println("133 - 데이터 삭제");
						 dto1 = new ScreeningDto(133); 
						try {
							int count = dao1.Screeningdelete(133);
							System.out.println(count);
						}catch(SQLException e) {
							System.out.println(e);
						}
			}
			
			
			
			

	}


