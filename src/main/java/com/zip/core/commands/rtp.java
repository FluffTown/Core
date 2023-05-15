package com.zip.core.commands;

import com.zip.core.Core;
import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;
public class rtp implements CommandExecutor {
    Core core;
    FileConfiguration config;
    public rtp(Core core) {
        this.core = core;
        this.config = core.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int tries = 0;
        int range = config.getConfigurationSection("options").getInt("rtp_range");
        Location loc;
        Player player = (Player) sender;
        Random random = new Random();
        try {
            Block block = Objects.requireNonNull(Bukkit.getWorld("Survival")).getHighestBlockAt(
                    random.nextInt(-range, range),
                    random.nextInt(-range, range)
            );
            loc = block.getLocation();
            while ((block.isLiquid() || (block.getType() == Material.ICE)) && tries<8) {
                tries++;
                block = Objects.requireNonNull(Bukkit.getWorld("Survival")).getHighestBlockAt(
                        random.nextInt(-range, range),
                        random.nextInt(-range, range)
                );
                loc = block.getLocation();
            }
            if (tries>8 || block.isLiquid()) {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "Sorry, but I can't find a safe place. Please try again!");
                return true;
            }
            player.teleport(loc.add(0,1,0));
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "World Does Not Exist");
            return true;
        }
        return true;
    }
}