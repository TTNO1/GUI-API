package me.ttno1.gui;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class InventoryGUISubArea implements InventoryGUIArea {

	private InventoryGUIArea superArea;
	
	private Map<Integer, Integer> slotMap;
	
	private int width;
	
	private int height;
	
	public InventoryGUISubArea(InventoryGUIArea superArea, int startSlot, int endSlot, Map<Integer, Integer> slotMap) {
		
		if(startSlot >= superArea.getSize() || startSlot < 0 || endSlot >= superArea.getSize() || endSlot < 0) {
			
			throw new IllegalArgumentException("InventoryGUISubArea out of bounds of super-area");
			
		}
		
		if (startSlot > endSlot) {
			
			int tempStartSlot = startSlot;
			
			startSlot = endSlot;
			
			endSlot = tempStartSlot;
			
		}
		
		this.superArea = superArea;
		
		width = (superArea.getColumnOf(endSlot) - superArea.getColumnOf(startSlot)) + 1;
		
		height = (superArea.getRowOf(endSlot) - superArea.getRowOf(startSlot)) + 1;
		
		this.slotMap = slotMap;
		
		for(int subSlot = 0, superSlot = startSlot; subSlot < width * height; subSlot++, superSlot++) {
			
			if(superArea.getColumnOf(superSlot) > superArea.getColumnOf(endSlot) || superArea.getColumnOf(superSlot) < superArea.getColumnOf(startSlot)) {
				
				superSlot = superArea.getSlotAt(superArea.getColumnOf(startSlot), superArea.getRowOf(superSlot) + 1);
				
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
	public InventoryGUIArea getSuperArea() {
		
		return superArea;
		
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
	public void setContents(ItemStack[] value) {
		
		setContents(value, null);
		
	}
	
	@Override
	public void setContents(ItemStack[] value, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < getSize() && i < value.length; i++) {
			
			setItem(i, value[i], onClick);
			
		}
		
	}
	
	@Override
	public void setContents(LinkedHashMap<ItemStack, Consumer<InventoryGUIClickEvent>> value) {
		
		ItemStack[] keys = (ItemStack[]) value.keySet().toArray();
		
		for(int i = 0; i < getSize() && i < value.size(); i++) {
			
			setItem(i, keys[i], value.get(keys[i]));
			
		}
		
	}

	@Override
	public void setContents(GUIItem[] value) {
		
		setContents(value, null);
		
	}
	
	@Override
	public void setContents(GUIItem[] value, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < getSize() && i < value.length; i++) {
			
			setItem(i, value[i], onClick);
			
		}
		
	}

	private void checkSlot(int slot) {
		
		if(slot >= getSize() || slot < 0) {
			
			throw new IndexOutOfBoundsException("Slot index out of bounds for InventoryGUIArea");
			
		}
		
	}
	
	@Override
	public ItemStack getItem(int slot) {

		checkSlot(slot);
		
		return superArea.getItem(slotMap.get(slot));
		
	}

	@Override
	public int getRowOf(int slot) {
		
		checkSlot(slot);
		
		return (int) Math.floor(slot/width);
		
	}
	
	@Override
	public int getColumnOf(int slot) {
		
		checkSlot(slot);
		
		return slot % width;
		
	}
	
	@Override
	public int getSlotAt(int column, int row) {
		
		return row * width + column;
		
	}
	
	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {

		checkSlot(slot);
		
		return superArea.getOnClick(slotMap.get(slot));
		
	}

	@Override
	public void setItem(int slot, ItemStack item) {
		
		setItem(slot, item, null);
		
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		superArea.setItem(slotMap.get(slot), item, onClick);
		
	}

	@Override
	public void setItem(int slot, GUIItem item) {
		
		setItem(slot, item, item.getOnGUIClick());
		
	}

	@Override
	public void setItem(int slot, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
		
		setItem(slot, item.getGUIItem(), onClick);
		
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		superArea.setOnClick(slotMap.get(slot), onClick);
		
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2) {
		
		replace(item1, item2, null);
		
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < getSize(); i++) {
			
			if(getItem(i).equals(item1)) {
				
				setItem(i, item2, onClick);
				
			}
			
		}
		
	}

	@Override
	public void replace(ItemStack item1, GUIItem item2) {
		
		replace(item1, item2, item2.getOnGUIClick());
		
	}

	@Override
	public void replace(ItemStack item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick) {
		
		replace(item1, item2.getGUIItem(), item2.getOnGUIClick());
		
	}
	
	@Override
	public void replace(GUIItem item1, GUIItem item2) {
		
		replace(item1.getGUIItem(), item2);
		
	}
	
	@Override
	public void replace(GUIItem item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick) {
		
		replace(item1.getGUIItem(), item2, onClick);
		
	}

	@Override
	public void fill(ItemStack item) {
		
		fill(item, null);
		
	}

	@Override
	public void fill(ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < getSize(); i++) {
			
			setItem(i, item, onClick);
			
		}
		
	}

	@Override
	public void fill(GUIItem item) {
		
		fill(item, item.getOnGUIClick());
		
	}

	@Override
	public void fill(GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
		
		fill(item.getGUIItem(), onClick);
		
	}
	
	@Override
	public void setRow(int rowNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = rowNumber * width; i < ((rowNumber + 1) * width) - 1; i++) {
			
			setItem(i, item, onClick);
			
		}
		
	}

	@Override
	public void setRow(int rowNumber, ItemStack item) {
		
		setRow(rowNumber, item, null);
		
	}

	@Override
	public void setRow(int rowNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
		
		setRow(rowNumber, item.getGUIItem(), onClick);
		
	}

	@Override
	public void setRow(int rowNumber, GUIItem item) {
		
		setRow(rowNumber, item, item.getOnGUIClick());
		
	}

	@Override
	public void setColumn(int columnNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = columnNumber; i <= getSize() - (width - columnNumber); i = i + width) {
			
			setItem(i, item, onClick);
			
		}
		
	}

	@Override
	public void setColumn(int columnNumber, ItemStack item) {
		
		setColumn(columnNumber, item, null);
		
	}

	@Override
	public void setColumn(int columnNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
		
		setColumn(columnNumber, item.getGUIItem(), onClick);
		
	}

	@Override
	public void setColumn(int columnNumber, GUIItem item) {
		
		setColumn(columnNumber, item, item.getOnGUIClick());
		
	}

	@Override
	public void addBorder(int vertical, int horizontal, ItemStack item) {
		
		addBorder(vertical, horizontal, item, null);
		
	}

	@Override
	public void addBorder(int vertical, int horizontal, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < horizontal; i++) {
			
			setRow(i, item, onClick);
			
		}
		
		for(int i = 0; i < vertical; i++) {
			
			setColumn(i, item, onClick);
			
		}
		
		for(int i = width - 1; i >= width - horizontal; i--) {
			
			setRow(i, item, onClick);
			
		}
		
		for(int i = width - 1; i >= width - vertical; i--) {
			
			setColumn(i, item, onClick);
			
		}
		
	}

	@Override
	public void addBorder(int vertical, int horizontal, GUIItem item) {
		
		addBorder(vertical, horizontal, item, item.getOnGUIClick());
		
	}

	@Override
	public void addBorder(int vertical, int horizontal, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
		
		addBorder(vertical, horizontal, item.getGUIItem(), onClick);
		
	}
	
	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex) {
		
		if(onClicks == null) {
			
			onClicks = new ArrayList<Consumer<InventoryGUIClickEvent>>(0);
			
		}
		
		for(int row = padding, index = startingIndex; row < height - padding && index < items.size(); row = row + verticalSpacing + 1) {
			
			for(int column = padding; column < width - padding && index < items.size(); column = column + horizontalSpacing + 1, index++) {
				
				Consumer<InventoryGUIClickEvent> onClick = null;
				
				if(index < onClicks.size()) {
					
					onClick = onClicks.get(index);
					
				}
				
				setItem(getSlotAt(column, row), items.get(index), onClick);
				
			}
			
		}
		
	}
	
	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding) {
		
		list(items, onClicks, horizontalSpacing, verticalSpacing, padding, 0);
		
	}
	
	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks) {
		
		list(items, onClicks, 0, 0, 0);
		
	}
	
	@Override
	public void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex) {
		
		list(items, null, horizontalSpacing, verticalSpacing, padding, startingIndex);
		
	}
	
	@Override
	public void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding) {
		
		list(items, horizontalSpacing, verticalSpacing, padding, 0);
		
	}
	
	@Override
	public void list(List<ItemStack> items) {
		
		list(items, null);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex) {
		
		List<ItemStack> newItems = new ArrayList<ItemStack>(items.size());
		
		for(int i = 0; i < items.size(); i++) {
			
			newItems.add(items.get(i).getGUIItem());
			
		}
		
		list(newItems, onClicks, horizontalSpacing, verticalSpacing, padding, startingIndex);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding) {
		
		list(items, onClicks, horizontalSpacing, verticalSpacing, padding, 0);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks) {
		
		list(items, onClicks, 0, 0, 0);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex) {
		
		List<Consumer<InventoryGUIClickEvent>> newOnClicks = new ArrayList<Consumer<InventoryGUIClickEvent>>(items.size());
		
		for(int i = 0; i < items.size(); i++) {
			
			newOnClicks.add(items.get(i).getOnGUIClick());
			
		}
		
		list(items, newOnClicks, horizontalSpacing, verticalSpacing, padding, startingIndex);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding) {
		
		list(items, horizontalSpacing, verticalSpacing, padding, 0);
		
	}
	
	@Override
	public void list(AbstractList<GUIItem> items) {
		
		list(items, 0, 0, 0);
		
	}
	
}
