package com.zip.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 1, 0.5));
        return true;
    }
}