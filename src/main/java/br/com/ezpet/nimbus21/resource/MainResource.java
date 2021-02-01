package br.com.ezpet.nimbus21.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainResource {

	@GetMapping("/cookie")
	@ResponseStatus(code = HttpStatus.OK)
	public String cookie() {
		return "cookie";
	}
}
