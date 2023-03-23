package com.session3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test
	public void testHeaders() {
		given()

				.when().get("https://www.google.com/")

				.then().header("content-type", "text/html; charset=ISO-8859-1").header("Content-Encoding", "gzip").and()
				.header("Server", "gws");

	}

	@Test
	public void testGetHeadersInformation() {
		Response response = given().when().get("https://www.google.com/");

		// get single header information
		String header_value = response.getHeader("content-type");
		System.out.println("content-type:" + header_value);
		// capture all headers information
		Headers headers = response.getHeaders();
		for(Header hdr:headers) {
			System.out.println(hdr.getName()+" : "+hdr.getValue());
		}

	}
}
