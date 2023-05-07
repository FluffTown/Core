package com.zip.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;
import static com.zip.core.utility.text.color;
public class rtp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Random random = new Random();
        try {
            Block block = Objects.requireNonNull(Bukkit.getWorld("Survival")).getHighestBlockAt(
                    random.nextInt(-(16*32), (16*32)),
                    random.nextInt(-(16*32), (16*32))
            );
            player.teleport(block.getLocation());
        } catch (Exception e) {
            player.sendMessage(color("&4[Error]&f World Does Not Exist"));
            return true;
        }
        return true;
    }
}