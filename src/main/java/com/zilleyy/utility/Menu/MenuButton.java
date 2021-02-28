package com.zilleyy.utility.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * Author: Zilleyy
 * <br>
 * Date: 27/02/2021 @ 11:11 am AEST
 */
public class MenuButton {

    private ItemStack itemStack;

    private Consumer<InventoryClickEvent> onClick;

    /**
     * Class constructor
     * @param itemStack The ItemStack to use for this button.
     */
    public MenuButton(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Returns the ItemStack for this button.
     * @return The ItemStack supplied in the constructor.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Returns the value of the whenClicked consumer.
     * @return The whenClicked consumer.
     */
    public Consumer<InventoryClickEvent> getOnClick() {
        return onClick;
    }

    /**
     * Sets the value of the whenClicked consumer.
     * @param onClick The consumer to set.
     * @return Returns this object.
     */
    public MenuButton setOnClick(Consumer<InventoryClickEvent> onClick) {
        this.onClick = onClick;
        return this;
    }

}
