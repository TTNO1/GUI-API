package me.ttno1.gui;

public interface MultiInventoryGUIArea extends InventoryGUIArea {
	
	int getLength();
	
	InventoryGUI getDefaultInventoryGUI();
	
	void setDefaultInventoryGUI(InventoryGUI value);
	
	int getIndexOf(InventoryGUIArea area);
	
	InventoryGUIArea getNext(InventoryGUIArea current);
	
	InventoryGUIArea getPrevious(InventoryGUIArea current);
	
}
