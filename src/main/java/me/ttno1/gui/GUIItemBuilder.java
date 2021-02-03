package me.ttno1.gui;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import com.google.common.collect.Multimap;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public interface GUIItemBuilder {
	
	GUIItemBuilder setType(Material value);
	
	GUIItemBuilder setAmount(int value);
	
	GUIItemBuilder addEnchantment(Enchantment enchant, int level);
	
	GUIItemBuilder addEnchantments(Map<Enchantment, Integer> enchants);
	
	GUIItemBuilder setEnchantments(Map<Enchantment, Integer> enchants);
	
	GUIItemBuilder removeEnchantment(Enchantment enchant);
	
	GUIItemBuilder addPotionEffects(PotionEffect ... effects);
	
	GUIItemBuilder addPotionEffects(Collection<PotionEffect> effects);
	
	GUIItemBuilder setPotionEffects(Collection<PotionEffect> effects);
	
	GUIItemBuilder removePotionEffects(PotionEffect ... effect);
	
	GUIItemBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier);
	
	GUIItemBuilder addAttributeModifier(Attribute attribute, String name, double amount);
	
	GUIItemBuilder addAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes);
	
	GUIItemBuilder setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes);
	
	GUIItemBuilder removeAttributeModifier(Attribute attribute, AttributeModifier attributeModifier);
	
	GUIItemBuilder addItemFlags(ItemFlag ... flags);
	
	GUIItemBuilder addItemFlags(Collection<ItemFlag> flags);
	
	GUIItemBuilder setItemFlags(Collection<ItemFlag> flags);
	
	GUIItemBuilder removeItemFlags(ItemFlag ... flag);
	
	GUIItemBuilder setColor(Color value);
	
	GUIItemBuilder setCustomModelData(int value);
	
	GUIItemBuilder setDisplayName(String value);
	
	GUIItemBuilder setLocalizedName(String value);
	
	GUIItemBuilder setLore(List<String> value);
	
	GUIItemBuilder setLore(String ... value);
	
	GUIItemBuilder addLore(List<String> value);
	
	GUIItemBuilder addLore(String ... value);
	
	GUIItemBuilder removeLore(String ... value);
	
	GUIItemBuilder setDurability(int value);
	
	GUIItemBuilder setUnbreakable(boolean value);
	
	GUIItemBuilder setPotionData(PotionData value);
	
	GUIItemBuilder setPotionData(PotionType type, boolean extended, boolean upgraded);
	
	GUIItemBuilder setOnClick(Consumer<InventoryGUIClickEvent> value);
	
	GUIItemBuilder setItem(ItemStack value);
	
	GUIItemBuilder setItem(GUIItem value);
	
	ItemStack build();
	
	GUIItem buildGUIItem();
	
}
