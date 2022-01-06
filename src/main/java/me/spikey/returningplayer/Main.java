package me.spikey.returningplayer;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        if (!event.getLoginResult().equals(AsyncPlayerPreLoginEvent.Result.KICK_FULL)) return;
        if (Bukkit.getOfflinePlayer(event.getUniqueId()).hasPlayedBefore()) event.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
    }
}
