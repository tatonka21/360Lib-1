package com.zilleyy.utility.Menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 11:26 am AEST
 */
public class MenuManager {

    private static MenuManager instance;

    private final Map<UUID, Menu> menus;

    public MenuManager() {
        this.menus = new HashMap<>();
    }

    /**
     * @return The instance of the MenuManager class.
     */
    public static MenuManager getInstance() {
        if(instance == null) instance = new MenuManager();
        return instance;
    }

    /**
     * Register a menu to a user.
     * @param user The user.
     * @param menu The menu.
     */
    public void registerMenu(UUID user, Menu menu) {
        menus.put(user, menu);
    }

    /**
     * Register a menu to a user.
     * @param user The user.
     * @param menu The menu.
     */
    public void registerMenu(Player user, Menu menu) {
        menus.put(user.getUniqueId(), menu);
    }

    /**
     * Register a menu to a user.
     * @param user The user.
     * @param menu The menu.
     */
    public void registerMenu(HumanEntity user, Menu menu) {
        menus.put(user.getUniqueId(), menu);
    }

    /**
     * Unregister a menu.
     * @param user The user's menu to unregister.
     */
    public void unregisterMenu(UUID user) {
        menus.remove(user);
    }

    /**
     * Unregister a menu.
     * @param user The user's menu to unregister.
     */
    public void unregisterMenu(Player user) {
        menus.remove(user.getUniqueId());
    }

    /**
     * Unregister a menu.
     * @param user The user's menu to unregister.
     */
    public void unregisterMenu(HumanEntity user) {
        menus.remove(user.getUniqueId());
    }

    /**
     * Find a menu.
     * @param user The user to search for.
     * @return The Menu found, or null if it does not exist.
     */
    public Menu matchMenu(UUID user) { return menus.get(user); }

    /**
     * Find a menu.
     * @param user The user to search for.
     * @return The Menu found, or null if it does not exist.
     */
    public Menu matchMenu(Player user) { return menus.get(user.getUniqueId()); }

    /**
     * Find a menu.
     * @param user The user to search for.
     * @return The Menu found, or null if it does not exist.
     */
    public Menu matchMenu(HumanEntity user) { return menus.get(user.getUniqueId()); }

}
