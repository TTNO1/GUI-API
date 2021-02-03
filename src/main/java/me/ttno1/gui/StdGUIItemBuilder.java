package me.ttno1.gui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Inject;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import com.google.common.collect.Multimap;

import me.ttno1.gui.events.InventoryGUIClickEvent;

public class StdGUIItemBuilder implements GUIItemBuilder {

	private Material type;
	
	private int amount;
	
	private Map<Enchantment, Integer> enchants;
	
	private Collection<PotionEffect> potionEffects;
	
	private Multimap<Attribute, AttributeModifier> attributes;
	
	private Collection<ItemFlag> flags;
	
	private List<String> lore;
	
	private String displayName;

	private String localName;
	
	private Color color;
	
	private int modelData;
	
	private int durability;
	
	private boolean unbreakable;
	
	private PotionData potionData;
	
	private Consumer<InventoryGUIClickEvent> onClick;
	
	private GUIItemFactory guiItemFactory;
	
	@Inject
	public StdGUIItemBuilder(Map<Enchantment, Integer> enchants, Collection<PotionEffect> potionEffects, Multimap<Attribute, AttributeModifier> attributes, Collection<ItemFlag> flags, List<String> lore, GUIItemFactory guiItemFactory) {
		
		this.enchants = enchants;
		
		this.potionEffects = potionEffects;
		
		this.attributes = attributes;
		
		this.flags = flags;
		
		this.lore = lore;
		
		this.guiItemFactory = guiItemFactory;
		
		amount = 1;
		
	}
	
	@Override
	public GUIItemBuilder setType(Material value) {
		
		type = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setAmount(int value) {
		
		amount = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder addEnchantment(Enchantment enchant, int level) {
		
		enchants.put(enchant, level);
		
		return this;
		
	}

	@Override
	public GUIItemBuilder addEnchantments(Map<Enchantment, Integer> enchants) {
		
		enchants.putAll(enchants);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setEnchantments(Map<Enchantment, Integer> enchants) {
		
		enchants.clear();
		
		addEnchantments(enchants);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder removeEnchantment(Enchantment enchant) {
		
		enchants.remove(enchant);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addPotionEffects(PotionEffect... effects) {
		
		addPotionEffects(Arrays.asList(effects));
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addPotionEffects(Collection<PotionEffect> effects) {
		
		potionEffects.addAll(effects);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setPotionEffects(Collection<PotionEffect> effects) {
		
		potionEffects.clear();
		
		addPotionEffects(effects);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder removePotionEffects(PotionEffect ... effects) {
		
		potionEffects.removeAll(Arrays.asList(effects));
		
		return this;
		
	}

	@Override
	public GUIItemBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
		
		attributes.put(attribute, modifier);
		
		return this;
		
	}

	@Override
	public GUIItemBuilder addAttributeModifier(Attribute attribute, String name, double amount) {
		
		addAttributeModifier(attribute, new AttributeModifier(name, amount, Operation.ADD_NUMBER));
		
		return this;
		
	}

	@Override
	public GUIItemBuilder addAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes) {
		
		this.attributes.putAll(attributes);
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setAttributeModifiers(Multimap<Attribute, AttributeModifier> attributes) {
		
		this.attributes.clear();
		
		addAttributeModifiers(attributes);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder removeAttributeModifier(Attribute attribute, AttributeModifier attributeModifier) {
		
		attributes.remove(attribute, attributeModifier);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addItemFlags(ItemFlag... flags) {
		
		addItemFlags(Arrays.asList(flags));
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addItemFlags(Collection<ItemFlag> flags) {
		
		this.flags.addAll(flags);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setItemFlags(Collection<ItemFlag> flags) {
		
		this.flags.clear();
		
		addItemFlags(flags);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder removeItemFlags(ItemFlag ... flags) {
		
		this.flags.removeAll(Arrays.asList(flags));
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setColor(Color value) {
		
		color = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setCustomModelData(int value) {
		
		modelData = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setDisplayName(String value) {
		
		displayName = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setLocalizedName(String value) {
		
		localName = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setLore(List<String> value) {
		
		lore.clear();
		
		lore.addAll(value);
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setLore(String... value) {
		
		setLore(Arrays.asList(value));
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addLore(List<String> value) {
		
		this.lore.addAll(value);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder addLore(String ... value) {
		
		addLore(Arrays.asList(value));
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder removeLore(String ... value) {
		
		this.lore.removeAll(Arrays.asList(value));
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setDurability(int value) {
		
		durability = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setUnbreakable(boolean value) {
		
		unbreakable = value;
		
		return this;
		
	}

	@Override
	public GUIItemBuilder setPotionData(PotionData value) {
		
		potionData = value;
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setPotionData(PotionType type, boolean extended, boolean upgraded) {
		
		potionData = new PotionData(type, extended, upgraded);
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setOnClick(Consumer<InventoryGUIClickEvent> value) {
		
		onClick = value;
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setItem(ItemStack item) {
		
		ItemMeta meta = item.getItemMeta();
		
		setType(item.getType());
		
		setAmount(item.getAmount());
		
		setEnchantments(meta.getEnchants());
		
		if(meta instanceof PotionMeta) {
			
			setPotionEffects(((PotionMeta) meta).getCustomEffects());
			
			setPotionData(((PotionMeta) meta).getBasePotionData());
			
			setColor(((PotionMeta) meta).getColor());
			
		} else if(meta instanceof LeatherArmorMeta) {
			
			setColor(((LeatherArmorMeta) meta).getColor());
			
		}
		
		setAttributeModifiers(meta.getAttributeModifiers());
		
		setItemFlags(meta.getItemFlags());
		
		setLore(meta.getLore());
		
		setDisplayName(meta.getDisplayName());
		
		setLocalizedName(meta.getLocalizedName());
		
		setCustomModelData(meta.getCustomModelData());
		
		if(meta instanceof Damageable) {
			
			setDurability(((Damageable) meta).getDamage());
			
		}
		
		setUnbreakable(meta.isUnbreakable());
		
		return this;
		
	}
	
	@Override
	public GUIItemBuilder setItem(GUIItem item) {
		
		setItem(item.getGUIItem());
		
		setOnClick(item.getOnGUIClick());
		
		return this;
		
	}

	@Override
	public ItemStack build() {
		
		ItemStack item = new ItemStack(type, amount);
		
		ItemMeta meta = item.getItemMeta();
		
		if(meta instanceof PotionMeta) {
			
			for(PotionEffect effect : potionEffects) {
				
				((PotionMeta) meta).addCustomEffect(effect, false);
				
			}
			
			((PotionMeta) meta).setBasePotionData(potionData);
			
			((PotionMeta) meta).setColor(color);
			
		} else if(meta instanceof LeatherArmorMeta) {
			
			((LeatherArmorMeta) meta).setColor(color);
			
		}
		
		if(meta instanceof EnchantmentStorageMeta) {
			
			for(Enchantment enchant : enchants.keySet()) {
				
				((EnchantmentStorageMeta) meta).addStoredEnchant(enchant, enchants.get(enchant), true);
				
			}
			
		} else {
			
			item.addUnsafeEnchantments(enchants);
			
		}
		
		meta.setAttributeModifiers(attributes);
		
		meta.addItemFlags((ItemFlag[]) flags.toArray());
		
		meta.setLore(lore);
		
		meta.setDisplayName(displayName);
		
		meta.setLocalizedName(localName);
		
		meta.setCustomModelData(modelData);
		
		if(meta instanceof Damageable)  {
			
			((Damageable) meta).setDamage(durability);
			
		}
		
		meta.setUnbreakable(unbreakable);
		
		item.setItemMeta(meta);
		
		return item;
		
	}

	@Override
	public GUIItem buildGUIItem() {
		
		return guiItemFactory.create(build(), onClick);
		
	}

}
