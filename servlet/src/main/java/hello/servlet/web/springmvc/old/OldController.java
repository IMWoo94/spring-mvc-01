package hello.servlet.web.springmvc.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 옛날 버전의 Controller
 * spring bean의 이름을 url 패턴으로 맞춘다.
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OldController.handleRequest");
		return new ModelAndView("new-form");
	}
}
