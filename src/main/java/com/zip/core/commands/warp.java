package com.zip.core.commands;
import com.zip.core.Core;
import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class warp implements CommandExecutor, TabCompleter {
    Core core;
    public warp(Core c) {
        this.core = c;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            ConfigurationSection warp = core.getConfig().getConfigurationSection("warps").getConfigurationSection(args[0]);
            Location loc = new Location(
                    Bukkit.getWorld(warp.getString("world")),
                    warp.getDouble("x"),
                    warp.getDouble("y"),
                    warp.getDouble("z")
            );
            ((Player) sender).teleport(loc);
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "warp is broken or does not exist");
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ConfigurationSection warps = core.getConfig().getConfigurationSection("warps");
        List<String> out = new ArrayList<>();
        out.addAll(warps.getKeys(false));
        return out;
    }
}