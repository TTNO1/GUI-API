package me.ttno1.gui.events;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public interface InventoryGUIClickEventFactory {

	InventoryGUIClickEvent create(InventoryView view, SlotType type, int slot, ClickType click, InventoryAction action, int key, InventoryGUI gui);
	
}
