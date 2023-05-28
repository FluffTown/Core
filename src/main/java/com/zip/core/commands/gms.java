package com.zip.core.commands;

import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//obvious
public class gms implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            //no args
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            //player argument
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setGameMode(GameMode.SURVIVAL);
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "player not found");
            }
        }
        return true;
    }
}