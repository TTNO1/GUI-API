package me.ttno1.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;

public interface MultiInventoryGUI extends MultiInventoryGUIArea {
	
	InventoryView open(HumanEntity player, int index);
	
	InventoryView open(HumanEntity player);
	
}
