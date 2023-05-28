package com.zip.core.commands;

import com.zip.core.Core;
import com.zip.core.utility.DBControl;
import com.zip.core.utility.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.zip.core.utility.text.color;
//tags? YES
public class deltag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Core.plugin.control.reconnectDB();
        Core.plugin.control.delete("Tags", "uuid", "\"" + player.getUniqueId() + "\"");
        MessageUtils.sendMessage(sender, MessageUtils.Type.INFO, "tag removed");
        return true;
    }
}