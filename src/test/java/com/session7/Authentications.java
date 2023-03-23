package com.session7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

	@Test
	void testBasicAuthentication() {
		// basic authentication takes two parameters i.e username and password.
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")

		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testPreEmptiveAuthentication() {
		
		// preemptive authentication is a combination of both basic and digest authentication
		given()
			.auth().preemptive().basic("postman", "password")

		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test
	void testBearerTokenAuthentication() {
		
		String bearerToken = "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";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	
}
