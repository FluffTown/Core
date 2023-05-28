package com.zip.core.utility;

import com.zip.core.Core;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.time.Instant;
//ROLE PLAY THINGY
public class rpUtil {
    public static boolean cooldown(Player player) {
        if (Core.plugin.rpCooldown.containsKey(player.getUniqueId().toString())) {
            if (!(Timestamp.from(Instant.now()).getTime() > Core.plugin.rpCooldown.get(player.getUniqueId().toString())+10000)) {
                long timeRemaining = Core.plugin.rpCooldown.get(player.getUniqueId().toString())+10000 - Timestamp.from(Instant.now()).getTime();
                MessageUtils.sendMessage(player, MessageUtils.Type.WARN,"Please try again in: "+timeRemaining/1000+"s");
                return true;
            }
        }
        Core.plugin.rpCooldown.put(player.getUniqueId().toString(),Timestamp.from(Instant.now()).getTime());
        return false;
    }
}
