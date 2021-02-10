package me.ttno1.gui;

import org.bukkit.entity.HumanEntity;

public interface MultiInventoryGUI extends InventoryGUIArea {

	int getLength();
	
	InventoryGUI getDefaultInventoryGUI();
	
	void setDefaultInventoryGUI(InventoryGUI value);
	
	
	
	InventoryGUI getInventoryGUI(int index);
	
	InventoryGUI openInventoryGUI(int index, HumanEntity player);
	
}
