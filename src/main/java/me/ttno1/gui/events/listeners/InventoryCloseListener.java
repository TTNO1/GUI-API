package me.ttno1.gui.events.listeners;

import javax.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.ttno1.gui.GUIAPIPlugin;
import me.ttno1.gui.InventoryGUI;
import me.ttno1.gui.events.InventoryGUICloseEventFactory;

public class InventoryCloseListener implements Listener {
	
	private InventoryGUICloseEventFactory eventFactory;
	
	private PluginManager pluginManager;
	
	@Inject
	public InventoryCloseListener(PluginManager pluginManager, InventoryGUICloseEventFactory eventFactory, @GUIAPIPlugin Plugin plugin) {
		
		this.pluginManager = pluginManager;
		
		this.eventFactory = eventFactory;
		
		pluginManager.registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onEvent(InventoryCloseEvent event) {
		
		if (event.getInventory().getHolder() instanceof InventoryGUI) {
			
			pluginManager.callEvent(eventFactory.create(event.getView(), (InventoryGUI) event.getInventory().getHolder()));
			
		}
		
	}
	
}
