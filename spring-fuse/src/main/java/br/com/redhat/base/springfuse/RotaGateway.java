package br.com.redhat.base.springfuse;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.processor.aggregate.GroupedBodyAggregationStrategy;
import org.springframework.stereotype.Component;

import br.com.redhat.base.springfuse.aggregators.MergeJsonAggregator;
import br.com.redhat.base.springfuse.processors.MergeJsonProcessor;

@Component
public class RotaGateway extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().producerComponent("http4").bindingMode(RestBindingMode.json);
		
		from("direct:simple-status")
			.to("rest:get:simple/status?host={{url.rest.server}}");
		
		from("direct:simple-post")
			.to("rest:post:simple?host={{url.rest.server}}");
		
		from("direct:simple-basic")
			.to("rest:get:basic/1?host={{url.rest.server}}");
		
		from("direct:simple-basic-complex")
			.multicast(new GroupedBodyAggregationStrategy())
				.parallelProcessing()
				.enrich("direct:simple-status")
				.enrich("direct:simple-basic")
			.end()
			.log("${body}")
			.process(new MergeJsonProcessor())
		.end();
		
		from("direct:simple-basic-enrich")
			.to("direct:simple-status")
			.enrich("direct:simple-basic", new MergeJsonAggregator())
		.end();
			
	}

}
