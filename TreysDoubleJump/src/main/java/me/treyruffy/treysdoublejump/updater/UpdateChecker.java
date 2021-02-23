package me.treyruffy.treysdoublejump.updater;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TreyRuffy on 02/1/2020.
 */

public class UpdateChecker {

	public static String request(final String RESOURCE_ID, final String PLUGIN_NAME) {
		
			final String REQUEST_URL = "https://api.spiget.org/v2/resources/" + RESOURCE_ID + "/versions?size=" + Integer.MAX_VALUE + "&sort=-releaseDate";
			try {
				URL url = new URL(REQUEST_URL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET");
	            connection.addRequestProperty("User-Agent", PLUGIN_NAME);
				
				InputStream inputStream = connection.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				
				JsonElement element = new JsonParser().parse(reader);
	
				if(!element.isJsonArray()) {
					return "";
				}
				
				reader.close();
				
				JsonObject latestVersion = element.getAsJsonArray().get(0).getAsJsonObject();
				
				return latestVersion.get("name").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
	}
	
	@SuppressWarnings("unused")
	public static String request(final String RESOURCE_ID) {
		return request(RESOURCE_ID, "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
	}
	
}