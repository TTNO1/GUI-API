package me.ttno1.gui.events;

import javax.inject.Inject;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.InventoryGUI;

public class StdInventoryGUIClickEventFactory implements InventoryGUIClickEventFactory {

	@Inject
	public StdInventoryGUIClickEventFactory() {
		
	}
	
	@Override
	public InventoryGUIClickEvent create(InventoryView view, SlotType type, int slot, ClickType click, InventoryAction action, int key, InventoryGUI gui) {

		return new InventoryGUIClickEvent(view, type, slot, click, action, key, gui);
		
	}

}
