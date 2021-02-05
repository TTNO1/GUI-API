package me.ttno1.gui;

import java.util.Map;

import javax.inject.Inject;

import com.google.inject.Provider;

public class StdInventoryGUISubAreaFactory implements InventoryGUISubAreaFactory{
	
	private Provider<Map<Integer, Integer>> mapProvider;
	
	private Provider<StdInventoryGUISubAreaFactory> subAreaFactoryProvider;
	
	@Inject
	public StdInventoryGUISubAreaFactory(Provider<Map<Integer, Integer>> mapProvider, Provider<StdInventoryGUISubAreaFactory> subAreaFactoryProvider) {
		
		this.mapProvider = mapProvider;
		
		this.subAreaFactoryProvider = subAreaFactoryProvider;
		
	}
	
	@Override
	public InventoryGUIArea create(InventoryGUIArea superArea, int startSlot, int endSlot) {

		return new InventoryGUISubArea(superArea, startSlot, endSlot, mapProvider.get(), subAreaFactoryProvider.get());
		
	}

}
