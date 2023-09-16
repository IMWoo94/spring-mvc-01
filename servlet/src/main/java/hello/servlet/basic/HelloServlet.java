package hello.servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	// 해당 서블릿이 호출이 되면 service가 호출된다.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		String username = request.getParameter("username");
		System.out.println("username = " + username);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		// 응답 정보에 들어간다.
		response.getWriter().write("hello " + username);
	}
}
