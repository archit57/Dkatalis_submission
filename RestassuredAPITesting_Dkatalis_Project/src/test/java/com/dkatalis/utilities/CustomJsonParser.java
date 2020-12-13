package com.dkatalis.utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pojo.Data;
import pojo.Support;
import pojo.UserDetails;

public class CustomJsonParser {

	public static UserDetails convertJsonStringToObject(String responseBody) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject inner = (JSONObject) json.get("data");

		Long id = (Long) inner.get("id");
		String email = (String) inner.get("email");
		String firstName = (String) inner.get("first_name");
		String lastName = (String) inner.get("last_name");
		String avatar = (String) inner.get("avatar");

		JSONObject innersub1 = (JSONObject) json.get("support");
		String url = (String) innersub1.get("url");
		String text = (String) innersub1.get("text");

		Data data = new Data(id, email, firstName, lastName, avatar);
		Support support = new Support(url, text);
		UserDetails userDetails = new UserDetails(data, support);
		return userDetails;

	}

}
