package me.shinthl09.color;

import org.bukkit.ChatColor;

public class color {
	public static String transalate(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
