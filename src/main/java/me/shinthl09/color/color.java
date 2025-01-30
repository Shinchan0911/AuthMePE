package me.shinthl09.color;

import me.shinthl09.AuthMePE;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class color {
    private static Plugin plugin = AuthMePE.getPlugin(AuthMePE.class);
    public static String transalate(String message) {
        return ChatColor.translateAlternateColorCodes('&', (message.replace("{prefix}", plugin.getConfig().getString("Setting.Prefix"))));

    }
}
