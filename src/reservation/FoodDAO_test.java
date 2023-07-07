package movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FoodDAO_test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//FoodDao 시작
        //음식 카테고리별로 조회
        FoodDAO foodDao = new FoodDAO();
        System.out.print("검색어 입력 >>> ");
		String foodCate = sc.nextLine();
		try {
			List<FoodDTO> foods = foodDao.getFoodsByCategory(foodCate);
			for(FoodDTO foodcate : foods)
				System.out.println(foodcate);
			
		} catch (SQLException e) {
			System.out.println("상품검색 예외 : " + e.getMessage());
		}
		
		//food update
		System.out.println("음식 정보 업데이트");
		
		System.out.println("음식 번호 입력하세요");
		int foodNo = sc.nextInt();
		sc.nextLine();
		
		System.out.println("음식 카테고리");
		String foodcate = sc.nextLine();
		
		System.out.println("음식 이름");
		String foodName = sc.nextLine();
		
		System.out.println("음식 가격");
		int foodPrice = sc.nextInt();
		sc.nextLine();
		
		FoodDTO foodDTO = new FoodDTO(foodNo, foodcate, foodName, foodPrice);
        foodDTO.setFoodNo(foodNo);
        foodDTO.setFoodCate(foodcate);
        foodDTO.setFoodName(foodName);
        foodDTO.setFoodPrice(foodPrice);

        FoodDAO dao = new FoodDAO();

        try {
            int result = dao.update(foodDTO);
            if (result > 0) {
                System.out.println("음식 정보 업데이트 성공");
            } else {
                System.out.println("음식 정보 업데이트 실패");
            }
        } catch (SQLException e) {
            System.out.println("음식 정보 업데이트 예외: " + e.getMessage());
        }
		
		sc.close();
	}
}