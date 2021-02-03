package me.ttno1.gui.events;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

import me.ttno1.gui.events.listeners.GUIClickListener;
import me.ttno1.gui.events.listeners.GUICloseListener;
import me.ttno1.gui.events.listeners.InventoryClickListener;
import me.ttno1.gui.events.listeners.InventoryCloseListener;
import me.ttno1.gui.events.listeners.InventoryOpenListener;

public class EventsModule extends AbstractModule {

	@Override
	public void configure() {
		
		bind(InventoryGUIClickEventFactory.class).to(StdInventoryGUIClickEventFactory.class);
		
		bind(StdInventoryGUIClickEventFactory.class).in(Singleton.class);
		
		bind(InventoryGUICloseEventFactory.class).to(StdInventoryGUICloseEventFactory.class);
		
		bind(StdInventoryGUICloseEventFactory.class).in(Singleton.class);
		
		bind(InventoryGUIOpenEventFactory.class).to(StdInventoryGUIOpenEventFactory.class);
		
		bind(StdInventoryGUIOpenEventFactory.class).in(Singleton.class);
		
		bind(GUIClickListener.class).asEagerSingleton();
		
		bind(GUICloseListener.class).asEagerSingleton();
		
		bind(InventoryClickListener.class).asEagerSingleton();
		
		bind(InventoryCloseListener.class).asEagerSingleton();
		
		bind(InventoryOpenListener.class).asEagerSingleton();
		
	}
	
}
