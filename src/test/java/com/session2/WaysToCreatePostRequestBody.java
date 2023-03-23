package com.session2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

/* Different ways to create POST request body
 * 1. By using HashMap
 * 2. By using org.json
 * 3. By using POJO classes
 * 4. By using external json file.
 * */

public class WaysToCreatePostRequestBody {
	int id;

	/*
	 * @Test(priority = 1) void testPostUsingHashMap() { // use hashmap for small
	 * set of data. HashMap data = new HashMap(); data.put("name", "scott");
	 * data.put("location", "France"); data.put("phone", "123456"); // to enter
	 * array into hashmap. String courseArr[] = { "c", "c++" };
	 *
	 * data.put("courses", courseArr);
	 *
	 * String response =
	 * given().contentType("application/json").body(data).when().post(
	 * "http://localhost:3000/students").then() .statusCode(201).body("name",
	 * equalTo("scott")).body("location", equalTo("France")) .body("phone",
	 * equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]",
	 * equalTo("c++")) .header("content-type",
	 * "application/json; charset=utf-8").log().all().extract().response().asString(
	 * ); JsonPath js = new JsonPath(response); id = js.getInt("id");
	 * System.out.println("The id created is:"+id);
	 *
	 * }
	 */

	// deleting the student record
	@Test(priority = 2)
	void testDelete() {
		given().when().delete("http://localhost:3000/students/" + id).then().statusCode(200).log().all();
	}

	/*
	 * @Test(priority = 1) void testPostUsingJsonLibrary() { //create an object of
	 * JSONObject class JSONObject data = new JSONObject(); data.put("name",
	 * "scott"); data.put("location", "France"); data.put("phone", "123456"); String
	 * courseArr[] = { "c", "c++" };
	 *
	 * data.put("courses", courseArr);
	 *
	 * String response =
	 * given().contentType("application/json").body(data.toString()).when().post(
	 * "http://localhost:3000/students").then() .statusCode(201).body("name",
	 * equalTo("scott")).body("location", equalTo("France")) .body("phone",
	 * equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]",
	 * equalTo("c++")) .header("content-type",
	 * "application/json; charset=utf-8").log().all().extract().response().asString(
	 * ); JsonPath js = new JsonPath(response); id = js.getInt("id");
	 * System.out.println("The id created is:"+id); }
	 */

	/*
	 * @Test(priority = 1) void testPostUsingPOJO() { //create an object of POJO
	 * Class
	 *
	 * POJO_PostRequest data = new POJO_PostRequest();
	 *
	 * data.setName("scott"); data.setLocation("France"); data.setPhone("123456");
	 *
	 * String coursesArr[] = {"c","c++","python"};
	 *
	 * data.setCourses(coursesArr); String response =
	 * given().contentType("application/json").body(data).when().post(
	 * "http://localhost:3000/students").then() .statusCode(201).body("name",
	 * equalTo("scott")).body("location", equalTo("France")) .body("phone",
	 * equalTo("123456")).body("courses[0]", equalTo("c")).body("courses[1]",
	 * equalTo("c++")) .header("content-type",
	 * "application/json; charset=utf-8").log().all().extract().response().asString(
	 * ); JsonPath js = new JsonPath(response); id = js.getInt("id");
	 * System.out.println("The id created is:"+id);
	 *
	 *
	 * }
	 */

	@Test(priority = 1)
	void testPostUsingExternalJsonFile() throws IOException {

		File file  = new File(".//body.json");   // open the file
		FileReader fr  = new FileReader(file);   // read the file
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);


		String response = given().contentType("application/json").body(data.toString()).when()
				.post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("scott"))
				.body("location", equalTo("France")).body("phone", equalTo("123456")).body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++")).header("content-type", "application/json; charset=utf-8").log()
				.all().extract().response().asString();
		JsonPath js = new JsonPath(response);
		id = js.getInt("id");
		System.out.println("The id created is:" + id);
	}

}
