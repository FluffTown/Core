package com.zip.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

import static com.zip.core.utility.text.color;

public class sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(color("&8[&c!&8]&7 Please specify a player"));
        } else {
            //player argument
            Player target = Bukkit.getPlayer(args[0]);
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i] + " ");
            }
            String message = sb.toString();
            if (target != null) {
                //&8[&2M&8]&7 &7[&rPlayer &7-> &rTarget&7] &7»&r Hello World
                target.performCommand(message);
            } else {
                player.sendMessage(color("&8[&c!&8]&7 Player not found"));
            }
        }
        return true;
    }
}