package com.session3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class LoggingDemo {
	@Test
	public void testLoggingDemo() {
		given()

		.when().get("https://www.google.com/")

		.then()
		.log().all();
		//.log().body();
		//.log().cookies();
		//.log().headers();
	}
}
