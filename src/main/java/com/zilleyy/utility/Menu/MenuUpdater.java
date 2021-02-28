package com.zilleyy.utility.Menu;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 12:00 pm AEST
 */
public class MenuUpdater extends BukkitRunnable {

    private Menu menu;

    public MenuUpdater(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        menu.onUpdate();
    }

}
