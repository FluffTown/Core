package com.zip.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.zip.core.utility.text.color;

public class gmc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            //no args
            player.setGameMode(GameMode.CREATIVE);
        } else {
            //player argument
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setGameMode(GameMode.CREATIVE);
            } else {
                player.sendMessage(color("&8[&c!&8]&7 Player not found"));
            }
        }
        return true;
    }
}