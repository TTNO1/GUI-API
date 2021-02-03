package me.ttno1.gui.events.listeners;

import javax.inject.Inject;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.ttno1.gui.GUIAPIPlugin;
import me.ttno1.gui.events.InventoryGUIOpenEvent;

public class GUIOpenListener implements Listener {

	@Inject
	public GUIOpenListener(@GUIAPIPlugin Plugin plugin, PluginManager pluginManager) {
		
		pluginManager.registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onEvent(InventoryGUIOpenEvent event) {
		
		if(event.getInventoryGUI().getOnOpen() != null) {
			
			event.getInventoryGUI().getOnOpen().accept(event);
			
		}
		
	}
	
}
