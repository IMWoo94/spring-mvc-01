package hello.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();

		String copyToString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody={}", copyToString);

		response.getWriter().write("ok");

	}

	/**
	 * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
	 * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
	 */
	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
		String copyToString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody={}", copyToString);

		writer.write("ok");

	}

	/**
	 * HttpEntity: HTTP header, body 정보를 편리하게 조회
	 * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
	 * 응답에서도 HttpEntity 사용 가능
	 * - 메시지 바디 정보 직접 반환(view 조회X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
	 */
	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
		String body = httpEntity.getBody();
		log.info("messageBody={}", body);
		return new HttpEntity<>("ok");
	}

	/**
	 * HttpEntity를 상속받아 파생된 객체로
	 * 추가적인 정보를 가진다.
	 * RequestEntity -> Http method, URI 추가
	 * ResponseEntity -> 응답 상태 코드를 가집니다.
	 */
	@PostMapping("/request-body-string-v3-1")
	public ResponseEntity<String> requestBodyStringV0(RequestEntity<String> httpEntity) throws IOException {
		String body = httpEntity.getBody();
		log.info("messageBody={}", body);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * @RequestBody
	 * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
	 * @ResponseBody
	 * - 메시지 바디 정보 직접 반환(view 조회X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
	 */
	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) {
		log.info("messageBody={}", messageBody);
		return "ok";
	}

}
