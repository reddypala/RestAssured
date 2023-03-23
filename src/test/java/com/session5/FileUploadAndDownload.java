package com.session5;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test
	void singleFileUpload() {

		File myfile1 = new File("C:\\Users\\varar\\Documents\\data.txt");
		given()
			.multiPart("file",myfile1)
			.contentType("multipart/form-data")
		.when()
			.post(" http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("data.txt"))

			.log().all();
	}
	@Test
	void testMultipleFileUpload() {

		File myfile1 = new File("C:\\Users\\varar\\Documents\\data.txt");
		File myfile2 = new File("C:\\Users\\varar\\Documents\\real.txt");
		given()
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")
		.when()
			.post(" http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("data.txt"))
			.body("[1].fileName", equalTo("real.txt"))
			.log().all();
	}

	@Test
	void testFileDownload() {
		when().get("http://localhost:8080/downloadFile/data.txt")
		.then().statusCode(200).log().body();
	}
}
