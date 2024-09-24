package web.servlet.controller;

//Component를 생성하는 일종의 공장
//서블릿이 전해주는 command값에 따라서 Component를 생성
//그리고 다시 서블릿에게 생성한 Component를 반환할때는 Controller 인터페이스 타입으로 반환
public class HandlerMapping {

	private static HandlerMapping factory = new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getFactory() {
		return factory;
	}
	
	//Component를 생성하는 기능...
	public Controller createComponent(String command) {
		Controller controller = null;
		
		if(command.equals("register.do")) controller = new RegisterController();	
		else if(command.equals("login.do")) controller = new LoginController();		
		else if(command.equals("myPage.do")) controller = new MyPageController();		
		else if(command.equals("setBudget.do")) controller = new SetBudgetController();		
		else if(command.equals("setSave.do")) controller = new SetSaveController();		
//		else if(command.equals("cardList.do")) controller = new CardListController();
		else if(command.equals("idcheck.do")) controller = new IdDuplicateCheckController(); //아직 미정,..
		else if(command.equals("cardSearch.do")) controller = new CardSearchController();
		else if(command.equals("cardSearch2.do")) controller = new CardSearch2Controller();
		else if(command.equals("cardRecommend.do")) controller = new CardRecommendController();
		else if(command.equals("depositList.do")) controller = new DepositListController();
		else if(command.equals("depositSearch.do")) controller = new DepositSearchController();
		
		return controller;
	}
}
