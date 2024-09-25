package web.servlet.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CardDAOImpl;
import model.vo.Card;
import model.vo.User;

public class CardRecommendController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "login.html";
		boolean isRedirect = false;
		
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if( user != null) {
				
		/* 카드 */			
				ArrayList<String> categoryList = CardDAOImpl.getInstance().searchCategory(user); //카테고리를 받아온 리스트
			
				HashMap<String, ArrayList<Card>> cardListByCategoryMap = new HashMap<>();
				
				for (String category : categoryList) {
					cardListByCategoryMap.put( category,
							CardDAOImpl.getInstance().searchCardsByCategory(category));
				}
				
				System.out.println("카드 카테고리별 카드 리스트");
				for(String category : cardListByCategoryMap.keySet()) {
					System.out.println(category + ", "+ cardListByCategoryMap.get(category));
				}
				System.out.println();
				
		/* 카드 상세 보기*/		
				// 카드리스트들, 카드 디테일들
				ArrayList<ArrayList<String>> cardDetails = new ArrayList<>();
				
				for(String category : categoryList) {
					
					ArrayList<Card> cards= cardListByCategoryMap.get(category);
					ArrayList<String> details = null;
					for(Card card : cards) {
						
						String str = card.getCardDetail();
						
						str = str.replaceAll("\\[[가-힣 A-Za-z0-5\\%]+\\][ ]?", "");
			            String strArr[] = str.split("-([가-힣A-Z]+[\\/]?[가-힣A-Z]+)-",5);
			            
			            details = new ArrayList<>();
			            
			            if( str.length() ==0 ){
			                details.add(str);
			            }
			            else {
			                int endIdx = 0;
			                if (strArr.length < 4) endIdx = strArr.length;
			                else endIdx = strArr.length - 1;

			                for (int i = 1; i < endIdx; i++) {
			                    details.add(strArr[i]);
			                }
			            }
			            cardDetails.add( details);
					}
					 
				}
	
//				  System.out.println("카드 리스트 별 카드 디테일\n"); 
//				  for(int i=0; i<cardDetails.size(); i++) { 
//					  for(int j =0; j< cardDetails.get(i).size(); j++) 
//						  System.out.println(i + " " + j + " : " + cardDetails.get(i).get(j) ); 
//					  
//					  System.out.println("=============\n"); 
//				  }
//				  
//				  System.out.println(cardDetails.size());
				

				request.setAttribute("cardListByCategoryMap", cardListByCategoryMap);
				request.setAttribute("cardDetails", cardDetails);
				
				path = "cardrecommend.jsp";
			}
			else {
				isRedirect = true;				
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return new ModelAndView(path,isRedirect);
	}

}
