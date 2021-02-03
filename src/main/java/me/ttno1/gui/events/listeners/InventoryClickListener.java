package me.ttno1.gui.events.listeners;

import javax.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.ttno1.gui.GUIAPIPlugin;
import me.ttno1.gui.InventoryGUI;
import me.ttno1.gui.events.InventoryGUIClickEvent;
import me.ttno1.gui.events.InventoryGUIClickEventFactory;

public class InventoryClickListener implements Listener{
	
	private InventoryGUIClickEventFactory eventFactory;
	
	private PluginManager pluginManager;
	
	@Inject
	public InventoryClickListener(PluginManager pluginManager, InventoryGUIClickEventFactory eventFactory, @GUIAPIPlugin Plugin plugin) {
		
		this.pluginManager = pluginManager;
		
		this.eventFactory = eventFactory;
		
		pluginManager.registerEvents(this, plugin);
		
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEvent(InventoryClickEvent event) {
		
		if(event.getInventory().getHolder() instanceof InventoryGUI) {
			
			InventoryGUIClickEvent guiEvent = eventFactory.create(event.getView(), event.getSlotType(), event.getRawSlot(), event.getClick(), event.getAction(), event.getHotbarButton(), (InventoryGUI) event.getInventory().getHolder());
			
			pluginManager.callEvent(guiEvent);
			//TODO Might not be necessary, or might need to set other options for event
			event.setCancelled(guiEvent.isCancelled());
			
		}
		
	}
	
}
