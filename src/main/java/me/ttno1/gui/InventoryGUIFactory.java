package me.ttno1.gui;

public interface InventoryGUIFactory {
	
	InventoryGUI create(String title, int size);
	
	InventoryGUI create(String title, int size, InventoryGUIArea superArea);
	
}
