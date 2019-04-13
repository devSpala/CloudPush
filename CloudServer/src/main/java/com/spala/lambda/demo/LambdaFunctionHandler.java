package com.spala.lambda.demo;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

	@Override
	public String handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		init();
		return "Hello from Lambda Arifin****! ";
	}

	private void init() {

		final String SERVER_KEY = "AAAArXYG-vE:APA91bEElbkFyq4TYHCPmWvb-dstXJv2j_mCzdYMpecWZFQ844vG4SVc4zcAezyn1WSID9HGxSlcOkcnqGuSxllrhVCyPlnfJ0tZrMkm5Se7aW3tjUbTeX3izEy8epFxBT5tqTWZfr7b";
		
		PushMessage.setKey(SERVER_KEY);
		
		
		// create Notification object 
		Notification raven = new Notification();

		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Hello", "World!");
//		data.put("Marco", "Polo");
//		data.put("Foo", "Bar");
		
		// build raven message using the builder pattern
		//to means Token..................................................................................................................................................
		raven.to("fDvLyQZX_sg:APA91bHCtmTCwC_NAqEsU4qT19_MPW_HkAeGogryJbtkrLt0xWQ4fnfkpMKahfoZiGto61BMeNpxbCJZ7KGqd_yJn0jq3KPqW8qPj5hlN8b_V5e1ceWDCkcEGnxTuvklyQBoBWWB2iUy")
			.collapse_key("a_collapse_key")
			.priority(1)
			.delay_while_idle(true)
			.time_to_live(1000)
			.restricted_package_name("com.spala.firebaseclient")
			.dry_run(false)
			.data(data)
			.title("Testing Arif")
			.body("Hello World!")
			.color("#ff0000");
		
		
		

		// push the raven message
		FcmResponse response = PushMessage.push(raven);
		
		// alternatively set static notification first.
//		Pushraven.setNotification(raven);
//		response = Pushraven.push();
		
		// prints response code and message
		System.out.println(response);
	
	}

}
