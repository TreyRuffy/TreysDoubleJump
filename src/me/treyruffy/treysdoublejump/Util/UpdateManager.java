package me.treyruffy.treysdoublejump.Util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.treyruffy.treysdoublejump.TreysDoubleJump;

/**
 * Created by TreyRuffy on 08/12/2018.
 */

public class UpdateManager {
	
	// Updates the config
	public void setup() {
		try {
			File file = new File(TreysDoubleJump.dataFolder, "config.yml");
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
			
			
			if (!config.contains("Velocity.SprintingForward")) {
				int index = lines.indexOf("Velocity:");
				lines.add(index + 1, "    SprintingForward: 1.8");
				lines.add(index + 2, "    # Allows you to set the forward jump velocity while sprinting");
				FileUtils.writeLines(file, lines);
			}
			if (!config.contains("Velocity.SprintingUp")) {
				int index = lines.indexOf("Velocity:");
				lines.add(index + 1, "    SprintingUp: 1.2");
				lines.add(index + 2, "    # Allows you to set the upward jump velocity while sprinting");
				FileUtils.writeLines(file, lines);
			}
			if (!config.contains("DisabledBlocks")) {
				
				lines.addAll(Arrays.asList(
						"DisabledBlocks: []",
						"    # Blocks to disable the double jump, if standing on"));
				
				FileUtils.writeLines(file, lines);
				
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
