package hello.springmvc.basic.response;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Controller
@RestController
public class ResponseBodyController {

	@GetMapping("/response-body-string-v1")
	public void responseBodyV1(HttpServletResponse response) throws IOException {
		response.getWriter().write("ok");
	}

	@GetMapping("/response-body-string-v2")
	public ResponseEntity<String> responseBodyV2() {
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	// @ResponseBody
	@GetMapping("/response-body-string-v3")
	public String responseBodyV3() {
		return "OK";
	}

	@GetMapping("/response-body-json-v1")
	public ResponseEntity<HelloData> responseBodyJsonV1() {
		HelloData helloData = new HelloData();
		helloData.setAge(20);
		helloData.setUsername("asdasd");

		return new ResponseEntity<>(helloData, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/response-body-json-v2")
	// @ResponseBody
	public HelloData responseBodyJsonV2() {
		HelloData helloData = new HelloData();
		helloData.setAge(20);
		helloData.setUsername("asdasd");

		return helloData;
	}
}

