package com.zip.core.commands;

import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

import static com.zip.core.utility.text.color;
//Super user do?
public class sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "Please specify a player");
        } else {
            //player argument
            Player target = Bukkit.getPlayer(args[0]);
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i] + " ");
            }
            String message = sb.toString();
            if (target != null) {
                target.performCommand(message);
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "Player not found");
            }
        }
        return true;
    }
}