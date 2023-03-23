package com.session1;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequests {
	int id;

	@Test(priority = 1)
	public void getUser() {
		when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2))
				.body("data[2].last_name", equalTo("Funke")).log().all();
	}

	@Test(priority = 2)
	public void createUser() {
		// preparing the data using java collection hashmap and passed into request
		// body.
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "pavan");
		data.put("job", "trainer");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
		// .then()
		// .statusCode(201).log().all();
	}

	// Here in update user use the id of the post request
	@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Krishna");
		data.put("job", "QualityAnalyst");

		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4)
	void deleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).
		then().statusCode(204);
	}
}
