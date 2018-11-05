package br.com.redhat.base.springfuse.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractPropertyProcessor implements Processor {

	private String property;
	
	public ExtractPropertyProcessor(String property) {
		this.property = property;
	}
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(body, new TypeReference<Map<String,Object>>(){});
		exchange.getIn().setHeader(this.property, jsonMap.get(this.property));
	}

}
