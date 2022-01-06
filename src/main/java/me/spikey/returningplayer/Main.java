package me.spikey.returningplayer;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    private ArrayList<UUID> bypassFull;

    @Override
    public void onEnable() {
        this.bypassFull = Lists.newArrayList();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void prelogin(AsyncPlayerPreLoginEvent event) {
        if (!Bukkit.getOfflinePlayer(event.getUniqueId()).hasPlayedBefore() && event.getLoginResult().equals(AsyncPlayerPreLoginEvent.Result.ALLOWED)) bypassFull.add(event.getUniqueId());
    }


    @EventHandler
    public void login(PlayerLoginEvent event) {

        if (event.getResult().equals(PlayerLoginEvent.Result.KICK_FULL) && bypassFull.contains(event.getPlayer().getUniqueId())) {
            event.allow();
            event.setResult(PlayerLoginEvent.Result.ALLOWED);
        }
        bypassFull.remove(event.getPlayer().getUniqueId());
    }


}
