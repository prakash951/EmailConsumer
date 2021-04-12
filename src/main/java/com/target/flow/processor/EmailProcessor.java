package com.target.flow.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String json = exchange.getIn().getBody(String.class);
		Map<String, Object> map = JsonParserFactory.getJsonParser().parseMap(json);
		System.out.println(map);
		System.out.println();
		exchange.getIn().setHeader("subject", map.get("subject"));
		StringBuffer emails = new StringBuffer();
		List<String> list = (List<String>) map.get("to");
		for (String s : list) {
			emails.append(s);
			emails.append(",");
		}
		String email = emails.toString();
		email = email.substring(0, email.length() - 1);
		exchange.getIn().setHeader("to", emails);
		exchange.getIn().setBody(json);
	}

}
