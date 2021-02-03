package me.ttno1.gui;

import java.util.Map;

import javax.inject.Inject;

import org.bukkit.inventory.Inventory;

@Deprecated
public class StdInventoryGUIRegistry implements InventoryGUIRegistry {

	private Map<Inventory, InventoryGUI> registryMap;
	
	@Inject
	public StdInventoryGUIRegistry(Map<Inventory, InventoryGUI> registryMap) {
		
		this.registryMap = registryMap;
		
	}
	
	@Override
	public void registerInventory(Inventory inventory, InventoryGUI gui) {
		
		registryMap.put(inventory, gui);
		
	}

	@Override
	public void unregisterInventory(Inventory inventory) {
		
		registryMap.remove(inventory);
		
	}

	@Override
	public InventoryGUI getGUI(Inventory inventory) {
		
		return registryMap.get(inventory);
		
	}

	@Override
	public boolean isGUI(Inventory inventory) {
		
		return registryMap.containsKey(inventory);
		
	}

}
