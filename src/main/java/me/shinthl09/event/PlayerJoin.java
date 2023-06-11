package me.shinthl09.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import fr.xephi.authme.api.v3.AuthMeApi;
import me.shinthl09.AuthMePE;
import me.shinthl09.color.color;

public class PlayerJoin implements Listener {
    private Plugin plugin = AuthMePE.getPlugin(AuthMePE.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        (new BukkitRunnable() {
            public void run() {
                if (FloodgateApi.getInstance().isFloodgatePlayer(e.getPlayer().getUniqueId())) {
                    if ((AuthMeApi.getInstance().isRegistered(e.getPlayer().getName()))) {
                        if (!AuthMeApi.getInstance().isAuthenticated(e.getPlayer())) {
                            if (plugin.getConfig().getBoolean("Setting.Skip-AuthMe-Geyser")) {
                                p.sendMessage(color.transalate(plugin.getConfig().getString("Message.Auto-Login")));
                                AuthMeApi.getInstance().forceLogin(p);
                            } else if (plugin.getConfig().getBoolean("Setting.Menu-AuthMe-Geyser")) {
                                FloodgatePlayer floodplayer = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
                                floodplayer.sendForm(((CustomForm.Builder) ((CustomForm.Builder) CustomForm.builder()
                                        .title(color.transalate(plugin.getConfig().getString("Menu.Title-Login"))))
                                        .input(color.transalate(plugin.getConfig().getString("Menu.Title-Login-Password")),
                                                color.transalate(
                                                        plugin.getConfig().getString("Menu.Input-Login-Placeholder")))
                                        .responseHandler((form, responseData) -> {
                                            CustomFormResponse loginform = form.parseResponse(responseData);
                                            if (!loginform.isCorrect())
                                                p.kickPlayer(color.transalate(
                                                        plugin.getConfig().getString("Message.Not-Enough")));
                                            String password = loginform.getInput(0);
                                            if (AuthMeApi.getInstance().checkPassword(p.getName(), password)) {
                                                AuthMeApi.getInstance().forceLogin(p);
                                            } else
                                                p.kickPlayer(color.transalate(
                                                        plugin.getConfig().getString("Message.Wrong-Password")));
                                        })).build());
                            }
                        }
                    } else {
                        if (plugin.getConfig().getBoolean("Setting.Skip-AuthMe-Geyser")) {
                            AuthMeApi.getInstance().forceRegister(p,
                                    plugin.getConfig().getString("Setting.Password-Login-Geyser"), true);
                            p.sendMessage(color.transalate(plugin.getConfig().getString("Message.Auto-Register")));
                        } else if (plugin.getConfig().getBoolean("Setting.Menu-AuthMe-Geyser")) {
                            FloodgatePlayer floodplayer = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
                            floodplayer.sendForm(((CustomForm.Builder) ((CustomForm.Builder) CustomForm.builder()
                                    .title(color.transalate(plugin.getConfig().getString("Menu.Title-Register"))))
                                    .input(color.transalate(plugin.getConfig().getString("Menu.Title-Register-Password")),
                                            color.transalate(
                                                    plugin.getConfig().getString("Menu.Input-Register-Placeholder")))
                                    .input(color.transalate(plugin.getConfig().getString("Menu.Title-Register-RePassword")),
                                            color.transalate(
                                                    plugin.getConfig().getString("Menu.Input-Register-RePlaceholder")))
                                    .responseHandler((form, responseData) -> {
                                        CustomFormResponse registerform = form.parseResponse(responseData);
                                        if (!registerform.getToggle(2)) {
                                            p.kickPlayer(color
                                                    .transalate(plugin.getConfig().getString( "Message.Not-Toggle")));
                                        }
                                        if (!registerform.isCorrect())
                                            p.kickPlayer(color
                                                    .transalate(plugin.getConfig().getString("Message.Not-Enough")));
                                        String password = registerform.getInput(0);
                                        String repassword = registerform.getInput(1);
                                        if (repassword.equals(password)) {
                                            AuthMeApi.getInstance().forceRegister(p, password, true);
                                        } else
                                            p.kickPlayer(color
                                                    .transalate(plugin.getConfig().getString("Message.Not-Same")));
                                    })).build());
                        }
                    }
                } else {
                    return;
                }
            }
        }).runTaskLater(plugin, plugin.getConfig().getLong("Setting.Delay-Open-Menu") * 20L);
    }
}
