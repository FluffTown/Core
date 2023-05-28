package com.zip.core.commands;

import com.zip.core.utility.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.zip.core.utility.rpUtil.cooldown;
import static com.zip.core.utility.text.color;
//yuck.
public class sniff implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (cooldown(player)) {
            return true;
        }
        if (args.length == 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                MessageUtils.sendMessage(p, MessageUtils.Type.INFO, player.getDisplayName() + " sniffed!");
                player.playSound(player.getLocation(), Sound.ENTITY_FOX_SNIFF, 500.0f, 1.0f);
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    MessageUtils.sendMessage(p, MessageUtils.Type.INFO, player.getDisplayName() + " sniffed " + target.getDisplayName());
                    target.playSound(target.getLocation(), Sound.ENTITY_FOX_SNIFF, 500.0f, 1.0f);
                    player.playSound(player.getLocation(), Sound.ENTITY_FOX_SNIFF, 500.0f, 1.0f);
                }
            } else {
                MessageUtils.sendMessage(sender, MessageUtils.Type.ERROR, "player not found");
            }
        }
        return true;
    }
}