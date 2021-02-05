package me.ttno1.gui;

public interface InventoryGUISubAreaFactory {
	
	InventoryGUIArea create(InventoryGUIArea superArea, int startSlot, int endSlot);
	
}
