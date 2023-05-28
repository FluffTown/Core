package com.zip.core;

import com.zip.core.utility.DBControl;
import com.zip.core.utility.botShit;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


import java.util.HashMap;
import java.util.Objects;
//MAIN DRIVER
public final class Core extends JavaPlugin {
    public DBControl control;
    public JDA jda;
    public void broadcast(String msg) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',msg));
    }
    public String chatChannel = "1111337079608201326";
    public String uptimeChannel = "1112099599776219147";
    public HashMap<String, Long> rpCooldown = new HashMap<>();
    public HashMap<String, Long> rtpCooldown = new HashMap<>();
    public static Core plugin;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().createWorld(new WorldCreator("Survival"));
        this.control = new DBControl();
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("gma")).setExecutor(new com.zip.core.commands.gma());
        Objects.requireNonNull(this.getCommand("gmc")).setExecutor(new com.zip.core.commands.gmc());
        Objects.requireNonNull(this.getCommand("gms")).setExecutor(new com.zip.core.commands.gms());
        Objects.requireNonNull(this.getCommand("gmsp")).setExecutor(new com.zip.core.commands.gmsp());

        Objects.requireNonNull(this.getCommand("heal")).setExecutor(new com.zip.core.commands.heal());
        Objects.requireNonNull(this.getCommand("feed")).setExecutor(new com.zip.core.commands.feed());

        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new com.zip.core.commands.fly());
        Objects.requireNonNull(this.getCommand("repair")).setExecutor(new com.zip.core.commands.repair());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new com.zip.core.commands.spawn());
        Objects.requireNonNull(this.getCommand("rtp")).setExecutor(new com.zip.core.commands.rtp());
        Objects.requireNonNull(this.getCommand("msg")).setExecutor(new com.zip.core.commands.msg());
        Objects.requireNonNull(this.getCommand("sudo")).setExecutor(new com.zip.core.commands.sudo());

        Objects.requireNonNull(this.getCommand("openinv")).setExecutor(new com.zip.core.commands.openinv());
        Objects.requireNonNull(this.getCommand("openender")).setExecutor(new com.zip.core.commands.openender());

        Objects.requireNonNull(this.getCommand("settag")).setExecutor(new com.zip.core.commands.settag());
        Objects.requireNonNull(this.getCommand("deltag")).setExecutor(new com.zip.core.commands.deltag());

        Objects.requireNonNull(this.getCommand("warp")).setExecutor(new com.zip.core.commands.warp());
        Objects.requireNonNull(this.getCommand("warp")).setTabCompleter(new com.zip.core.commands.warp());

        Objects.requireNonNull(this.getCommand("home")).setExecutor(new com.zip.core.commands.HomeCommands());
        Objects.requireNonNull(this.getCommand("home")).setTabCompleter(new com.zip.core.commands.HomeCommands());

        Objects.requireNonNull(this.getCommand("delhome")).setExecutor(new com.zip.core.commands.HomeCommands());
        Objects.requireNonNull(this.getCommand("delhome")).setTabCompleter(new com.zip.core.commands.HomeCommands());

        Objects.requireNonNull(this.getCommand("sethome")).setExecutor(new com.zip.core.commands.HomeCommands());

        Objects.requireNonNull(this.getCommand("homeas")).setExecutor(new com.zip.core.commands.HomeAsCommand());
        Objects.requireNonNull(this.getCommand("homeas")).setTabCompleter(new com.zip.core.commands.HomeAsCommand());

        Objects.requireNonNull(this.getCommand("boop")).setExecutor(new com.zip.core.commands.boop());
        Objects.requireNonNull(this.getCommand("sniff")).setExecutor(new com.zip.core.commands.sniff());
        Objects.requireNonNull(this.getCommand("beep")).setExecutor(new com.zip.core.commands.beep());
        Objects.requireNonNull(this.getCommand("scream")).setExecutor(new com.zip.core.commands.scream());
        Objects.requireNonNull(this.getCommand("awoo")).setExecutor(new com.zip.core.commands.awoo());

        Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        getServer().getPluginManager().registerEvents(new Events(), this);

        String token = getConfig().getString("token");
        System.out.println("[Bot] token is: "+token);
        jda = JDABuilder.createDefault(token)
                .addEventListeners(new botShit())
                .setActivity(Activity.playing("Minecraft"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        
        System.out.println("[bot] waiting");
        try {
            jda.awaitReady();
            System.out.println("[bot] done waiting");
            jda.getTextChannelById(uptimeChannel).sendMessage(":white_check_mark: Server Started").queue();
        } catch (InterruptedException e) {
            System.out.println("[bot] error");
        }
        plugin = this;
    }
    //discord bot shit

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            control.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jda.getTextChannelById(uptimeChannel).sendMessage(":octagonal_sign: Server Stopped").queue();
        jda.shutdown();
    }
}
