package me.ttno1.gui;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ObjectArrays;

import me.ttno1.gui.events.InventoryGUIClickEvent;
/**
 * The InvnetoryGUIArea functionality behaves as though the sub-InventoryGUIs <br>are arranged in a single vertical column starting at the top and going down.
 */
public class StdMultiInventoryGUI extends AbstractInventoryGUIArea implements MultiInventoryGUI {
	
	private List<InventoryGUI> guis;
	
	private Map<Integer, SlotData> slotMap;
	
	private int size;
	
	private int height;
	
	public StdMultiInventoryGUI(InventoryGUIArea superArea, List<InventoryGUI> guiList, Map<Integer, SlotData> slotMap, StdInventoryGUISubAreaFactory subAreaFactory) {
		
		super(superArea, subAreaFactory);
		
		this.guis = guiList;
		
		this.slotMap = slotMap;
		
		updateSize();
		
	}
	
	private void updateSize() {
		
		int slot = 0;
		
		for(InventoryGUI gui : guis) {
			
			for(int i = 0; i < gui.getSize(); i++, slot++) {
				
				slotMap.put(slot, new SlotData(gui, i));
				
			}
			
		}
		
		int size = 0;
		
		for(InventoryGUI gui : guis) {
			
			size += gui.getSize();
			
		}
		
		this.size = size;
		
		int height = 0;
		
		for(InventoryGUI gui : guis) {
			
			height += gui.getHeight();
			
		}
		
		this.height = height;
		
	}

	@Override
	public int getSize() {
		
		return size;
		
	}
	
	@Override
	public int getLength() {
		
		return guis.size();
		
	}

	@Override
	public int getWidth() {
		
		return 9;
		
	}

	@Override
	public int getHeight() {
		
		return height;
		
	}
	
	@Override
	public ItemStack[] getContents() {
		
		ItemStack[] result = new ItemStack[0];
		
		for(InventoryGUI gui : guis) {
			
			result = ObjectArrays.concat(result, gui.getContents(), ItemStack.class);
			
		}
		
		return result;
		
	}
	
	@Override
	public ItemStack getItem(int slot) {
		
		return slotMap.get(slot).gui.getItem(slotMap.get(slot).slot);
	
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		slotMap.get(slot).gui.setItem(slotMap.get(slot).slot, item, onClick);
		
	}

	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		
		return slotMap.get(slot).gui.getOnClick(slotMap.get(slot).slot);
		
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		slotMap.get(slot).gui.setOnClick(slotMap.get(slot).slot, onClick);
		
	}
	
	public static class SlotData {
		
		public final InventoryGUI gui;
		
		public final int slot;
		
		public SlotData(InventoryGUI gui, int slot) {
			
			this.gui = gui;
			
			this.slot = slot;
			
		}
		
	}
	
}
