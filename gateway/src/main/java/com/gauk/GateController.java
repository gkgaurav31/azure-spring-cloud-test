package com.gauk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GateController {

	@Value("${application.message:Not configured by a Spring Cloud Server}")
    private String message;
	
	@GetMapping("/test")
	public String test() {
		return message;
	}
	
	@Autowired
	Environment environment;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPort() {
		return "GATEWAY SERVICE PORT: " + environment.getProperty("local.server.port");
	}


}
