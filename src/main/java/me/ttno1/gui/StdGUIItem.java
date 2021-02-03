package me.ttno1.gui;

import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class StdGUIItem implements GUIItem {

	private ItemStack item;
	
	private Consumer<InventoryGUIClickEvent> onClick;
	
	public StdGUIItem(ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		this.item = item;
		
		this.onClick = onClick;
		
	}
	
	@Override
	public ItemStack getGUIItem() {
		
		return item;
		
	}
	
	@Override
	public Consumer<InventoryGUIClickEvent> getOnGUIClick() {
		
		return onClick;
		
	}

}
