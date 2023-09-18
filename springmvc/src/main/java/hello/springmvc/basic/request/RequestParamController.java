package hello.springmvc.basic.request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.springmvc.basic.HelloData;
import hello.springmvc.basic.TestData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {

	/**
	 * 반환 타입이 없으면서 이렇게 응답에 값을 직접 적어 넣으면, View 조회 X
	 */
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("RequestParamController.requestParamV1");
		String username = request.getParameter("username");

		int age = Integer.parseInt(request.getParameter("age"));

		log.info("username={}, age={}", username, age);
		response.getWriter().write("ok");

	}

	/**
	 * @RequestParam 사용
	 * - 파라미터 이름으로 바인딩
	 * @ResponseBody 추가
	 * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
	 */
	@RequestMapping("/request-param-v2")
	@ResponseBody
	public String requestParamV2(
		@RequestParam("username") String memberName,
		@RequestParam("age") int memberAge
	) {
		System.out.println("RequestParamController.requestParamV2");
		log.info("username={}, age={}", memberName, memberAge);
		return "ok";
	}

	/**
	 * @RequestParam 사용
	 * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
	 */
	@RequestMapping("/request-param-v3")
	@ResponseBody
	public String requestParamV3(
		@RequestParam String username,
		@RequestParam int age
	) {
		System.out.println("RequestParamController.requestParamV3");
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam 사용
	 * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
	 */
	@RequestMapping("/request-param-v4")
	@ResponseBody
	public String requestParamV4(
		String username,
		int age
	) {
		System.out.println("RequestParamController.requestParamV4");
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam.required
	 * /request-param-required -> username이 없으므로 예외 *
	 * 주의!
	 * /request-param-required?username= -> 빈문자로 통과 *
	 * 주의!
	 * /request-param-required
	 * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(
		@RequestParam(required = true) String username,
		@RequestParam(required = false) Integer age) {
		System.out.println("RequestParamController.requestParamRequired");
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam
	 * - defaultValue 사용 *
	 * 참고: defaultValue는 빈 문자의 경우에도 적용 * /request-param-default?username=
	 */
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
		@RequestParam(required = true, defaultValue = "guest") String username,
		@RequestParam(required = false, defaultValue = "-1") int age) {
		System.out.println("RequestParamController.requestParamDefault");
		log.info("username={}, age={}", username, age);
		return "ok";
	}

	/**
	 * @RequestParam Map, MultiValueMap
	 * Map(key=value)
	 * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		System.out.println("RequestParamController.requestParamMap");
		log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-multi")
	public String requestParamMultiMap(@RequestParam MultiValueMap<String, String> paramMap) {
		System.out.println("RequestParamController.requestParamMultiMap");
		List<String> list = paramMap.get("age");

		String testOrigin = paramMap.get("TEST").get(0);

		for (String s : list) {
			log.info("age info = {}", s);
		}
		log.info("username={}, age={}", paramMap.get("username").get(0), paramMap.get("age"));
		return "ok";
	}

	/**
	 * @ModelAttribute 사용
	 * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을 설명할 때
	 * 자세히 설명
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute("viewModel") HelloData helloData, Model model,
		@ModelAttribute(binding = false) TestData testData,
		@ModelAttribute(name = "testData1", binding = false) TestData testData1) {
		System.out.println("RequestParamController.modelAttributeV1");

		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		log.info("helloData = {}", helloData);

		log.info("testData = {}", testData);
		log.info("testData = {}", testData1);

		// 나는 분명 model 객체에 helloData를 넣지 않앗다. 그런데 들어가 있다!! 이게 @ModelAttribute가 제공하는 또 다른 기능
		// 요거를 view 로 넘겨주면 view 에서는 helloData로 꺼내 쓸수 있겟죠?
		log.info("@ModelAttribute model 자동 적용 = {}", model.getAttribute("viewModel").toString());
		log.info("@ModelAttribute model 자동 적용 = {}", model.getAttribute("testData").toString());
		for (Map.Entry<String, Object> stringObjectEntry : model.asMap().entrySet()) {
			System.out.println(stringObjectEntry.getValue());
			System.out.println(stringObjectEntry.getKey());
		}

		return "ok";
	}

	/**
	 * @ModelAttribute 생략 가능
	 * String, int 같은 단순 타입 = @RequestParam
	 * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		System.out.println("RequestParamController.modelAttributeV2");

		log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
		log.info("helloData = {}", helloData);
		return "ok";
	}
}
