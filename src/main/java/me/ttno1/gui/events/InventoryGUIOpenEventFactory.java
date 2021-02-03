package me.ttno1.gui.events;

import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public interface InventoryGUIOpenEventFactory {

	InventoryGUIOpenEvent create(InventoryView view, InventoryGUI gui);
	
}
