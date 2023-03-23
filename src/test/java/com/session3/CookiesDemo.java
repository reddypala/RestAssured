package com.session3;

import static io.restassured.RestAssured.given;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test(priority = 1)
	public void testCookies() {

		given()

				.when().get("https://www.google.com/")

				.then().cookie("AEC", "ARSKqsKDP15zbAzfvlkKT6QAnG-IeT3FsCnZO_MhCxVhG3acPRwW3TtCnw").log().all();
	}

	@Test(priority = 2)
	public void getCookiesInfo() {

		Response response = given().when().get("https://www.google.com/");
		// extract information from the response
		// How to get single cookie information
//		String cookie_value = response.getCookie("AEC");
//		System.out.println("value of the cookie is:"+cookie_value);

		//get all cookies information from the response
		Map<String,String> cookie_values=response.getCookies();
		Set<String> keyset=cookie_values.keySet();
		System.out.println(keyset);
		Collection<String> valueset=cookie_values.values();
		System.out.println(valueset);

		for(String k:cookie_values.keySet()) {
			String cookie =response.getCookie(k);
			System.out.println("k:"+"     "+cookie);
		}

	}
}
