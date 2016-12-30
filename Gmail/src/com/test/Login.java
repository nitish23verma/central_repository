package com.test;

import org.testng.annotations.Test;

import com.pom.HomePage;
import config.TestConfig;

public class Login extends TestConfig {
	@Test
	public void test() {

		HomePage h1 = new HomePage(driver);
		h1.clickLogoutGem();
		h1.clickSignOut();

	}

}