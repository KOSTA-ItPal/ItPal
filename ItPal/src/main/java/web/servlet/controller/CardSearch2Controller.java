package web.servlet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.java.model.CardDAOImpl;
import main.java.model.vo.Card;

public class CardSearch2Controller implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "index.html";
		boolean isRedirect = false;
		CardDAOImpl dao = CardDAOImpl.getInstance();
		
		try {
			String cardType = request.getParameter("cardType");
			String category = request.getParameter("category");
			System.out.println("[CardSearch2] 실행중1...cardType:" + cardType + ", category:" + category);
			
			//카드 타입과 카테고리에 해당하는 카드 리스트들...
			ArrayList<Card> cards = dao.searchByCategory(cardType, category);
			
			System.out.println("[CardSearch2] 실행중2...cards 추출 완료 및 카드 디테일 분할 시작");
			
			//카드 detail 분할
			Map<String, ArrayList<String>> splitDetails = new HashMap<>();
			
			for (Card card : cards) {
				String cardNo = String.valueOf(card.getCardNo());
				
				ArrayList<String> details = new ArrayList<>();
				
				String cardDetail = card.getCardDetail(); //cardDetail == str
				cardDetail = cardDetail.replaceAll("\\[[가-힣 A-Za-z0-5\\%]+\\][ ]?", "");
	            String strArr[] = cardDetail.split("-([가-힣A-Z]+[\\/]?[가-힣A-Z]+)-",5);
	            
	            if(cardDetail.length() == 0 ) // 이 부분은 뭐지?	
	                details.add(cardDetail);	       
	            else {
	                int endIdx = 0;
	                if (strArr.length < 4) endIdx = strArr.length;
	                else endIdx = strArr.length - 1;

	                for (int i = 1; i < endIdx; i++) {
	                    details.add(strArr[i]);
	                }
	            }
	            
	           splitDetails.put(cardNo, details);
			}
			//카드 detail 분할 끝
			
			System.out.println("[CardSearch2] 실행중3...분할 완료");
			
			Map<String, Object> responseData = new HashMap<>();
			responseData.put("cards", cards);
			responseData.put("splitDetails", splitDetails);
			
			Gson gson = new Gson();
	        String jsonResponse = gson.toJson(responseData);
	        
	        // JSON 형식으로 반환할 수 있도록 response의 MIME 타입 설정
	        response.setContentType("application/json; charset=UTF-8");
	        request.setAttribute("jsonResponse", jsonResponse);
//			request.setAttribute("cards", cards);
			
	        path = "cardsearchresult2.jsp";
	        System.out.println("[CardSearch2] 실행중4...");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView(path);
	}	
}
