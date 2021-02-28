package com.zilleyy.utility.Text;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zilleyy
 * On 24/02/2021 @ 5:00 pm AEST
 */
public class Messenger {

    /**
     * @param sender The CommandSender to send the message to.
     * @param message The message to send to the CommandSender.
     */
    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    /**
     * @param sender The CommandSender to send the message to.
     * @param prefix The prefix to put before the message, will put a "»" in between the prefix and the message.
     * @param message The message to send to the CommandSender.
     */
    public static void sendMessage(CommandSender sender, String prefix, String message) {
        sender.sendMessage(color(prefix + "&8» &7" + message));
    }

    /**
     * Sends an action bar message to the specified player.
     * @param player The Player to send the alert to.
     * @param title The title to send to the Player.
     */
    public static void sendActionBar(Player player, String title) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(title));
    }

    /**
     * Sends the Player a BossBar, currently does not have animation compatibility.
     * @param player The Player to send the boss bar to.
     * @param title The title of the boss bar.
     */
    public static void sendBossBar(Player player, String title) {
        BossBar bossBar = Bukkit.createBossBar(title, BarColor.PINK, BarStyle.SOLID);
        bossBar.setVisible(true);
        bossBar.addPlayer(player);
    }

    /**
     * Sends the Player a BossBar, currently does not have animation compatibility.
     * @param player The Player to send the boss bar to.
     * @param title The title of the boss bar.
     * @param color The color of the boss bar.
     * @param style The style of the boss bar (how many segments it has).
     */
    public static void sendBossBar(Player player, String title, BarColor color, BarStyle style) {
        BossBar bossBar = Bukkit.createBossBar(title, color, style);
        bossBar.setVisible(true);
        bossBar.addPlayer(player);
    }

    /**
     * Sends the Player a BossBar, currently does not have animation compatibility.
     * @param player The Player to send the boss bar to.
     * @param title The title of the boss bar.
     * @param color The color of the boss bar.
     * @param style The style of the boss bar (how many segments it has).
     * @param progress The percentage progress of the boss bar (how much health it has).
     */
    public static void sendBossBar(Player player, String title, BarColor color, BarStyle style, double progress) {
        BossBar bossBar = Bukkit.createBossBar(title, color, style);
        bossBar.setProgress(progress);
        bossBar.setVisible(true);
        bossBar.addPlayer(player);
    }

    /**
     * Unsends all the boss bars that a Player can currently see.
     * @param player The Player who's boss bars you would like to make invisible.
     */
    public static void unsendBossBars(Player player) {
        Bukkit.getBossBars().forEachRemaining(bossBar -> {
            if(bossBar.getPlayers().contains(player)) bossBar.removePlayer(player);
        });
    }

    /**
     * @param string The text to apply color to.
     * @return The inputted string with its color codes translated.
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * @param string The text to apply color to.
     * @param altColorChar The color character to use.
     * @see Messenger#color(String) for default color character '&'.
     *
     * @return The inputted string with its color codes translated from specified color character.
     */
    public static String color(char altColorChar, String string) {
        return ChatColor.translateAlternateColorCodes(altColorChar, string);
    }

}
