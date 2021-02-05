package me.ttno1.gui;

import java.util.List;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;
/**
 * The InvnetoryGUIArea functionality behaves as though the sub-InventoryGUIs <br>are arranged in a single vertical column starting at the top and going down.
 */
public class StdMultiInventoryGUI extends AbstractInventoryGUIArea implements MultiInventoryGUI {

	private List<InventoryGUI> guis;
	
	public StdMultiInventoryGUI(InventoryGUIArea superArea, List<InventoryGUI> guisList, StdInventoryGUISubAreaFactory subAreaFactory) {
		
		super(superArea, subAreaFactory);
		
		this.guis = guisList;
		
	}

	@Override
	public int getSize() {
		
		return 0;
		
	}

	@Override
	public int getWidth() {
		
		return 9;
		
	}

	@Override
	public int getHeight() {
		
		return 0;
		
	}

	@Override
	public ItemStack[] getContents() {
		return null;
	}

	@Override
	public ItemStack getItem(int slot) {
		return null;
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		return null;
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
	}
	
}
