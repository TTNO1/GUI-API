package me.ttno1.gui;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class AbstractMultiInventoryGUIArea extends AbstractInventoryGUIArea implements MultiInventoryGUIArea {

	private List<InventoryGUIArea> areas;
	
	private Map<Integer, SlotData> slotMap;
	
	private int size;
	
	private int width;
	
	private int height;
	
	public AbstractMultiInventoryGUIArea(InventoryGUIArea superArea, InventoryGUISubAreaFactory subAreaFactory) {
		
		super(superArea, subAreaFactory);
		
	}

	private void updateSize() {
		
		int slot = 0;
		
		for(InventoryGUIArea area : areas) {
			
			for(int i = 0; i < area.getSize(); i++, slot++) {
				
				slotMap.put(slot, new SlotData(area, i));
				
			}
			
		}
		
		int size = 0;
		
		for(InventoryGUIArea area : areas) {
			
			size += area.getSize();
			
		}
		
		this.size = size;
		
		int height = 0;
		
		for(InventoryGUIArea area : areas) {
			
			height += area.getHeight();
			
		}
		
		this.height = height;
		
	}
	
	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public int getWidth() {
		return 0;
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

	public static class SlotData {
		
		public final InventoryGUIArea area;
		
		public final int slot;
		
		public SlotData(InventoryGUIArea area, int slot) {
			
			this.area = area;
			
			this.slot = slot;
			
		}
		
	}
	
}
