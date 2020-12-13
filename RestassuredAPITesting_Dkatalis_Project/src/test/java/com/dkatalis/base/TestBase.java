/*
 * Author: Archit Agarwal
 * summary: Used Test case for logging the status using Log4j
 * Date: 12/13/2020
 */

package com.dkatalis.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
//	public static Response response;

	public Logger logger;

	@BeforeClass
	public void setup() {

		logger = Logger.getLogger("Reqres API");// added Logger
		PropertyConfigurator.configure("Log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);

	}

}
