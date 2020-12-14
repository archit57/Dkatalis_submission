package com.dkatalis.testCases;

import java.net.UnknownHostException;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dkatalis.base.TestBase;
import com.dkatalis.utilities.APIResuableMethods;
import com.dkatalis.utilities.CustomJsonParser;
import com.dkatalis.utilities.GetList_FileReading;
import com.dkatalis.utilities.LoadPropertyWithSingleton;
import com.dkatalis.utilities.NumericNodeComparator;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.UserDetails;

public class PrintArrayListfromFileTest extends TestBase {
	RequestSpecification httpRequest;
	Response response1, response2;
	NumericNodeComparator cmp;
	JSONParser parser;

	@BeforeClass
	void settinginitialinfo() throws InterruptedException, UnknownHostException {
		logger.info("********* Started Reading File  **********");
		httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Cache-Control", "no-cache");
		cmp = new NumericNodeComparator();
		parser = new JSONParser();
	}

	@Test(dataProvider = "getData")
	void printarraylist_file(String path1, String path2) throws InterruptedException, ParseException {
		if (path1 == null || path2 == null) {
			System.out.println("No file to compare  with");
			logger.warn("No file to compare  with");
			throw new SkipException("Skipping the test");
		}

		logger.info("*********Hitting the API of 1st file **********");
		if (!APIResuableMethods.netIsAvailable()) {
			System.out.println("Internet Connection not available");
			logger.warn("Internet Connection not available");
			throw new SkipException("Skipping the test");
		}

		response1 = httpRequest.request(Method.GET, path1);
		APIResuableMethods.checksuccessstatusLine(response1);
		logger.info("*********Got Response from URI of 1st file **********");
		APIResuableMethods.waitforrequesttohit(2000);
		logger.info("*********Hitting the API of 2nd file **********");
		response2 = httpRequest.request(Method.GET, path2);
		logger.info("*********Got Response from URI of 2nd file **********");
		String responseBody1 = response1.getBody().asString();
		String responseBody2 = response2.getBody().asString();
		System.out.println("responseBody is :" + responseBody1);
		UserDetails userDetails = CustomJsonParser.convertJsonStringToObject(responseBody1);
		UserDetails userDetails1 = CustomJsonParser.convertJsonStringToObject(responseBody2);

		int value = cmp.compare(userDetails, userDetails1);
		if (value == 1) {
			System.out.println(path1 + " is equal to " + path2);
			logger.info(path1 + " is equal to " + path2);

		} else {
			System.out.println(path1 + " is not equal to  " + path2);
			logger.info(path1 + " is not equal to " + path2);
		}

	}

	@DataProvider
	public Object[][] getData() throws Exception {
		String sublocation = System.getProperty("user.dir");
		String location1 = sublocation + LoadPropertyWithSingleton.getinstance().getvaluefromproperty("file1location");
		String location2 = sublocation + LoadPropertyWithSingleton.getinstance().getvaluefromproperty("file2location");
		List<String> list1 = GetList_FileReading.filereader(location1);
		List<String> list2 = GetList_FileReading.filereader(location2);
		int size = (list1.size() > list2.size()) ? list1.size() : list2.size();
		// Rows - Number of times your test has to be repeated.
		// Columns - Number of parameters in test data.
		Object[][] data = new Object[size][2];

		for (int i = 0; i < list1.size(); i++)
			data[i][0] = list1.get(i);

		for (int j = 0; j < list2.size(); j++)
			data[j][1] = list2.get(j);
		return data;
	}

	@AfterTest
	void tearDown() {
		logger.info("********* Comparing API Response from 2 files Ended**********");
	}

}