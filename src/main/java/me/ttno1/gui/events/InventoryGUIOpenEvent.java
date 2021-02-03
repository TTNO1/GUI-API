package me.ttno1.gui.events;

import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class InventoryGUIOpenEvent extends InventoryOpenEvent {

	private InventoryGUI gui;
	
	public InventoryGUIOpenEvent(InventoryView view, InventoryGUI gui) {
		
		super(view);
		
		this.gui = gui;
		
	}
	
	public InventoryGUI getInventoryGUI() {
		
		return gui;
		
	}

}
