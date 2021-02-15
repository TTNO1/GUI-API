package me.ttno1.gui;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ObjectArrays;

import me.ttno1.gui.events.InventoryGUIClickEvent;
@Deprecated
public abstract class AbstractMultiInventoryGUIArea extends AbstractInventoryGUIArea implements MultiInventoryGUIArea {

	protected List<? extends InventoryGUIArea> areas;
	
	protected Map<Integer, SlotData> slotMap;
	
	public AbstractMultiInventoryGUIArea(InventoryGUISubAreaFactory subAreaFactory, Map<Integer, SlotData> slotMap, List<? extends InventoryGUIArea> areas) {
		
		super(subAreaFactory);
		
		this.slotMap = slotMap;
		
		this.areas = areas;
		
	}
	
	protected void updateSlotMap() {
		
		slotMap.clear();
		
		int slot = 0;
		
		for(InventoryGUIArea area : areas) {
			
			for(int i = 0; i < area.getSize(); i++, slot++) {
				
				slotMap.put(slot, new SlotData(area, i));
				
			}
			
		}
		
	}

	@Override
	public ItemStack[] getContents() {
		
		ItemStack[] result = new ItemStack[0];
		
		for(InventoryGUIArea area : areas) {
			
			result = ObjectArrays.concat(result, area.getContents(), ItemStack.class);
			
		}
		
		return result;
		
	}

	@Override
	public ItemStack getItem(int slot) {
		
		return slotMap.get(slot).area.getItem(slotMap.get(slot).slot);
		
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		slotMap.get(slot).area.setItem(slotMap.get(slot).slot, item, onClick);
		
	}

	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		
		return slotMap.get(slot).area.getOnClick(slotMap.get(slot).slot);
		
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		slotMap.get(slot).area.setOnClick(slotMap.get(slot).slot, onClick);
		
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
