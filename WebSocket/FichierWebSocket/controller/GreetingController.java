package com.fivePoints.webSocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.fivePoints.webSocket.Model.Greeting;
import com.fivePoints.webSocket.Model.HelloMessage;

@Controller
public class GreetingController {

	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000);
		return new Greeting("Hello"+HtmlUtils.htmlEscape(message.getName())+ "!");
	}
}
