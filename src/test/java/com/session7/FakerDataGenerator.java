package com.session7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void testGenerateFakeData() {

		// create a faker class object
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();

		String userName = faker.name().username();
		String password = faker.internet().password();
		String phoneNumber = faker.phoneNumber().cellPhone();
		String email = faker.internet().emailAddress();
		String animalName = faker.animal().name();
		
		System.out.println("Fullname:"+fullname);
		System.out.println("firstName:"+firstName);
		System.out.println("lastName:"+lastName);
		System.out.println("username:"+userName);
		System.out.println("password:"+password);
		System.out.println("phoneNumber:"+phoneNumber);
		System.out.println("email:"+email);
		System.out.println("animalName:"+animalName);
	}
}
