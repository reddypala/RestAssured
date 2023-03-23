package com.session3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

// https://reqres.in/api/users?page=2&id=5
public class PathAndQueryParameters {

	@Test
	public void testPathAndQueryParameters() {
		given().
			pathParam("mypath", "users").
			queryParam("page", 2).
			queryParam("id", 2)
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200).log().all();
	}

}
