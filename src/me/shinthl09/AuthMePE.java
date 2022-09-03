package me.shinthl09;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.shinthl09.color.color;
import me.shinthl09.event.PlayerJoin;

public class AuthMePE extends JavaPlugin {
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		getLogger().log(Level.INFO,color.transalate("&e---------------------"));
		getLogger().log(Level.INFO,color.transalate(""));
		getLogger().log(Level.INFO,color.transalate("&aAuthor:&e ShinTHL09"));
		getLogger().log(Level.INFO,color.transalate("&aPlugin:&e Has Enable"));
		getLogger().log(Level.INFO,color.transalate(""));
		getLogger().log(Level.INFO,color.transalate("&e---------------------"));
	}
	@Override
	public void onDisable() {
		getLogger().log(Level.INFO,color.transalate("&e---------------------"));
		getLogger().log(Level.INFO,color.transalate(""));
		getLogger().log(Level.INFO,color.transalate("&aAuthor:&e ShinTHL09"));
		getLogger().log(Level.INFO,color.transalate("&aPlugin:&e Has Disable"));
		getLogger().log(Level.INFO,color.transalate(""));
		getLogger().log(Level.INFO,color.transalate("&e---------------------"));
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("authmepe") && sender.hasPermission("authmepe.admin")) {
			if (args.length == 0) {
				String prefix = color.transalate(this.getConfig().getString("Prefix"));
				sender.sendMessage(prefix + color.transalate(this.getConfig().getString("Command-Wrong")));
			} else {
				String prefix = color.transalate(this.getConfig().getString("Prefix"));
				reloadConfig();
				sender.sendMessage(prefix + color.transalate(this.getConfig().getString("Reload-Successful")));
			}
		} else {
			String prefix = color.transalate(this.getConfig().getString("Prefix"));
			sender.sendMessage(prefix + color.transalate(this.getConfig().getString("No-Permissions")));
		}
		return true;
	}
}
