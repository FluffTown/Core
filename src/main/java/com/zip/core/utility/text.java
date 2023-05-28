package com.zip.core.utility;
import org.bukkit.ChatColor;
//TEXT = YUMMY
public class text {
    public static String color(String str) {
        str = str.replaceAll("&#([0-9A-Fa-f])([0-9A-Fa-f])([0-9A-Fa-f])([0-9A-Fa-f])([0-9A-Fa-f])([0-9A-Fa-f])","&x&$1&$2&$3&$4&$5&$6");
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
