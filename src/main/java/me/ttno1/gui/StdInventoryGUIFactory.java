package me.ttno1.gui;

import java.util.Map;
import java.util.function.Consumer;

import com.google.inject.Inject;
import com.google.inject.Provider;

import me.ttno1.gui.bukkitfactories.InventoryFactory;
import me.ttno1.gui.events.InventoryGUIClickEvent;

public class StdInventoryGUIFactory implements InventoryGUIFactory{
	
	private Provider<InventoryFactory> inventoryFactoryProvider;
	
	private Provider<Map<Integer, Consumer<InventoryGUIClickEvent>>> mapProvider;
	
	@Inject
	public StdInventoryGUIFactory(Provider<InventoryFactory> inventoryFactoryProvider, Provider<Map<Integer, Consumer<InventoryGUIClickEvent>>> mapProvider) {
		
		this.inventoryFactoryProvider = inventoryFactoryProvider;
		
		this.mapProvider = mapProvider;
		
	}

	@Override
	public InventoryGUI create(String title, int size) {
		
		return new StdInventoryGUI(title, size, inventoryFactoryProvider.get(), mapProvider.get());
		
	}

}
