package me.ttno1.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Singleton;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class GUIAPIModule extends AbstractModule {

	private Plugin plugin;
	
	public GUIAPIModule(Plugin plugin) {
		
		this.plugin = plugin;
		
	}
	
	@Override
	public void configure() {
		
		bind(Plugin.class).annotatedWith(GUIAPIPlugin.class).toInstance(plugin);
		
		bind(InventoryGUIFactory.class).to(StdInventoryGUIFactory.class);
		
		bind(StdInventoryGUIFactory.class).in(Singleton.class);
		
		bind(InventoryGUIRegistry.class).to(StdInventoryGUIRegistry.class);
		
		bind(new TypeLiteral<Map<Inventory, ? extends InventoryGUI>>(){}).to(new TypeLiteral<HashMap<Inventory, InventoryGUI>>(){});
		
		bind(new TypeLiteral<Map<Integer, Integer>>(){}).to(new TypeLiteral<HashMap<Integer, Integer>>(){});
		
		bind(new TypeLiteral<Map<Integer, Consumer<InventoryGUIClickEvent>>>(){}).to(new TypeLiteral<HashMap<Integer, Consumer<InventoryGUIClickEvent>>>(){});
		
	}
	
}
