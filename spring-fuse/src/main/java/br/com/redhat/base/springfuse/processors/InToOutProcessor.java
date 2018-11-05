package br.com.redhat.base.springfuse.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InToOutProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody(exchange.getIn().getBody());
	}

}
