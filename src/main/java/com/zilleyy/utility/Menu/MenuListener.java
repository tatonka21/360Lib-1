package com.zilleyy.utility.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 11:32 am AEST
 */
public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu((Player) event.getWhoClicked());
        if(matchedMenu == null) return;

        matchedMenu.handleClick(event);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getPlayer());
        matchedMenu.handleOpenAction(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getPlayer());
        if (matchedMenu == null) return;

        matchedMenu.handleCloseAction(event);
        MenuManager.getInstance().unregisterMenu(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Menu matchedMenu = MenuManager.getInstance().matchMenu(event.getPlayer());
        if (matchedMenu == null) return;

        // TODO MAKE SURE THIS ACTUALLY FIRES THE CLOSE EVENT.
        event.getPlayer().closeInventory();
    }

}
