package com.session6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

// https://jsonformatter.org/json-to-jsonschema      --> JsonSchema converter
public class JsonSchemaValidation {

	@Test
	void jsonSchemaValidation() {

		given()

		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
	}
}
