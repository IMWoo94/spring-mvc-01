package hello.servlet.web.servletmvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {

		// WEB_INF -> 정해진 룰 해당 경로는 컨트롤러, 서블릿을 통해서만 접근이 가능하도록 하는 것
		// 즉, 내부에서 찾아올 수 있는 리소스 경로

		String viewPath = "/WEB_INF/views/new-form.jsp";
		// 들어온 요청을 서버 안에서 내부적으로 전달하기 위해서 사용되는 클래스 입니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		// forward 는 서버 내부에서 다시 호출하는 것이며, 처리 결과에 대한 제어를 넘긴 화면에게 제어권을 넘겨 준다.
		dispatcher.forward(request, response);
	}
}
