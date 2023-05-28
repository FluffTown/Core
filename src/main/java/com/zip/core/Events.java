package com.zip.core;

import com.zip.core.utility.DBControl;
import com.zip.core.utility.text;
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

import static com.zip.core.utility.text.color;
//EVENT HANDLER
public class Events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //new players are teleported to spawn
        if (!player.hasPlayedBefore()) {
            player.teleport(new Location(Bukkit.getWorld("world"), 0.5,1,0.5));
        }
        CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(event.getPlayer());
        event.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&7[&f" + metaData.getPrefix() + "&f&7] &f" + event.getPlayer().getDisplayName()));
        String out = ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] &a" + event.getPlayer().getName());
        event.setJoinMessage(out);
        Core.plugin.jda.getTextChannelById(Core.plugin.chatChannel).sendMessage(ChatColor.stripColor(out)).queue(); //*Blep*
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String out = ChatColor.translateAlternateColorCodes('&', "&8[&c-&8] &c" + event.getPlayer().getName());
        event.setQuitMessage(out);
        Core.plugin.jda.getTextChannelById(Core.plugin.chatChannel).sendMessage(ChatColor.stripColor(out)).queue();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        LuckPerms api = LuckPermsProvider.get();
        CachedMetaData metaData = api.getPlayerAdapter(Player.class).getMetaData(event.getPlayer());
        String prefix = metaData.getPrefix();
        Core.plugin.control.reconnectDB();
        ResultSet rs = Core.plugin.control.selectRaw("Tags", "uuid", "\""+event.getPlayer().getUniqueId().toString()+"\"");
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
        String out = message; //ChatColor.translateAlternateColorCodes('&',message);
        event.setMessage(color(message));


        out = out.replaceAll("@","@ ");


        //out = out.replaceAll("&3", "\033[0;36m");
        out = "```ansi\n" + out +"\n```";

        Core.plugin.jda.getTextChannelById(Core.plugin.chatChannel).sendMessage(ChatColor.stripColor(color(message))).queue();
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
