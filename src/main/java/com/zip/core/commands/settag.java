package com.zip.core.commands;

import com.zip.core.utility.DBControl;
import com.zip.core.utility.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class settag implements CommandExecutor {
    DBControl control;
    public settag(DBControl con) {
        control = con;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        control.reconnectDB();
        if (args.length > 0) {
            if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',args[0])).length() < 10) {
                control.delete("Tags", "uuid", "\"" + player.getUniqueId() + "\"");
                control.insert("Tags", new String[]{player.getUniqueId().toString(), args[0]});
                MessageUtils.sendMessage(sender, MessageUtils.Type.INFO, "tag set to: " + args[0]);
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.WARN, "please keep tag under 10 characters");
            }
        } else {
            control.delete("Tags", "uuid", "\"" + player.getUniqueId() + "\"");
            MessageUtils.sendMessage(sender, MessageUtils.Type.INFO, "tag removed");
        }
        return true;
    }
}