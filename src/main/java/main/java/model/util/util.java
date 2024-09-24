package main.java.model.util;

public class util {
	public static String transformPattern(String category) {
		String newCategory = null; 
		
		 if(category.equals("cafe")) {
			 newCategory ="카페";
		 }else if (category.equals("mobile")) {
			 newCategory  ="통신비";
		}else if (category.equals("transport")) {
			 newCategory  ="대중교통";
		}else if (category.equals("culture")) {
			 newCategory  ="영화";
		}else if (category.equals("shopping")) {
			 newCategory  ="쇼핑";
		}else if (category.equals("travel")) {
			 newCategory  ="여행";
		}else if (category.equals("digital_content")) {
			 newCategory  ="디지털";
		}else if (category.equals("cvs")) {
			 newCategory  ="편의점";
		}
		/*현재 코드의 문제점 culture와 travle과 같이 여러 가지 뜻?을 가진 단어를 여러번 검색이 안된다? 
		하드코딩의 문제점 같긴하다...
		*/
		 
		return  newCategory ;
	}
}
