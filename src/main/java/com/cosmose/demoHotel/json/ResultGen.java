package com.cosmose.demoHotel.json;

import com.google.gson.Gson;

public class ResultGen {

	public static String resultJsonGen(String result) {
		return new Gson().toJson(new ResultJson(result), ResultJson.class);
	}

}
