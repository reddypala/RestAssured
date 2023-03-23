package com.session7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class OAUTH {
	
	@Test
	void testOauth1(){
		//This is the syntax of using oauth1.0(now this authentication is deprecated)
		given()
			.auth().oauth("consumerKey", "consumerSecret" , "accessToken", "tokensecret")  // The details are provided by developer
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testOauth2(){
		//This is the syntax of using oauth1.0(now this authentication is deprecated)
		given()
			.auth().oauth2("provide access token")  // Here access token is generated manually from the postman
														//these are provided by developers.
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication() {
		// Api key authentication are passed along with query parameters.
		given()
			.queryParam("provide_keyName", "provide_keyValue")
		.when()
			.get("provide_url")
			
		.then()
			.statusCode(200).log().all();
	}
}
