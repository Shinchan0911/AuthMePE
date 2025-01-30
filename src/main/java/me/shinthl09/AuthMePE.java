package me.shinthl09;

import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.shinthl09.color.color;
import me.shinthl09.event.PlayerJoin;

import java.util.ArrayList;
import java.util.List;

public class AuthMePE extends JavaPlugin {
    
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        logMessage("&e---------------------");
        logMessage("&aAuthor:&e ShinTHL09");
        logMessage("&aPlugin:&e Has Enable");
        logMessage("&aName:&e " + this.getName());
        logMessage("&aVersion:&e 1.1.2");
        logMessage("&aAdd support folia by Tuanvo0022");
        logMessage("&e---------------------");
    }

    @Override
    public void onDisable() {
        logMessage("&e---------------------");
        logMessage("&aAuthor:&e ShinTHL09");
        logMessage("&aPlugin:&e Has Disable");
        logMessage("&aName:&e " + this.getName());
        logMessage("&aVersion:&e 1.1.2");
        logMessage("&aAdd support folia by Tuanvo0022");        
        logMessage("&e---------------------");
        Bukkit.getGlobalRegionScheduler().cancelTasks(this);
    }

    private void logMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(color.transalate(message));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("authmepe")) {
            handleAuthMeCommand(sender, args);
        }
        return true;
    }

    private void handleAuthMeCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("authmepe.admin") || !(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(color.transalate(getConfig().getString("Message.Command-Wrong")));
            } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                reloadConfiguration(sender);
            } else {
                sender.sendMessage(color.transalate(getConfig().getString("Message.Command-Wrong")));
            }
        } else {
            sender.sendMessage(color.transalate(getConfig().getString("Message.No-Permissions")));
        }
    }

    private void reloadConfiguration(CommandSender sender) {
        reloadConfig();
        sender.sendMessage(color.transalate(getConfig().getString("Message.Reload-Successful")));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("authmepe") && args.length == 1) {
            ArrayList<String> command = new ArrayList<>();
            command.add("reload");
            return command;
        }
        return null;
    }
}
