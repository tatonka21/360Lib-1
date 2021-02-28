package com.zilleyy.utility.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 2:04 pm AEST
 */
public class PlayerScoreboardManager {

    private static List<PlayerScoreboard> scoreboards = new ArrayList<>();

    public static List<PlayerScoreboard> getScoreboards() {
        return scoreboards;
    }

    public static PlayerScoreboard getScoreboard(Player player) {
        for(PlayerScoreboard scoreboard : getScoreboards()) {
            if(scoreboard.getPlayer().equals(player)) return scoreboard;
        }
        return null;
    }

    public static boolean hasScoreboard(Player player) {
        return (getScoreboard(player) != null);
    }

    public static void addScoreboard(PlayerScoreboard scoreboard) {
        scoreboards.add(scoreboard);
    }

    public static void removeScoreboard(PlayerScoreboard scoreboard) {
        scoreboards.remove(scoreboard);
    }

    public static void updateScoreboard(PlayerScoreboard scoreboard) {
        for(int i = 0; i < scoreboard.getLines().length; i++) {
            scoreboard.setLine(i, scoreboard.getLines()[i]);
        }
    }

}
