package com.session4;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
// parsing of json response means traversing through the json response to get the required fields or data from the json.
public class ParsingJsonResponseData {

	@Test(priority = 1)
	void testJsonResponse() {

		// Approach1(very minimal validations are supported in this approach)

		/*
		 * given().contentType(Content-type.JSON).
		 * when().get(" http://localhost:3000/store"). then().header("Content-type",
		 * "application/json; charset=utf-8") .body("book[3].title",
		 * equalTo("The lord of the rings")).statusCode(200);
		 */

		// Approach2 (get the response into a variable and do validations on response)-
		// very useful approach.
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");

		Assert.assertEquals(res.getStatusCode(), 200); // validation1
		Assert.assertEquals(res.header("Content-type"), "application/json; charset=utf-8");

		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The lord of the rings");

	}

	@Test(priority = 2)
	void testJsonResponseBodyData() {

		// Approach2 (get the response into a variable and do validations on response)-
		// very useful approach.
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");
		            //OR
		//String res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store").asString();

		/*
		 * Assert.assertEquals(res.getStatusCode(), 200); //validation1
		 * Assert.assertEquals(res.header("Content-type"),
		 * "application/json; charset=utf-8");
		 *
		 * String bookName=res.jsonPath().get("book[3].title").toString();
		 * Assert.assertEquals(bookName,"The lord of the rings");8
		 */

		// To traverse/parse through the entire json response, JSONObject class is used
		// search for title of the book in json
		JSONObject jo = new JSONObject(res.asString()); // converting response to JSONObject type
		Boolean status = false;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("The lord of the rings")) {
				status = true;
				break;
			}

		}
		Assert.assertEquals(status, true);

		//total price of the books
		double totalPrice = 0;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();

			totalPrice = totalPrice+Double.parseDouble(price);

		}
		System.out.println("Total price of books:"+totalPrice);
		Assert.assertEquals(totalPrice,526.0);
	}

	/*InterviewQuestion:How to parse the json response data?
			Answer: By using "JSONObject" class we can parse the json response.*/
}
