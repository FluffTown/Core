package com.zip.core.commands;

import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//MEDIC!!!
public class heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setSaturation(20);
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setHealth(20);
                target.setFoodLevel(20);
                player.setSaturation(20);
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "player not found");
            }
        }
        return true;
    }
}