package movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerDAO_test {
	public static void main(String[] args) {
		//회원목록조회
		System.out.println("회원목록 조회");
		CustomerDAO customerDao = new CustomerDAO();
		try {
			List<CustomerDTO> customers = customerDao.selectAll();
			for(CustomerDTO customer : customers)
				System.out.println(customer);
		} catch (SQLException e) {
			System.out.println("회원목록 조회 예외 : " + e.getMessage());
		}
		
		//간편회원가입
		Scanner sc = new Scanner(System.in);
		System.out.println("간편 회원가입");
		
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		
		System.out.println("전화번호를 입력하세요");
		String phone = sc.nextLine();
		
		CustomerDAO dao = new CustomerDAO();
		
		try {
			int result = dao.RegistrationCust(name, phone);
			if(result>0) {
				System.out.println("회원등록 성공");
			}else {
				System.out.println("회원등록 실패");
			}
		}catch(SQLException e){
			System.out.println("회원 등록 예외 :"+ e.getMessage());
			System.out.println("회원등록을 종료합니다");
		}
		

		
		//업데이트
		System.out.println("회원 정보 업데이트");
		
		System.out.println("회원 번호 입력하세요");
		int custNo = sc.nextInt();
		sc.nextLine();
		
		System.out.println("이름");
		name = sc.nextLine();
		
		System.out.println("전화번호");
		phone = sc.nextLine();
		
		System.out.println("포인트");
		int point = sc.nextInt();
		sc.nextLine();
		
		CustomerDTO customerDTO = new CustomerDTO(custNo, name, phone, point);
        customerDTO.setCustNo(custNo);
        customerDTO.setCustName(name);
        customerDTO.setCustPhone(phone);
        customerDTO.setCustPoint(point);

        CustomerDAO dao1 = new CustomerDAO();

        try {
            int result = dao1.update(customerDTO);
            if (result > 0) {
                System.out.println("회원 정보 업데이트 성공");
            } else {
                System.out.println("회원 정보 업데이트 실패");
            }
        } catch (SQLException e) {
            System.out.println("회원 정보 업데이트 예외: " + e.getMessage());
        }
        
        // 포인트 적립 
		
        System.out.println("포인트 적립");
        
        System.out.print("전화번호를 입력하세요: ");
        String Phone = sc.nextLine();

        CustomerDAO dao4 = new CustomerDAO();
        try {
            dao4.pointupdate(Phone);
            System.out.println("포인트 적립이 완료되었습니다.");
        } catch (SQLException e) {
            System.out.println("포인트 적립 예외: " + e.getMessage());
        }

    }
}





































