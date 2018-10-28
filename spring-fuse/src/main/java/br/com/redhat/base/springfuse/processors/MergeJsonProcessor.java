package br.com.redhat.base.springfuse.processors;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.json.simple.JsonArray;

public class MergeJsonProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		JsonArray array = new JsonArray(ex.getIn().getBody(List.class));	
		if(ex.getPattern().isOutCapable()) {			
			ex.getOut().setBody(array);
		} else {
			ex.getIn().setBody(array);
		}
	}

}
