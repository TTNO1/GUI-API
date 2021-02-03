package me.ttno1.gui.bukkitfactories;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface InventoryFactory {

	Inventory create(InventoryHolder owner, int size);
	
	Inventory create(InventoryHolder owner, int size, String title);
	
	Inventory create(InventoryHolder owner, InventoryType type);
	
	Inventory create(InventoryHolder owner, InventoryType type, String title);
	
}
