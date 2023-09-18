package hello.springmvc.basic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LogTestController {

	// private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest() {
		String name = "Spring";
		System.out.println("name = " + name);
		System.out.println("log = " + log.getClass());

		if (log.isInfoEnabled()) {
			log.info("isInfoEnabled");
		}
		log.trace("trace log ={}", name);
		log.debug("debug log ={}", name);
		log.info("info log ={}", name);
		log.warn("warn log ={}", name);
		log.error("error log ={}", name);

		// 로그를 사용하지 않아도 A+B 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
		log.trace("String concat log =" + name);
		/**
		 * "String concat log + name string 연산이 일어나서 String 객체 생성!!
		 */

		return "ok";

	}
}
