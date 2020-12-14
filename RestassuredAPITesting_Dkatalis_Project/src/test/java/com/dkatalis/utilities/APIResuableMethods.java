package com.dkatalis.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

	public static boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}
}
