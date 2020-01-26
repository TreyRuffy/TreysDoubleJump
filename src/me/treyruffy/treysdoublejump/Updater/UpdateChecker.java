package me.treyruffy.treysdoublejump.Updater;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.treyruffy.treysdoublejump.TreysDoubleJump;

/**
 * Created by TreyRuffy on 08/12/2018.
 * Updated 01/26/2020.
 */

public class UpdateChecker {

	// The URLs to check for new versions
	final static String VERSION_URL = "https://api.spiget.org/v2/resources/19630/versions?size=" + Integer.MAX_VALUE;
	final static String DESCRIPTION_URL = "https://api.spiget.org/v2/resources/19630/updates?size=" + Integer.MAX_VALUE;
	
	// Checks for updates
	protected static String getLastUpdate(TreysDoubleJump plugin) {
		String currentVersion = plugin.getDescription().getVersion();
		try {
			JsonParser parser =  new JsonParser();
			JsonArray versions = parser.parse(IOUtils.toString(new URL(VERSION_URL), Charset.defaultCharset())).getAsJsonArray();
			String lastVersion = versions.get(versions.size() - 1).getAsJsonObject().get("name").getAsString();
			if (!lastVersion.equalsIgnoreCase(currentVersion)) {
				return lastVersion;
			}
			return "";
		} catch (Exception e) {
			return "";
		}
		
	}
}
