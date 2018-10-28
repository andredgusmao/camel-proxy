package br.com.redhat.base.springfuse;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringFuseApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplication(SpringFuseApplication.class).run(args);
		CamelSpringBootApplicationController applicationController =
	            applicationContext.getBean(CamelSpringBootApplicationController.class);
	    applicationController.run();
	}
}
