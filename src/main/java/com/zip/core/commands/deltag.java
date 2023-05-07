package com.zip.core.commands;

import com.zip.core.utility.DBControl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.zip.core.utility.text.color;

public class deltag implements CommandExecutor {
    DBControl control;
    public deltag(DBControl con) {
        control = con;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        control.reconnectDB();
        control.delete("Tags", "uuid", "\"" + player.getUniqueId() + "\"");
        player.sendMessage("[i] tag removed");
        return true;
    }
}