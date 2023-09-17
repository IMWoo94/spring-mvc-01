package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class ModelView {

	@Getter
	private String viewName;
	private Map<String, Object> model = new HashMap<>();

	public ModelView(String viewName) {
		this.viewName = viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
}
