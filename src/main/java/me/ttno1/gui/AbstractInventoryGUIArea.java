package me.ttno1.gui;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public abstract class AbstractInventoryGUIArea implements InventoryGUIArea {
	
	protected InventoryGUIArea superArea;
	
	protected InventoryGUISubAreaFactory subAreaFactory;
	
	public AbstractInventoryGUIArea(InventoryGUISubAreaFactory subAreaFactory) {
		
		this.subAreaFactory = subAreaFactory;
		
	}
	
	@Override
	public InventoryGUIArea getSuperArea() {
		
		return superArea;
		
	}
	
	@Override
	public InventoryGUIArea getNewSubArea(int startSlot, int endSlot) {
		
		return subAreaFactory.create(this, startSlot, endSlot);
		
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
		
		for(int i = 0; i < getSize() && i < value.length; i++) {
			
			setItem(i, value[i]);
			
		}
		
	}
	
	@Override
	public void setContents(GUIItem[] value, Consumer<InventoryGUIClickEvent> onClick) {
		
		ItemStack[] newArray = new ItemStack[value.length];
		
		for(int i = 0; i < value.length; i++) {
			
			newArray[i] = value[i].getGUIItem();
			
		}
		
		setContents(newArray, onClick);
		
	}

	protected boolean checkSlot(int slot) {
		
		if(slot >= getSize() || slot < 0) {
			
			throw new IndexOutOfBoundsException("Slot index out of bounds for InventoryGUIArea");
			
		}
		
		return true;
		
	}
	
	@Override
	public int getRowOf(int slot) {
		
		return (int) Math.floor(slot/getWidth());
		
	}

	@Override
	public int getColumnOf(int slot) {
		
		return slot % getWidth();
		
	}

	@Override
	public int getSlotAt(int column, int row) {
		
		return row * getWidth() + column;
		
	}

	@Override
	public void setItem(int slot, ItemStack item) {
		
		setItem(slot, item, null);
		
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
		
		for(int i = rowNumber * getWidth(); i < ((rowNumber + 1) * getWidth()) - 1; i++) {
			
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
		
		for(int i = columnNumber; i <= getSize() - (getWidth() - columnNumber); i += getWidth()) {
			
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
		
		for(int i = getWidth() - 1; i >= getWidth() - horizontal; i--) {
			
			setRow(i, item, onClick);
			
		}
		
		for(int i = getWidth() - 1; i >= getWidth() - vertical; i--) {
			
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
		
		for(int row = padding, index = startingIndex; row < getHeight() - padding && index < items.size(); row += verticalSpacing + 1) {
			
			for(int column = padding; column < getWidth() - padding && index < items.size(); column = column + horizontalSpacing + 1, index++) {
				
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
