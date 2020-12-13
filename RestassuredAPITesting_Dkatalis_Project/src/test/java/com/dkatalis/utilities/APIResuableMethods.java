package com.dkatalis.utilities;

import org.testng.Assert;

import io.restassured.response.Response;

public class APIResuableMethods {
	public static void checksuccessstatusLine(Response response) {
		String statusLine = response.getStatusLine(); // get the status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	public static void waitforrequesttohit(int timeinmilli) throws InterruptedException {
		Thread.sleep(timeinmilli);

	}

}
