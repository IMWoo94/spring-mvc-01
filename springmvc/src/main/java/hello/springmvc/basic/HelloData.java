package hello.springmvc.basic;

import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 을 자동으로 적용 해준다.
public class HelloData {
	private String username;
	private int age;
	private int test;
}
