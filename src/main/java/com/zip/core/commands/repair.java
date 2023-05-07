package com.zip.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.zip.core.utility.text.color;

public class repair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            Inventory inv = player.getInventory();
            for (int i = 0 ; i < inv.getContents().length; i++) {
                ItemStack item = inv.getContents()[i];
                if (item != null) {
                    org.bukkit.inventory.meta.Damageable meta = (org.bukkit.inventory.meta.Damageable) item.getItemMeta();
                    meta.setDamage(0);
                    item.setItemMeta(meta);
                }
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                Inventory inv = target.getInventory();
                for (int i = 0 ; i < inv.getContents().length; i++) {
                    ItemStack item = inv.getContents()[i];
                    if (item != null) {
                        org.bukkit.inventory.meta.Damageable meta = (org.bukkit.inventory.meta.Damageable) item.getItemMeta();
                        if (meta != null) {
                            meta.setDamage(0);
                        }
                        item.setItemMeta(meta);
                    }
                }
            } else {
                player.sendMessage("[i] player not found");
            }
        }
        return true;
    }
}