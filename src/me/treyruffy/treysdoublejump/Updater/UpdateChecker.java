package me.treyruffy.treysdoublejump.Updater;

import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import me.treyruffy.treysdoublejump.TreysDoubleJump;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class UpdateChecker {

	// The URLs to check for new versions
	final static String VERSION_URL = "https://api.spiget.org/v2/resources/19630/versions?size=" + Integer.MAX_VALUE;
	final static String DESCRIPTION_URL = "https://api.spiget.org/v2/resources/19630/updates?size=" + Integer.MAX_VALUE;

	// Checks for updates
	public static Object[] getLastUpdate() {
        try {
            JSONArray versionsArray = (JSONArray) JSONValue.parseWithException(IOUtils.toString(new URL(String.valueOf(VERSION_URL)), "UTF-8"));
            String lastVersion = ((JSONObject) versionsArray.get(versionsArray.size() - 1)).get("name").toString();

            if ((Integer.parseInt(lastVersion.replaceAll("\\.","")) > (Integer.parseInt(TreysDoubleJump.getPlugin(TreysDoubleJump.class).getDescription().getVersion().replaceAll("\\.",""))))) {
                JSONArray updatesArray = (JSONArray) JSONValue.parseWithException(IOUtils.toString(new URL(String.valueOf(DESCRIPTION_URL)), "UTF-8"));
                String updateName = ((JSONObject) updatesArray.get(updatesArray.size() - 1)).get("title").toString();
   
                Object[] update = {lastVersion, updateName};
                return update;
            }
        }
        catch (Exception e) {
            return new String[0];
        }
		return new String[0];
    }
	
}
