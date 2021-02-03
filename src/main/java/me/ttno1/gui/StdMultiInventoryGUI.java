package me.ttno1.gui;

import java.util.AbstractList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class StdMultiInventoryGUI implements MultiInventoryGUI {

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
	public InventoryGUIArea getSuperArea() {
		return null;
	}

	@Override
	public ItemStack[] getContents() {
		return null;
	}

	@Override
	public void setContents(ItemStack[] value) {
	}

	@Override
	public void setContents(ItemStack[] value, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setContents(LinkedHashMap<ItemStack, Consumer<InventoryGUIClickEvent>> value) {
	}

	@Override
	public void setContents(GUIItem[] value) {
	}

	@Override
	public void setContents(GUIItem[] value, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public int getRowOf(int slot) {
		return 0;
	}

	@Override
	public int getColumnOf(int slot) {
		return 0;
	}

	@Override
	public int getSlotAt(int column, int row) {
		return 0;
	}

	@Override
	public ItemStack getItem(int slot) {
		return null;
	}

	@Override
	public void setItem(int slot, ItemStack item) {
	}

	@Override
	public void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setItem(int slot, GUIItem item) {
	}

	@Override
	public void setItem(int slot, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public Consumer<InventoryGUIClickEvent> getOnClick(int slot) {
		return null;
	}

	@Override
	public void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2) {
	}

	@Override
	public void replace(ItemStack item1, ItemStack item2, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void replace(ItemStack item1, GUIItem item2) {
	}

	@Override
	public void replace(ItemStack item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void replace(GUIItem item1, GUIItem item2) {
	}

	@Override
	public void replace(GUIItem item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void fill(ItemStack item) {
	}

	@Override
	public void fill(ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void fill(GUIItem item) {
	}

	@Override
	public void fill(GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setRow(int rowNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setRow(int rowNumber, ItemStack item) {
	}

	@Override
	public void setRow(int rowNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setRow(int rowNumber, GUIItem item) {
	}

	@Override
	public void setColumn(int columnNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setColumn(int columnNumber, ItemStack item) {
	}

	@Override
	public void setColumn(int columnNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void setColumn(int columnNumber, GUIItem item) {
	}

	@Override
	public void addBorder(int vertical, int horizontal, ItemStack item) {
	}

	@Override
	public void addBorder(int vertical, int horizontal, ItemStack item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void addBorder(int vertical, int horizontal, GUIItem item) {
	}

	@Override
	public void addBorder(int vertical, int horizontal, GUIItem item, Consumer<InventoryGUIClickEvent> onClick) {
	}

	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing,
			int verticalSpacing, int padding, int startingIndex) {
	}

	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing,
			int verticalSpacing, int padding) {
	}

	@Override
	public void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks) {
	}

	@Override
	public void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding,
			int startingIndex) {
	}

	@Override
	public void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding) {
	}

	@Override
	public void list(List<ItemStack> items) {
	}

	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks,
			int horizontalSpacing, int verticalSpacing, int padding, int startingIndex) {
	}

	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks,
			int horizontalSpacing, int verticalSpacing, int padding) {
	}

	@Override
	public void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks) {
	}

	@Override
	public void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding,
			int startingIndex) {
	}

	@Override
	public void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding) {
	}

	@Override
	public void list(AbstractList<GUIItem> items) {
	}
		
}
