package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

	/**
	 * 회원 목록 조회: GET /users
	 * 회원 등록: POST /users
	 * 회원 조회: GET /users/{userId}
	 * 회원수정: PATCH /users/{userId}
	 * 회원 삭제: DELETE /users/{userId}
	 */

	/**
	 * GET /mapping/users
	 * @return
	 */
	@GetMapping
	public String users() {
		return "get users";
	}

	/**
	 * POST /mapping/users
	 * @return
	 */
	@PostMapping
	public String addUser() {
		return "post user";
	}

	/**
	 * GET /mapping/users/{userId}
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public String findUser(@PathVariable String userId) {
		return "get userId = " + userId;
	}

	/**
	 * PATCH /mapping/users/{userId}
	 * @param userId
	 * @return
	 */
	@PatchMapping("/{userId}")
	public String updateUser(@PathVariable String userId) {
		return "update userId = " + userId;
	}

	/**
	 * DELETE /mapping/users/{userId}
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId) {
		return "delete userId = " + userId;
	}

}
