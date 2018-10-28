package br.com.redhat.base.springfuse.aggregators;

import org.apache.camel.Exchange;
import org.apache.camel.json.simple.JsonArray;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MergeJsonAggregator implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		Object old = oldExchange.getIn().getBody();
		Object newz = newExchange.getIn().getBody();
		
		JsonArray json = new JsonArray();
		json.add(old);
		json.add(newz);
		
		if(newExchange.getPattern().isOutCapable()) {
			newExchange.getOut().setBody(json);
		} else {
			newExchange.getIn().setBody(json);
		}
		return newExchange;
	}

}
