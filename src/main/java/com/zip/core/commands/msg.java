package com.zip.core.commands;

import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.zip.core.utility.text.color;

public class msg implements CommandExecutor {
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
                player.sendMessage(color("&8[&2M&8]&7 &7[&rYou &7→&7 &r" + target.getName() + "&7] &7»&r " + message));
                target.sendMessage(color("&8[&2M&8]&7 &7[&r" + player.getName() + " &7→ &rYou&7] &7»&r " + message));
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "player not found");
            }
        }
        return true;
    }
}