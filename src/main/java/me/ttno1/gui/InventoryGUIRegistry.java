package me.ttno1.gui;

import org.bukkit.inventory.Inventory;

@Deprecated
public interface InventoryGUIRegistry {

	void registerInventory(Inventory inventory, InventoryGUI gui);
	
	void unregisterInventory(Inventory inventory);
	
	InventoryGUI getGUI(Inventory inventory);
	
	boolean isGUI(Inventory inventory);
	
}
