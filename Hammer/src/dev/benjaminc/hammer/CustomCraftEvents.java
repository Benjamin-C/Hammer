package dev.benjaminc.hammer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomCraftEvents implements Listener {
	
	private HammerMain plugin;
	
	private Random rand;
	
	public CustomCraftEvents(HammerMain p) {
		plugin = p;
		rand = new Random();
	}

	@EventHandler
	public void onLuckyHammerCraft(PrepareItemCraftEvent e) { // or CraftItemEvent
//		ItemStack i = e.getInventory().getResult();
//		boolean legal = true;
//		if(i != null && i.getItemMeta() != null && i.getItemMeta().getLore() != null && i.getItemMeta().getLore().get(0).equals(ToolType.LUCKYHAMMER.getLore())) {
//			ItemStack[] ing = e.getInventory().getMatrix();
//			ItemStack r = e.getInventory().getResult();
//			if(r != null) {
//				for(ItemStack is : ing) {
//					if(is != null) {
//						if(is.getType() == r.getType()) {
//							List<String> lore = is.getItemMeta().getLore();
//							if(lore == null || !lore.get(0).equals(ToolType.HAMMER.getLore())) {
//								legal = false;
//							}
//						} else if(is.getType() == Material.STICK) {
//							List<String> lore = is.getItemMeta().getLore();
//							if(lore == null || !lore.get(0).equals(ToolType.LUCKYSTICK.getLore())) {
//								legal = false;
//							}
//						}
//					}
//				}
//			}
//			if(!legal) {
//				e.getInventory().setResult(new ItemStack(Material.AIR));
//			}
//		}
	}
}
