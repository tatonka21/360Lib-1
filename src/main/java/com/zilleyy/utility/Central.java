package com.zilleyy.utility;

import net.minecraft.server.v1_16_R3.Chunk;
import org.bukkit.Bukkit;
import org.bukkit.ChunkSnapshot;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Author: Zilleyy
 * <br>
 * Date: 24/02/2021 @ 4:46 pm AEST
 */
public final class Central {

    private static JavaPlugin plugin;
    private static boolean isLoaded = false;

    public static void onLoad() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        if(isLoaded) {
            sender.sendMessage("360Lib is already loaded!");
        } else {
            sender.sendMessage("Successfully loaded 360Lib.");
        }
        Chunk chunk;
        chunk.get
    }

    /**
     * @return The plugin registered with this library.
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * You must register your plugin using this or the library will not work.
     * @param plugin Your plugin to register to this util.
     */
    public static void registerPlugin(JavaPlugin plugin) {
        Central.plugin = plugin;
        onLoad();
    }

}
