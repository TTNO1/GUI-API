package me.ttno1.gui.events;

import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public interface InventoryGUICloseEventFactory {

	InventoryGUICloseEvent create(InventoryView view, InventoryGUI gui);
	
}
