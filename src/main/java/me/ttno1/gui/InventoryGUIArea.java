package me.ttno1.gui;

import java.util.AbstractList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import me.ttno1.gui.events.InventoryGUIClickEvent;

/**
 * Represents an area of slots in an {@linkplain InventoryGUI InventoryGUI}.
 */
public interface InventoryGUIArea {

	/**
	 * Returns the size of the InventoryGUIArea.
	 *
	 * @return The size of the InventoryGUIArea.
	 */
	int getSize();
	
	/**
	 * Returns the width of the InventoryGUIArea.
	 *
	 * @return The width of the InventoryGUIArea.
	 */
	int getWidth();
	
	/**
	 * Returns the height of the InventoryGUIArea.
	 *
	 * @return The height of the InventoryGUIArea.
	 */
	int getHeight();
	
	/**
	 * Returns the super/parent area of the InventoryGUIArea, or null if it has none.
	 *
	 * @return The super area of the InventoryGUIArea, or null if it has none.
	 */
	InventoryGUIArea getSuperArea();
	
	void setSuperArea(InventoryGUIArea value);
	
	InventoryGUIArea getNewSubArea(int startSlot, int endSlot);
	
	/**
	 * Returns a copy of the contents of the InventoryGUIArea.
	 *
	 * @return The contents of the InventoryGUIArea.
	 */
	ItemStack[] getContents();
	
	/**
	 * Sets the contents of the InventoryGUIArea.
	 *
	 * @param value The new contents for the InventoryGUIArea.
	 */
	void setContents(ItemStack[] value);
	
	void setContents(ItemStack[] value, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Sets the contents and "on click" actions of the InventoryGUIArea.
	 *
	 * @param value The new contents and their respective "on click" actions for the InventoryGUIArea.
	 */
	void setContents(LinkedHashMap<ItemStack, Consumer<InventoryGUIClickEvent>> value);
	
	/**
	 * Sets the contents and "on click" actions of the InventoryGUIArea.
	 *
	 * @param value The new contents for the InventoryGUIArea.
	 */
	void setContents(GUIItem[] value);
	
	void setContents(GUIItem[] value, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Returns the index of the row of the given slot.
	 * Note: Will not throw exception if the slot index is out of bounds.
	 *
	 * @param slot The index of the inventory slot.
	 * @return The index of the row (starts at 0).
	 */
	int getRowOf(int slot);
	
	/**
	 * Returns the index of the column of the given slot.
	 * Note: Will not throw exception if the slot index is out of bounds.
	 *
	 * @param slot The index of the inventory slot.
	 * @return The index of the column (starts at 0).
	 */
	int getColumnOf(int slot);
	
	/**
	 * Returns the slot at the given row and column (like coordinates).<br>
	 * Note: Will not throw exception if row and/or column indexes are out of bounds.
	 *
	 * @param column The index of the column (x cord).
	 * @param row The index of the row (y cord).
	 * @return The index of the inventory slot.
	 */
	int getSlotAt(int column, int row);
	
	/**
	 * Returns the item in the given slot of the InventoryGUIArea.
	 *
	 * @param slot The index of the inventory slot.
	 * @return The item in the specified slot
	 */
	ItemStack getItem(int slot);
	
	/**
	 * Sets the item of the given slot. This will remove any previously set "on click" actions.
	 *
	 * @param slot The index of the inventory slot.
	 * @param item The item to put in the slot.
	 */
	void setItem(int slot, ItemStack item);

	/**
	 * Sets the item and "on click" action of the given slot.
	 *
	 * @param slot The index of the inventory slot.
	 * @param item The item to put in the slot.
	 * @param onClick The action to be performed when the slot is clicked. Use null to remove.
	 */
	void setItem(int slot, ItemStack item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Sets the item and "on click" action of the given slot.<br>
	 * If the GUIItem has no "on click" action set, then it will be set to null (removed).
	 *
	 * @param slot The index of the inventory slot.
	 * @param item The item to put in the slot.
	 */
	void setItem(int slot, GUIItem item);

	/**
	 * Sets the item and "on click" action of the given slot.<br>
	 * The {@code onClick} parameter will override the "on click" action of the GUIItem.
	 *
	 * @param slot The index of the inventory slot.
	 * @param item The item to put in the slot.
	 * @param onClick The action to be performed when the slot is clicked. Use null to remove.
	 */
	void setItem(int slot, GUIItem item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Returns the action to perform when clicked of the given slot.
	 *
	 * @param slot The index of the inventory slot.
	 * @return The action to perform the slot is clicked.
	 */
	Consumer<InventoryGUIClickEvent> getOnClick(int slot);
	
	/**
	 * Sets the "on click" action for the given slot. Use null to remove.<br>
	 * Note: The slot index does not have to be within the boundaries of the inventory.
	 *
	 * @param slot The index of the inventory slot.
	 * @param onClick The action to be performed when the slot is clicked. Use null to remove.
	 */
	void setOnClick(int slot, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Replaces any occurrences of {@code item1} with {@code item2} and removes the "on click" action from any items replaced.
	 *
	 * @param item1 The item to be replaced.
	 * @param item2 The item to replace with.
	 */
	void replace(ItemStack item1, ItemStack item2);
	
	/**
	 * Replaces any occurrences of {@code item1} with {@code item2} and sets the "on click" action for any items replaced.
	 *
	 * @param item1 The item to be replaced.
	 * @param item2 The item to replace with.
	 * @param onClick The action to be performed when clicked. Use null to remove.
	 */
	void replace(ItemStack item1, ItemStack item2, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Replaces any occurrences of {@code item1} with {@code item2} and sets the "on click" action.
	 *
	 * @param item1 The item to be replaced.
	 * @param item2 The item to replace with.
	 */
	void replace(ItemStack item1, GUIItem item2);
	
	/**
	 * Replaces any occurrences of {@code item1} with {@code item2} and sets the "on click" action.<br>
	 * The {@code onClick} parameter will override the "on click" action of {@code item2}.
	 *
	 * @param item1 The item to be replaced.
	 * @param item2 The item to replace with.
	 * @param onClick The action to be performed when clicked. Use null to remove.
	 */
	void replace(ItemStack item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick);
	
	void replace(GUIItem item1, GUIItem item2);
	
	void replace(GUIItem item1, GUIItem item2, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Fills the entire InventoryGUIArea with the specified item and removes all "on click" actions.
	 *
	 * @param item The item to fill with.
	 */
	void fill(ItemStack item);
	
	/**
	 * Fills the entire InventoryGUIArea with the specified item and "on click" action.
	 *
	 * @param item The item to fill with.
	 * @param onClick The action to be performed when clicked. Use null to remove.
	 */
	void fill(ItemStack item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Fills the entire InventoryGUIArea with the given item and its "on click" action.
	 *
	 * @param item The item to fill with.
	 */
	void fill(GUIItem item);
	
	/**
	 * Fills the entire InventoryGUIArea with the specified item and "on click" action.<br>
	 * The {@code onClick} parameter overrides the "on click" action of the {@code item}.
	 *
	 * @param item The item to fill with.
	 * @param onClick The action to be performed when clicked. Use null to remove.
	 */
	void fill(GUIItem item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Fills the entire row at the given index with the given item and "on click" action.
	 *
	 * @param rowNumber The index of the row (starts at 0).
	 * @param item The item to fill the row with.
	 * @param onClick The "on click" action to fill the row with.
	 */
	void setRow(int rowNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Fills the entire row at the given index with the given item and removes any "on click" actions.
	 *
	 * @param rowNumber The index of the row (starts at 0).
	 * @param item The item to fill the row with.
	 */
	void setRow(int rowNumber, ItemStack item);
	
	/**
	 * Fills the entire row at the given index with the given item and "on click" action.<br>
	 * The {@code onClick} parameter will override the "on click" action of the GUIItem.
	 *
	 * @param rowNumber The index of the row (starts at 0).
	 * @param item The item to fill the row with.
	 * @param onClick The "on click" action to fill the row with.
	 */
	void setRow(int rowNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Fills the entire row at the given index with the given item and "on click" action.<br>
	 * If the GUIItem has no "on click" action set, then it will be set to null (removed).
	 *
	 * @param rowNumber The index of the row (starts at 0).
	 * @param item The item to fill the row with.
	 */
	void setRow(int rowNumber, GUIItem item);
	
	/**
	 * Sets the column.
	 *
	 * @param columnNumber the column number
	 * @param item the item
	 * @param onClick the on click
	 */
	void setColumn(int columnNumber, ItemStack item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Sets the column.
	 *
	 * @param columnNumber the column number
	 * @param item the item
	 */
	void setColumn(int columnNumber, ItemStack item);
	
	/**
	 * Sets the column.
	 *
	 * @param columnNumber the column number
	 * @param item the item
	 * @param onClick the on click
	 */
	void setColumn(int columnNumber, GUIItem item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Sets the column.
	 *
	 * @param columnNumber the column number
	 * @param item the item
	 */
	void setColumn(int columnNumber, GUIItem item);
	
	/**
	 * Adds the border.
	 *
	 * @param vertical the vertical
	 * @param horizontal the horizontal
	 * @param item the item
	 */
	void addBorder(int vertical, int horizontal, ItemStack item);
	
	/**
	 * Adds the border.
	 *
	 * @param vertical the vertical
	 * @param horizontal the horizontal
	 * @param item the item
	 * @param onClick the on click
	 */
	void addBorder(int vertical, int horizontal, ItemStack item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Adds the border.
	 *
	 * @param vertical the vertical
	 * @param horizontal the horizontal
	 * @param item the item
	 */
	void addBorder(int vertical, int horizontal, GUIItem item);
	
	/**
	 * Adds the border.
	 *
	 * @param vertical the vertical
	 * @param horizontal the horizontal
	 * @param item the item
	 * @param onClick the on click
	 */
	void addBorder(int vertical, int horizontal, GUIItem item, Consumer<InventoryGUIClickEvent> onClick);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 * @param startingIndex The list index at which items will start being listed from.
	 */
	void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 */
	void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 */
	void list(List<ItemStack> items, List<Consumer<InventoryGUIClickEvent>> onClicks);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 * @param startingIndex The list index at which items will start being listed from.
	 */
	void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 */
	void list(List<ItemStack> items, int horizontalSpacing, int verticalSpacing, int padding);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 */
	void list(List<ItemStack> items);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 * @param startingIndex The list index at which items will start being listed from.
	 */
	//AbstractList to avoid type erasure error
	void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 */
	void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks, int horizontalSpacing, int verticalSpacing, int padding);
	
	/**
	 * Lists the given items in the InventoryGUIArea. The items will be given the "on click" action in the {@code onClicks} list with the corresponding index.
	 * 
	 * @param items The items to be listed.
	 * @param onClicks The "on click" actions for each item.
	 */
	void list(AbstractList<GUIItem> items, List<Consumer<InventoryGUIClickEvent>> onClicks);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 * @param startingIndex The list index at which items will start being listed from.
	 */
	void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding, int startingIndex);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 * @param horizontalSpacing The amount of empty slots to the right of each item.
	 * @param verticalSpacing The amount of empty slots below each item.
	 * @param padding The amount of empty border around the list.
	 */
	void list(AbstractList<GUIItem> items, int horizontalSpacing, int verticalSpacing, int padding);
	
	/**
	 * Lists the given items in the InventoryGUIArea.
	 * 
	 * @param items The items to be listed.
	 */
	void list(AbstractList<GUIItem> items);
	
}
