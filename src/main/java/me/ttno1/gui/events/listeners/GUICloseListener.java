package me.ttno1.gui.events.listeners;

import javax.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.ttno1.gui.GUIAPIPlugin;
import me.ttno1.gui.events.InventoryGUICloseEvent;

public class GUICloseListener implements Listener{
	
	@Inject
	public GUICloseListener(@GUIAPIPlugin Plugin plugin, PluginManager pluginManager) {
		
		pluginManager.registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onEvent(InventoryGUICloseEvent event) {
		
		if(event.getInventoryGUI().getOnClose() != null) {
			
			event.getInventoryGUI().getOnClose().accept(event);
			
		}
		
	}
	
}
