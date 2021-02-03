package me.ttno1.gui.bukkitfactories;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

public class BukkitModule extends AbstractModule{

	public void cofigure() {
		
		bind(InventoryFactory.class).to(StdInventoryFactory.class);
		
		bind(StdInventoryFactory.class).in(Singleton.class);
		
	}
	
}
