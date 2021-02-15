package me.ttno1.gui;

import java.util.function.Consumer;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

import me.ttno1.gui.events.InventoryGUIClickEvent;
import me.ttno1.gui.events.InventoryGUICloseEvent;
import me.ttno1.gui.events.InventoryGUIOpenEvent;

public interface InventoryGUI extends GUI, InventoryGUIArea, InventoryHolder{
	
	Consumer<InventoryGUIClickEvent> CANCEL = event -> event.setCancelled(true);
	
	String getTitle();
	
	void setTitle(String value);
	
	Consumer<InventoryGUIClickEvent> getDefaultOnClick();
	
	void setDefaultOnClick(Consumer<InventoryGUIClickEvent> onClick);
	
	Consumer<InventoryGUIOpenEvent> getOnOpen();
	
	void setOnOpen(Consumer<InventoryGUIOpenEvent> value);
	
	Consumer<InventoryGUICloseEvent> getOnClose();
	
	void setOnClose(Consumer<InventoryGUICloseEvent> value);
	
	Inventory getInventory();
	
	InventoryView open(HumanEntity player);
	
}
