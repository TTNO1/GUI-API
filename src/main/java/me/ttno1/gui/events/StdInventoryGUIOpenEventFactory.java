package me.ttno1.gui.events;

import javax.inject.Inject;

import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class StdInventoryGUIOpenEventFactory implements InventoryGUIOpenEventFactory {

	@Inject
	public StdInventoryGUIOpenEventFactory() {
		
	}
	
	@Override
	public InventoryGUIOpenEvent create(InventoryView view, InventoryGUI gui) {

		return new InventoryGUIOpenEvent(view, gui);
		
	}

}
