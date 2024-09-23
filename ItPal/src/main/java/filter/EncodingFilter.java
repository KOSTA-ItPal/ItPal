package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//필터링에 적용할 코드를 이 부분에 등록한다.
		// 양방향 한글처리...
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//컨테이너 상에서 다음에 나오는 컴포넌트(서블릿)에게 계속 필터에 적용한 것들을 적용하도록 한다.
		chain.doFilter(request, response);
	}
}
