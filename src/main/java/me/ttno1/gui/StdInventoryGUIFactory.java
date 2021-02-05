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
	
	private Provider<StdInventoryGUISubAreaFactory> subAreaFactoryProvider;
	
	@Inject
	public StdInventoryGUIFactory(Provider<InventoryFactory> inventoryFactoryProvider, Provider<Map<Integer, Consumer<InventoryGUIClickEvent>>> mapProvider,Provider<StdInventoryGUISubAreaFactory> subAreaFactoryProvider) {
		
		this.inventoryFactoryProvider = inventoryFactoryProvider;
		
		this.mapProvider = mapProvider;
		
		this.subAreaFactoryProvider = subAreaFactoryProvider;
		
	}

	@Override
	public InventoryGUI create(String title, int size, InventoryGUIArea superArea) {
		
		return new StdInventoryGUI(title, size, superArea, inventoryFactoryProvider.get(), mapProvider.get(), subAreaFactoryProvider.get());
		
	}
	
	@Override
	public InventoryGUI create(String title, int size) {
		
		return new StdInventoryGUI(title, size, inventoryFactoryProvider.get(), mapProvider.get(), subAreaFactoryProvider.get());
		
	}

}
