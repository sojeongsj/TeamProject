package reservation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		 ReservationDao reservationDao = new ReservationDao();
		 ScreeningDao screendao = new ScreeningDao();
	        System.out.println(":::::: 예약 테스트 :::::");

	        Scanner sc = new Scanner(System.in);

	        // 1. 좌석 예매
	        System.out.println("예약번호 입력 >>");
	        String reserNo = sc.nextLine();

	        System.out.println("좌석 선택 >>");
	        String reserSeat = sc.nextLine();

	        try {
	            reservationDao.update(reserSeat, reserNo);
	            System.out.println("좌석 " + reserSeat + "이(가) 예매되었습니다.");
	        } catch (SQLException e) {
	            System.out.println("좌석 업데이트 예외: " + e.getMessage());
	        }

	        // 2. 예약 테스트
	        System.out.println("영화이름을 입력하세요>>");
	        String movieTitle = sc.nextLine();

	        System.out.println("상영관을 고르세요>>");
	        int screenNo = sc.nextInt();
	        sc.nextLine();

	        System.out.println("예약할 좌석을 입력하세요>>");
	        String reservedSeat = sc.nextLine();

	        ReservationDto reservation = ReservationDto.builder()
	                .ReserNo(reserNo)
	                .ReserSeat(reservedSeat)
	                .MovieTitle(movieTitle)
	                .ScreenNo(screenNo)
	                .build();

	        try {
	            int result = reservationDao.insert(reservation);
	            System.out.println("예약 완료건: " + result);
	        } catch (SQLException e) {
	            System.out.println("예약 오류: " + e.getMessage());
	        }

	        // 3. 예매내역검색(예매번호)
	        System.out.println("조회할 예약번호를 입력하세요>>");
	        String reserNo1 = sc.nextLine();

	        try {
	            ReservationDto reservation1 = reservationDao.ReservationOne(reserNo1);
	            ScreeningDto screen = screendao.search(reserNo1);
	            if (reservation1 != null) {
	                System.out.println("예약번호: " + reservation1.getReserNo());
	                System.out.println("상영날짜: " + screen.getScreenDate());
	                System.out.println("예약좌석: " + reservation1.getReserSeat());
	                System.out.println("영화제목: " + reservation1.getMovieTitle());
	                System.out.println("영화상영품번: " + reservation1.getScreenNo());
	                System.out.println("====================");
	            } else {
	                System.out.println("해당 예약번호에 대한 예약이 없습니다.");
	            }
	        } catch (SQLException e) {
	            System.out.println("예약 정보 검색 중 오류 발생: " + e.getMessage());
	            e.printStackTrace();
	        }

	        // 4. 고객예약취소 테스트
	        System.out.println("예약 취소할 예약번호를 입력하세요");
	        String reservationNo1 = sc.nextLine();

	        try {
	            int result = reservationDao.delete(reservationNo1);
	            if (result > 0) {
	                System.out.println("예약 취소가 완료되었습니다.");
	            } else {
	                System.out.println("예약 취소에 실패하였습니다.");
	            }
	        } catch (SQLException e) {
	            System.out.println("예약 취소 오류: " + e.getMessage());
	        }

	        // 5. 예약 전체 조회
	        try {
	            List<ReservationDto> reservations = reservationDao.selectAll();
	            System.out.println("======= 예약 전체 조회 =======");
	            for (ReservationDto reser : reservations) {
	                System.out.println("예약번호: " + reser.getReserNo());
	                System.out.println("예약날짜: " + reser.getReserDate());
	                System.out.println("예약좌석: " + reser.getReserSeat());
	                System.out.println("영화제목: " + reser.getMovieTitle());
	                System.out.println("영화상영품번: " + reser.getScreenNo());
	                System.out.println("====================");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        sc.close();
	    }
	}
	






	    
	    
	   
	
	
	
	
		
	
		
	
	

