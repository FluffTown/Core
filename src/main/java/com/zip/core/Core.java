package com.zip.core;

import com.zip.core.utility.DBControl;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
public final class Core extends JavaPlugin {
    DBControl control;
    @Override
    public void onEnable() {
        getServer().createWorld(new WorldCreator("Survival"));
        control = new DBControl();
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

        Objects.requireNonNull(this.getCommand("settag")).setExecutor(new com.zip.core.commands.settag(control));
        Objects.requireNonNull(this.getCommand("deltag")).setExecutor(new com.zip.core.commands.deltag(control));

        Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        getServer().getPluginManager().registerEvents(new Events(control), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
