package com.zip.core;

import com.zip.core.utility.DBControl;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Events implements Listener {
    DBControl control;
    Core core;
    public Events(Core core) {
        control = core.control;
        this.core = core;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //new players are teleported to spawn
        if (!player.hasPlayedBefore()) {
            player.teleport(new Location(Bukkit.getWorld("world"), 0.5,1,0.5));
        }
        CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(event.getPlayer());
        event.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&7[&f" + metaData.getPrefix() + "&f&7] &f" + event.getPlayer().getDisplayName()));
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] &a" + event.getPlayer().getName()));
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c-&8] &c" + event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        LuckPerms api = LuckPermsProvider.get();
        CachedMetaData metaData = api.getPlayerAdapter(Player.class).getMetaData(event.getPlayer());
        String prefix = metaData.getPrefix();
        control.reconnectDB();
        ResultSet rs = control.selectRaw("Tags", "uuid", "\""+event.getPlayer().getUniqueId().toString()+"\"");
        if (prefix != null) {
            prefix = prefix.substring(0,2) + ChatColor.stripColor(prefix).charAt(2);
        }
        String message = (prefix == null ? "&fU" : prefix) + "&f " + event.getPlayer().getDisplayName() + "&f&7 » &f" + event.getMessage();
        try {
            if (rs.next()) {
                message = prefix + " &7[&f"+rs.getString("tag") +"&f&7] &f" + event.getPlayer().getDisplayName() + "&f&7 » &f" + event.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        event.setFormat("%2$s");
        event.setMessage(ChatColor.translateAlternateColorCodes('&',message));
    }
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        World fromWorld = e.getFrom().getWorld();
        if (e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (fromWorld.getEnvironment() == World.Environment.NETHER) {
                Location to = e.getTo();
                to.setWorld(Bukkit.getWorld("Survival"));
                e.setTo(to);
            }
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isGliding()) {
            if (player.getWorld().getName().equals("world")) {
                player.setGliding(false);
                event.setCancelled(true);
            }
        }
    }
}
