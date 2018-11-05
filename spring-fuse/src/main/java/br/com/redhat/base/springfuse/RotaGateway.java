package br.com.redhat.base.springfuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.processor.aggregate.GroupedBodyAggregationStrategy;
import org.springframework.stereotype.Component;

import br.com.redhat.base.springfuse.aggregators.MergeJsonAggregator;
import br.com.redhat.base.springfuse.processors.ExtractPropertyProcessor;
import br.com.redhat.base.springfuse.processors.InToOutProcessor;
import br.com.redhat.base.springfuse.processors.MergeJsonProcessor;

@Component
public class RotaGateway extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().producerComponent("http4").bindingMode(RestBindingMode.json);
		
		from("direct:simple-status")
			.to("rest:get:simple/status?host={{url.simple.rest.server}}");
		
		from("direct:simple-post")
			.to("rest:post:simple?host={{url.simple.rest.server}}");
	
		from("direct:simple-get")
			.toD("rest:get:simple:${header.id}?host={{url.simple.rest.server}}");
	
		from("direct:basic-post")
			.to("rest:post:basic?host={{url.simple.rest.server}}");
		
		from("direct:basic-get")
			.toD("rest:get:basic:${header.id}?host={{url.simple.rest.server}}");
		
		from("direct:simple-basic")
			.to("rest:get:basic/1?host={{url.simple.rest.server}}");
		
		from("direct:simple-basic-enrich")
			.to("direct:simple-status")
			.enrich("direct:simple-basic", new MergeJsonAggregator())
		.end();
		
		from("direct:simple-basic-split")
			.split().jsonpath("$.*")
			.log("${body}")
			.to("direct:simple-basic-choice");
		
		from("direct:simple-basic-choice")
			.choice()	
			.when().jsonpath("[?(@.simple)]")
				.to("direct:simple-post")
			.when().jsonpath("[?(@.basic)]")
				.to("direct:basic-post")
			.otherwise()
				.to("mock:choice");
		
		from("direct:simple-chained")
			.to("direct:simple-get")
			.log("${body}")
			.process(new ExtractPropertyProcessor("id"))
			.enrich("direct:basic-get")
			.log("${body}")
			.process(new MergeJsonProcessor())
			.process(new InToOutProcessor());
		
		from("direct:simple-basic-complex")
			.multicast(new GroupedBodyAggregationStrategy())
				.parallelProcessing()
					.enrich("direct:simple-status")
					.enrich("direct:simple-basic")
				.end()
				.log("${body}")
				.process(new MergeJsonProcessor())
			.end();
			
	}

}
