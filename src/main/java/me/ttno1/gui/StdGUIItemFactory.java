package me.ttno1.gui;

import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class StdGUIItemFactory implements GUIItemFactory {

	@Override
	public GUIItem create(ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		return new StdGUIItem(item, onClick);
		
	}
	
}
