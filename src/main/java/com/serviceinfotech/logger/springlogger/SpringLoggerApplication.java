package com.serviceinfotech.logger.springlogger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoggerApplication.class, args);
	}

	@RestController
	@RefreshScope
	class ExampleController {

		@Value("${foo.bar}")
		private String value;

		@RequestMapping
		public String sayValue() {
			return value;
		}
	}

}
