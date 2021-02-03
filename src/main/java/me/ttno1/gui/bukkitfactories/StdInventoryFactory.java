package me.ttno1.gui.bukkitfactories;

import javax.inject.Inject;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class StdInventoryFactory implements InventoryFactory{

	@Inject
	public StdInventoryFactory() {
		
	}
	
	@Override
	public Inventory create(InventoryHolder owner, int size) {
		
		return Bukkit.createInventory(owner, size);
		
	}

	@Override
	public Inventory create(InventoryHolder owner, int size, String title) {
		
		return Bukkit.createInventory(owner, size, title);
		
	}

	@Override
	public Inventory create(InventoryHolder owner, InventoryType type) {
		
		return Bukkit.createInventory(owner, type);
		
	}

	@Override
	public Inventory create(InventoryHolder owner, InventoryType type, String title) {
		
		return Bukkit.createInventory(owner, type, title);
		
	}
	
}
