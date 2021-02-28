package com.zilleyy.utility.Scoreboard;

import com.zilleyy.utility.Central;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import static com.zilleyy.utility.Text.Messenger.color;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 1:45 pm AEST
 */
public class PlayerScoreboard {

    private PlayerScoreboard instance;

    private Scoreboard scoreboard;
    private Objective objective;
    private Team[] teams;

    private Player player;

    private String title;
    private String[] lines;

    private BukkitRunnable updater;

    public PlayerScoreboard(Player player, String title, String[] lines) {
        instance = this;

        this.player = player;
        this.title = title.length() > 32 ? title.substring(0, 32) : title;
        this.lines = lines;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(title, "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(int i = 0; i < lines.length; i++) {
            Team team = scoreboard.registerNewTeam("SLOT_" + i);
            team.addEntry(lines[i]);
        }

        player.setScoreboard(scoreboard);
        PlayerScoreboardManager.getScoreboards().add(this);

        updater = new BukkitRunnable() {
            @Override
            public void run() {
                PlayerScoreboardManager.updateScoreboard(instance);
            }
        };
        updater.runTaskTimerAsynchronously(Central.getPlugin(), 20L, 20L);
    }

    public BukkitRunnable getUpdater() {
        return updater;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = color(title);
        objective.setDisplayName(title.length() > 32 ? title.substring(0, 32) : title);
    }

    public String[] getLines() {
        return lines;
    }

    public void setLine(int index, String text) {
        Team team = scoreboard.getTeam("SLOT_" + index);

        String entry = generateEntry(index);
        if(!scoreboard.getEntries().contains(entry)) objective.getScore(entry);

        text = color(text);
        String prefix = getFirstSplit(text);
        String suffix = getFirstSplit(ChatColor.getLastColors(prefix) + getSecondSplit(text));

        team.setPrefix(prefix);
        team.setSuffix(suffix);
    }

    public void removeLine(int index) {
        String entry = generateEntry(index);
        if(scoreboard.getEntries().contains(entry)) {
            scoreboard.resetScores(entry);
        }
    }

    private static String generateEntry(int index) {
        return ChatColor.values()[index].toString();
    }

    private String getFirstSplit(String s) {
        return s.length() > 16 ? s.substring(0, 16) : s;
    }

    private String getSecondSplit(String s) {
        if(s.length() > 32) {
            s = s.substring(0, 32);
        }
        return s.length() > 16 ? s.substring(16) : "";
    }

}
