package me.ttno1.gui.events.listeners;

import javax.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.ttno1.gui.GUIAPIPlugin;
import me.ttno1.gui.InventoryGUI;
import me.ttno1.gui.events.InventoryGUIClickEvent;

public class GUIClickListener implements Listener {
	
	@Inject
	public GUIClickListener(@GUIAPIPlugin Plugin plugin, PluginManager pluginManager) {
		
		pluginManager.registerEvents(this, plugin);
		
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEvent(InventoryGUIClickEvent event) {

		InventoryGUI gui = event.getInventoryGUI();

		if(gui.getOnClick(event.getRawSlot()) != null) {

			gui.getOnClick(event.getRawSlot()).accept(event);

		}else if(gui.getDefaultOnClick() != null) {

			gui.getDefaultOnClick().accept(event);

		}

	}
	
}
