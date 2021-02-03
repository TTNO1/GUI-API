package me.ttno1.gui;

import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public interface GUIItem {
	
	ItemStack getGUIItem();
	
	default Consumer<InventoryGUIClickEvent> getOnGUIClick() {
		
		return null;
		
	}
	
}
