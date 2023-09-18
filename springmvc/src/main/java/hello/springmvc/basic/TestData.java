package hello.springmvc.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TestData {
	private String username;
	private Integer test;

	private TestData() {
		System.out.println("TestData.class Contructor");
	}
}
