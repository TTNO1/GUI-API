package me.ttno1.gui.events;

import javax.inject.Inject;

import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class StdInventoryGUICloseEventFactory implements InventoryGUICloseEventFactory {
	
	@Inject
	public StdInventoryGUICloseEventFactory() {
		
	}
	
	@Override
	public InventoryGUICloseEvent create(InventoryView view, InventoryGUI gui) {

		return new InventoryGUICloseEvent(view, gui);
		
	}

}
