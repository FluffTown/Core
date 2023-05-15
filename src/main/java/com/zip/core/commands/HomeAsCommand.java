package com.zip.core.commands;

import com.zip.core.Core;
import com.zip.core.utility.DBControl;
import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.sql.ResultSet;
import java.util.*;

public class HomeAsCommand implements CommandExecutor, TabCompleter {

    private final FileConfiguration config;
    Core core;
    DBControl control;

    public HomeAsCommand(Core core) {
        this.core = core;
        this.config = core.getConfig();
        this.control = core.control;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "This command can only be used by a player.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0 || args[0].equalsIgnoreCase(player.getName())) {
            MessageUtils.sendMessage(player, MessageUtils.Type.INFO, "Home aliasing removed.");
            config.set(player.getUniqueId().toString(), null);
        } else {
            OfflinePlayer[] seen_players = Bukkit.getOfflinePlayers();
            String alias_uuid = null;
            for (int i = 0; i < seen_players.length; i++) {
                if (seen_players[i].getName().equalsIgnoreCase(args[0]))
                    alias_uuid = seen_players[i].getUniqueId().toString();
            }
            if (alias_uuid == null)
                MessageUtils.sendMessage(player, MessageUtils.Type.ERROR, "Player " + args[0] + " has never joined the server.");
            else {
                MessageUtils.sendMessage(player, MessageUtils.Type.INFO, "Home aliasing set to player " + args[0] + ".");
                config.set(player.getUniqueId().toString(), alias_uuid);
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return null;
        if (args.length == 1) {
            ResultSet set = control.selectRaw("Homes");
            List<String> options = new ArrayList<>();
            try {
                while (set.next()) {
                    String name = set.getString("name");
                    if (name.toLowerCase().contains(args[0].toLowerCase())) {
                        options.add(name);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                options.add("[Database Error]");
            }
            return options;
        }
        return null;
    }
}