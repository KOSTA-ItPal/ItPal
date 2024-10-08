package web.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DepositDAOImpl;
import model.vo.Deposit;

public class DepositSearchController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = null;
		String path = "depositsearch.jsp";

		
		//1. 클라이언트에서 보낸 필터링 조건을 받음
		String depositType = request.getParameter("depositType");
		String bankSector = request.getParameter("bankSector");
		String registerPeriod = request.getParameter("registerPeriod");
		String calMethod = request.getParameter("calMethod");
		
		
		try {
        	// 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
			response.setCharacterEncoding("utf-8");
			//
			
			//2. dao 호출하여 필터링된 데이터를 가져옴
	    	ArrayList<Deposit> deposits = DepositDAOImpl.getInstance().searchDeposit(depositType, bankSector, registerPeriod, calMethod);
			System.out.println("1. getInstance() 성공");
			System.out.println("[getInstance() 결과] deposit 데이터 갯수 : " + deposits.size());
			
			//3. 데이터를 json 형태로 변환
			String json = new Gson().toJson(deposits);
			System.out.println("[json 변환 결과] json 문자열 길이: "+json.length());
						
			//4. json 데이터를 ajax에 전송
			PrintWriter out = response.getWriter();
			response.setContentType("application/json; charset=utf-8"); //응답 타입을 json으로 설정
			
			//
			request.setAttribute("json", json);

			//
			path = "depositresult.jsp";
			//
			
			System.out.println("depositSearch.do 성공");
		} catch (SQLException | IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			System.err.println("[Error] : depositsearch.do 실행 실패");
		} //try-catch
		
		return new ModelAndView(path);
	} //handleRequest
} //DepositSearchController