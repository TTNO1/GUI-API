package me.ttno1.gui;

import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class InventoryGUISubArea extends AbstractInventoryGUIArea {
	
	private Map<Integer, Integer> slotMap;
	
	private int startSlot;
	
	private int endSlot;
	
	private int width;
	
	private int height;
	
	public InventoryGUISubArea(InventoryGUIArea superArea, int startSlot, int endSlot, Map<Integer, Integer> slotMap, StdInventoryGUISubAreaFactory subAreaFactory) {
		
		super(subAreaFactory);
		//make sure endSlot > startSlot to make things easier later on
		if (startSlot > endSlot) {
			
			int tempStartSlot = startSlot;
			
			startSlot = endSlot;
			
			endSlot = tempStartSlot;
			
		}
		
		if(startSlot < 0) {
			
			throw new IllegalArgumentException("InventoryGUISubArea out of bounds of super-area");
			
		}
		
		this.startSlot = startSlot;
		
		this.endSlot = endSlot;
		
		this.slotMap = slotMap;
		
		setSuperArea(superArea);
		
	}
	
	//Not very practical, might move setSuperArea() to InventoryGUI
	@Override
	public void setSuperArea(InventoryGUIArea value) {
		
		if(endSlot >= value.getSize()) {
			
			throw new IllegalArgumentException("InventoryGUISubArea out of bounds of super-area");
			
		}
		
		superArea = value;
		
		width = (value.getColumnOf(endSlot) - value.getColumnOf(startSlot)) + 1;
		
		height = (value.getRowOf(endSlot) - value.getRowOf(startSlot)) + 1;
		
		for(int subSlot = 0, superSlot = startSlot; subSlot < getSize(); subSlot++, superSlot++) {
			//Check if we've gone out of bounds of the area
			if(superArea.getColumnOf(superSlot) > superArea.getColumnOf(endSlot)) {
				
				superSlot = superArea.getSlotAt(superArea.getColumnOf(startSlot), superArea.getRowOf(superSlot) + 1);
				
			} else if(superArea.getColumnOf(superSlot) < superArea.getColumnOf(startSlot)) {
				
				superSlot = superArea.getSlotAt(superArea.getColumnOf(startSlot), superArea.getRowOf(superSlot));
				
			}
			
			slotMap.put(subSlot, superSlot);
			
		}
		
	}
	
	@Override
	public int getSize() {
		
		return width * height;
		
	}

	@Override
	public int getWidth() {
		
		return width;
		
	}

	@Override
	public int getHeight() {
		
		return height;
		
	}

	@Override
	public ItemStack[] getContents() {
		
		ItemStack[] items = new ItemStack[getSize()];
		
		for(int i = 0; i < getSize(); i++) {
			
			items[i] = getItem(i);
			
		}
		
		return items;
		
	}
	
	@Override
	public ItemStack getItem(int slot) {

		checkSlot(slot);
		
		return superArea.getItem(slotMap.get(slot));
		
	}
	
	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {

		checkSlot(slot);
		
		return superArea.getOnClick(slotMap.get(slot));
		
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		superArea.setItem(slotMap.get(slot), item, onClick);
		
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		superArea.setOnClick(slotMap.get(slot), onClick);
		
	}
	
}
