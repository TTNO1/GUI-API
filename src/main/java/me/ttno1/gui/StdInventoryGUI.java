package me.ttno1.gui;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.bukkitfactories.InventoryFactory;
import me.ttno1.gui.events.InventoryGUIClickEvent;
import me.ttno1.gui.events.InventoryGUICloseEvent;
import me.ttno1.gui.events.InventoryGUIOpenEvent;

public class StdInventoryGUI implements InventoryGUI {
	
	private String title;
	
	private ItemStack[] items;
	
	Map<Integer, Consumer<InventoryGUIClickEvent>> onClickMap;
	
	private Consumer<InventoryGUIClickEvent> defaultOnClick;
	
	private Consumer<InventoryGUIOpenEvent> onOpen;
	
	private Consumer<InventoryGUICloseEvent> onClose;
	
	private InventoryFactory inventoryFactory;
	
	public StdInventoryGUI(String title, int size, InventoryFactory inventoryFactory, Map<Integer, Consumer<InventoryGUIClickEvent>> onClickMap) {
		
		this.title = title;
		
		this.items = new ItemStack[size];
		
		this.onClickMap = onClickMap;
		
		this.inventoryFactory = inventoryFactory;
		
	}

	@Override
	public String getTitle() {
		
		return title;
		
	}
	
	@Override
	public void setTitle(String value) {
		
		title = value;
		
	}
	
	@Override
	public Consumer<InventoryGUIClickEvent> getDefaultOnClick() {
		
		return defaultOnClick;
		
	}
	
	@Override
	public void setDefaultOnClick(Consumer<InventoryGUIClickEvent> onClick) {
		
		defaultOnClick = onClick;
		
	}
	
	@Override
	public Consumer<InventoryGUIOpenEvent> getOnOpen() {
		
		return onOpen;
		
	}
	
	@Override
	public void setOnOpen(Consumer<InventoryGUIOpenEvent> value) {
		
		onOpen = value;
		
	}
	
	@Override
	public Consumer<InventoryGUICloseEvent> getOnClose() {
		
		return onClose;
		
	}
	
	@Override
	public void setOnClose(Consumer<InventoryGUICloseEvent> value) {
		
		onClose = value;
		
	}
	
	@Override
	public Inventory getInventory() {
		
		Inventory inventory = inventoryFactory.create(this, items.length, title);
		
		inventory.setContents(items);
		
		return inventory;
		
	}
	
	@Override
	public void open(HumanEntity player) {
		
		player.openInventory(getInventory());
		
	}

	@Override
	public int getSize() {
		
		return items.length;
		
	}

	@Override
	public int getWidth() {
		
		return 9;
		
	}

	@Override
	public int getHeight() {
		
		return (int) Math.ceil(getSize()/getWidth());
		
	}

	@Override
	public InventoryGUIArea getSuperArea() {
		
		return null;
		
	}

	@Override
	public ItemStack[] getContents() {
		
		return items.clone();
		
	}

	@Override
	public void setContents(ItemStack[] value) {
		
		setContents(value, null);
		
	}
	
	@Override
	public void setContents(ItemStack[] value, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < items.length && i < value.length; i++) {
			
			setItem(i, value[i], onClick);
			
		}
		
	}
	
	@Override
	public void setContents(LinkedHashMap<ItemStack, Consumer<InventoryGUIClickEvent>> value) {
		
		ItemStack[] keys = (ItemStack[]) value.keySet().toArray();
		
		for(int i = 0; i < items.length && i < value.size(); i++) {
			
			setItem(i, keys[i], value.get(keys[i]));
			
		}
		
	}

	@Override
	public void setContents(GUIItem[] value) {
		
		setContents(value, null);
		
	}
	
	@Override
	public void setContents(GUIItem[] value, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < items.length && i < value.length; i++) {
			
			setItem(i, value[i], onClick);
			
		}
		
	}

	private void checkSlot(int slot) {
		
		if(slot >= getSize() || slot < 0) {
			
			throw new IndexOutOfBoundsException("Slot index out of bounds for InventoryGUIArea");
			
		}
		
	}
	
	@Override
	public int getRowOf(int slot) {
		
		checkSlot(slot);
		
		return (int) Math.floor(slot/getWidth());
		
	}

	@Override
	public int getColumnOf(int slot) {
		
		checkSlot(slot);
		
		return slot % getWidth();
		
	}

	@Override
	public int getSlotAt(int column, int row) {
		
		return row * getWidth() + column;
		
	}

	@Override
	public ItemStack getItem(int slot) {
		
		checkSlot(slot);
		
		return items[slot];
		
	}

	@Override
	public void setItem(int slot, ItemStack item) {
		
		setItem(slot, item, null);
		
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		items[slot] = item;
		
		setOnClick(slot, onClick);
		
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
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		
		return onClickMap.get(slot);
		
	}

	/**
	 * Sets the "on click" action for the given slot. Use null to remove.<br>
	 * Note: The slot index does not have to be within the boundaries of the inventory.
	 *
	 * @param slot The index of the inventory slot.
	 * @param onClick The action to be performed when the slot is clicked. Use null to remove.
	 */
	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		onClickMap.put(slot, onClick);
		
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2) {
		
		replace(item1, item2, null);
		
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2, Consumer<InventoryGUIClickEvent> onClick) {
		
		for(int i = 0; i < items.length; i++) {
			
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
		
		for(int i = 0; i < items.length; i++) {
			
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
		
		for(int i = columnNumber; i <= getSize() - (getWidth() - columnNumber); i = i + getWidth()) {
			
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
		
		for(int row = padding, index = startingIndex; row < getHeight() - padding && index < items.size(); row = row + verticalSpacing + 1) {
			
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
