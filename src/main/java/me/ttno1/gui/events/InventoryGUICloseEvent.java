package me.ttno1.gui.events;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class InventoryGUICloseEvent extends InventoryCloseEvent {

	private InventoryGUI gui;
	
	public InventoryGUICloseEvent(InventoryView view, InventoryGUI gui) {
		
		super(view);
		
		this.gui = gui;
		
	}
	
	public InventoryGUI getInventoryGUI() {
		
		return gui;
		
	}

}
