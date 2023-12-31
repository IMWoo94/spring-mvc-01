package hello.springmvc.basic.requestmapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MappingController {

	/**
	 * 기본요청
	 * 둘다 허용 /hello-basic, /hello-basic/
	 * 스프링 부트 3.0 의 경우에는 서로 다른 URL 이다
	 * HTTP 메소드 모두 혀용
	 */
	@RequestMapping("/hello-basic")
	public String helloBasic() {
		log.info("helloBasic");
		return "ok";
	}

	/**
	 * method 특정 HTTP 메서드 요청만 허용
	 * 이 외에 메소드 요청 시  HTTP 405 method not allowed 를 반환
	 * @return
	 */
	@RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
	public String mappingGetV1() {
		log.info("mappingGetV1");
		return "ok";
	}

	/**
	 * 편리한 축약 애노테이션 (코드보기) * @GetMapping
	 * @PostMapping
	 * @PutMapping
	 * @DeleteMapping
	 * @PatchMapping
	 */
	@GetMapping(value = "/mapping-get-v2")
	public String mappingGetV2() {
		log.info("mapping-get-v2");
		return "ok";
	}

	/**
	 * PathVariable 사용
	 * 변수명이 같으면 생략 가능
	 * @PathVariable("userId") String userId -> @PathVariable userId
	 */
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String data) {
		log.info("mappingPath userId = {}", data);
		return "ok";
	}

	/**
	 * PathVariable 사용 다중
	 */
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
		log.info("mappingPath userId={}, orderId={}", userId, orderId);
		return "ok";
	}

	/**
	 * 파라미터로 추가 매핑
	 * params="mode",
	 * params="!mode"
	 * params="mode=debug"
	 * params="mode!=debug" (! = )
	 * params = {"mode=debug","data=good"}
	 * 조건추가 특정 파라미터가 있는 경우에만 실행
	 */
	@GetMapping(value = "/mapping-param", params = "mode=debug")
	public String mappingParam() {
		log.info("mappingParam");
		return "ok";
	}

	/**
	 * 특정 헤더로 추가 매핑
	 * headers="mode",
	 * headers="!mode"
	 * headers="mode=debug"
	 * headers="mode!=debug" (! = )
	 */
	@GetMapping(value = "/mapping-header", headers = "mode=debug")
	public String mappingHeader() {
		log.info("mappingHeader");
		return "ok";
	}

	/**
	 * Content-Type 헤더 기반 추가 매핑 Media Type
	 * consumes="application/json"
	 * consumes="!application/json"
	 * consumes="application/*"
	 * consumes="*\/*"
	 * MediaType.APPLICATION_JSON_VALUE
	 */
	// @PostMapping(value = "/mapping-consume", consumes = "application/json")
	@PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String mappingConsumes() {
		log.info("mappingConsumes");
		return "ok";
	}

	/**
	 * Accept 헤더 기반 Media Type * produces = "text/html"
	 * produces = "!text/html"
	 * produces = "text/*"
	 * produces = "*\/*"
	 */
	// @PostMapping(value = "/mapping-produce", produces = "text/html")
	@PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
	public String mappingProduces() {
		log.info("mappingProduces");
		return "ok";
	}
}
