package hello.servlet.basic.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 파라미터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requsetParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {

		System.out.println("[전체 파라미터 조회] - start");
		request.getParameterNames()
			.asIterator()
			.forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
		System.out.println("[전체 파라미터 조회] - end");

		System.out.println();

		System.out.println("[단일 파라미터 조회]");
		// 같은 이름의 파라미터가 존재하면, 단건 조회 시 우선순위가 높은 먼저 나오는 파라미터가 잡힌다.
		String username = request.getParameter("username");
		String age = request.getParameter("age");

		System.out.println("username = " + username);
		System.out.println("age = " + age);

		System.out.println();

		System.out.println("[이름이 같은 복수 파라미터 조회]");
		String[] usernames = request.getParameterValues("username");
		for (String name : usernames) {
			System.out.println("usernames = " + name);
		}

		response.getWriter().write("ok");

	}
}
