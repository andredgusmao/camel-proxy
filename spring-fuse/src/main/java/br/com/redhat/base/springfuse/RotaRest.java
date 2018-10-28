package br.com.redhat.base.springfuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RotaRest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.host("localhost")
        	.port("8081") 
        	.contextPath("api")
        	.enableCORS(true)
			.component("restlet")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("prettyPrint", "true");
		
		from("rest:get:simple:hello")
			.to("direct:simple-status");
		
		from("rest:post:simple:new")
			.to("direct:simple-post");
		
		from("rest:get:simple/basic")
			.to("direct:simple-basic-enrich");
	}

}
