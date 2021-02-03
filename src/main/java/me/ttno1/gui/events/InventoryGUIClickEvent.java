package me.ttno1.gui.events;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class InventoryGUIClickEvent extends InventoryClickEvent{

	private InventoryGUI gui;
	
	public InventoryGUIClickEvent(InventoryView view, SlotType type, int slot, ClickType click, InventoryAction action, int key, InventoryGUI gui) {
		
		super(view, type, slot, click, action, key);
		
		this.gui = gui;
		
	}

	public InventoryGUI getInventoryGUI() {
		
		return gui;
		
	}
	
	@Override
	public int getSlot() {
		
		return getRawSlot();
		
	}
	
}
