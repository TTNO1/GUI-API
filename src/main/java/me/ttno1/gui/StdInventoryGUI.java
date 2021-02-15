package me.ttno1.gui;

import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.bukkitfactories.InventoryFactory;
import me.ttno1.gui.events.InventoryGUIClickEvent;
import me.ttno1.gui.events.InventoryGUICloseEvent;
import me.ttno1.gui.events.InventoryGUIOpenEvent;

public class StdInventoryGUI extends AbstractInventoryGUIArea implements InventoryGUI {
	
	private String title;
	
	private ItemStack[] items;
	
	Map<Integer, Consumer<InventoryGUIClickEvent>> onClickMap;
	
	private Consumer<InventoryGUIClickEvent> defaultOnClick;
	
	private Consumer<InventoryGUIOpenEvent> onOpen;
	
	private Consumer<InventoryGUICloseEvent> onClose;
	
	private InventoryFactory inventoryFactory;
	
	public StdInventoryGUI(String title, int size, InventoryGUIArea superArea, InventoryFactory inventoryFactory, Map<Integer, Consumer<InventoryGUIClickEvent>> onClickMap, StdInventoryGUISubAreaFactory subAreaFactory) {
		
		super(subAreaFactory);
		
		this.title = title;
		
		this.items = new ItemStack[size];
		
		this.superArea = superArea;
		
		this.onClickMap = onClickMap;
		
		this.inventoryFactory = inventoryFactory;
		
	}
	
	public StdInventoryGUI(String title, int size, InventoryFactory inventoryFactory, Map<Integer, Consumer<InventoryGUIClickEvent>> onClickMap, StdInventoryGUISubAreaFactory subAreaFactory) {
		
		this(title, size, null, inventoryFactory, onClickMap, subAreaFactory);
		
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
	public InventoryView open(HumanEntity player) {
		
		return player.openInventory(getInventory());
		
	}
	
	@Override
	public void setSuperArea(InventoryGUIArea value) {
		
		superArea = value;
		
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
	public ItemStack[] getContents() {
		
		return items.clone();
		
	}

	@Override
	public ItemStack getItem(int slot) {
		
		checkSlot(slot);
		
		return items[slot];
		
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
		
		checkSlot(slot);
		
		items[slot] = item;
		
		setOnClick(slot, onClick);
		
	}

	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		
		return onClickMap.get(slot);
		
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
		
		onClickMap.put(slot, onClick);
		
	}
	
}
