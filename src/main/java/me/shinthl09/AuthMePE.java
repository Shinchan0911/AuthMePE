package me.shinthl09;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.shinthl09.color.color;
import me.shinthl09.event.PlayerJoin;

public class AuthMePE extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.getLogger().info(color.transalate("&e---------------------\n"));
        this.getLogger().info(color.transalate("&aAuthor:&e ShinTHL09"));
        this.getLogger().info(color.transalate("&aPlugin:&e Has Enable"));
        this.getLogger().info(color.transalate("&aName:&e " + this.getName() + "\n"));
        this.getLogger().info(color.transalate("&e---------------------"));
    }

    @Override
    public void onDisable() {
        this.getLogger().info(color.transalate("&e---------------------\n"));
        this.getLogger().info(color.transalate("&aAuthor:&e ShinTHL09"));
        this.getLogger().info(color.transalate("&aPlugin:&e Has Disable"));
        this.getLogger().info(color.transalate("&aName:&e " + this.getName() + "\n"));
        this.getLogger().info(color.transalate("&e---------------------"));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("authmepe")) {
            if (sender.hasPermission("authmepe.admin") || !(sender instanceof Player)) {
                if (args.length == 0) {
                    sender.sendMessage(color.transalate(this.getConfig().getString("Message.Command-Wrong")));
                } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    this.reloadConfig();
                    sender.sendMessage(color.transalate(this.getConfig().getString("Message.Reload-Successful")));
                } else {
                    sender.sendMessage(color.transalate(this.getConfig().getString("Message.Command-Wrong")));
                }
            } else
                sender.sendMessage(color.transalate(this.getConfig().getString("Message.No-Permissions")));
        }
        return true;
    }
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("authmepe")) {
            if (args.length == 1) {
                ArrayList<String> command = new ArrayList<>();
                command.add("reload");
                return command;
            }
        }
        return null;
    }
}
