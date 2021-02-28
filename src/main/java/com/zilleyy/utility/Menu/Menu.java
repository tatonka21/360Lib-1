package com.zilleyy.utility.Menu;

import com.zilleyy.utility.Central;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.zilleyy.utility.Text.Messenger.color;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 11:10 am AEST
 */
public class Menu {

    private Inventory inventory;
    private Map<Integer, MenuButton> buttons;

    private Consumer<InventoryClickEvent> defaultClickAction;
    private Consumer<InventoryOpenEvent> onOpenAction;
    private Consumer<InventoryCloseEvent> onCloseAction;

    private BukkitRunnable menuUpdater;

    /**
     * Menu constructor.
     * @param title The title of the inventory, color codes are converted automatically.
     * @param rows The amount of rows in the inventory.
     */
    public Menu(String title, int rows) {
        if (rows > 6)
            throw new IllegalArgumentException("Menu cannot be larger than 6 rows in size.");
        else if(rows < 1)
            throw new IllegalArgumentException("Menu cannot be smaller than 1 row in size.");

        if(title.length() > 32)
            throw new IllegalArgumentException("Menu title cannot be longer than 32 characters.");

        this.inventory = Bukkit.createInventory(null, rows * 9, color(title));
        this.buttons = new HashMap<>();
    }

    public void createMenuUpdater() {
        menuUpdater = new BukkitRunnable() {
            @Override
            public void run() {
                onUpdate();
            }
        };
        menuUpdater.runTaskTimerAsynchronously(Central.getPlugin(), 0L, 0L);
    }

    /**
     * Method is called when the MenuUpdater runnable executes.
     */
    public void onUpdate() {}

    public Inventory getBukkitInventory() {
        return inventory;
    }

    public Consumer<InventoryClickEvent> getDefaultClickAction() {
        if(defaultClickAction == null) defaultClickAction = (clicked -> {});
        return defaultClickAction;
    }

    public void setDefaultClickAction(Consumer<InventoryClickEvent> defaultClickAction) {
        this.defaultClickAction = defaultClickAction;
    }

    /**
     * Sets the value of the onOpenAction consumer.
     * @param onOpenAction The consumer to use.
     */
    public void setOnOpenAction(Consumer<InventoryOpenEvent> onOpenAction) {
        this.onOpenAction = onOpenAction;
    }

    /**
     * Sets the value of the onCloseAction consumer.
     * @param onCloseAction The consumer to use.
     */
    public void setOnCloseAction(Consumer<InventoryCloseEvent> onCloseAction) {
        this.onCloseAction = onCloseAction;
    }

    /**
     * Handles a Player opening the Inventory. <br>
     * Executes the onOpenAction consumer if it is not null.
     * @param event The Event who fired when the inventory is opened.
     */
    public void handleOpenAction(InventoryOpenEvent event) {
        if (onOpenAction != null) onOpenAction.accept(event);
    }

    /**
     * Handles a Player closing the inventory. <br>
     * Executes the onCloseAction consumer if it is not null.
     * @param event The Event who fired when the inventory is closed.
     */
    public void handleCloseAction(InventoryCloseEvent event) {
        if (onCloseAction != null) onCloseAction.accept(event);
    }

    /**
     * Registers a button in a specified slot.
     * @param button The button object to register.
     * @param slot The slot to associate it with.
     */
    public void registerButton(int slot, MenuButton button) {
        buttons.put(slot, button);
    }

    /**
     * Handles an InventoryClickEvent inside this menu.
     * @param event The InventoryClickEvent.
     */
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (!buttons.containsKey(event.getRawSlot())) return;

        Consumer<InventoryClickEvent> consumer = buttons.get(event.getRawSlot()).getOnClick();
        if (consumer != null) consumer.accept(event);
        else getDefaultClickAction().accept(event);
    }

    /**
     * Opens the inventory to a specified player.
     * @param player The player to open the inventory to.
     */
    public void open(Player player) {
        MenuManager manager = MenuManager.getInstance();

        buttons.forEach((slot, button) -> {
            inventory.setItem(slot, button.getItemStack());
        });

        player.openInventory(inventory);
        manager.registerMenu(player.getUniqueId(), this);
    }

}
