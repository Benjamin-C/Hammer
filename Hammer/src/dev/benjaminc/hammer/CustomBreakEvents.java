package dev.benjaminc.hammer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomBreakEvents implements Listener {
//	
//	private SBMain plugin;
//	
//	private Map<String, List<Randomizable<Material>>> blocks;
//
//	private Random rand;
//	
//	public CustomBreakEvents(SBMain p) {
//		plugin = p;
//		rand = new Random();
//		blocks = plugin.getBlockDrops();
//	}
//	
//	private void randomDrop(String key, BlockBreakEvent e) {
//		if(blocks.containsKey(key)) {
//			Material toMake;
//			List<Randomizable<Material>> list = blocks.get(key);
//			double rn = rand.nextDouble();
//			int i = 0;
//			while(rn > list.get(i).getBound() && i < list.size()) {
//				i++;
//			}
//			if(i < list.size()) {
//				toMake =  list.get(i).getValue();
//				if(toMake != null) {
//					ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
////					e.getPlayer().sendMessage(toMake + "");
//					Block b = e.getBlock();
//					if(toMake != Material.AIR) {
//						ItemStack toDrop = new ItemStack(toMake);
//						toDrop.setAmount(1);
//			            Location loc = b.getLocation().clone().add(0, 0, 0);
//			            b.getWorld().dropItemNaturally(loc, toDrop);
//					}
//		            b.setType(Material.AIR);
//		            
//		            Damageable d = (Damageable) hand.getItemMeta();
//		            d.setDamage(d.getDamage() -1 );
//		            hand.setItemMeta((ItemMeta) d);
//				} else {
//					e.getPlayer().sendMessage("ToMake was null");
//				}
//			} else {
//				System.out.println("i was too big");
//			}
//		} else {
//			e.getPlayer().sendMessage("You arn't supposed to break like that");
//		}
//	}
//	
//	private ToolType getTool(String lore) {
//		for(ToolType t : ToolType.values()) {
//			if(t.getLore().equals(lore)) {
//				return t;
//			}
//		}
//		return null;
//	}
//	@EventHandler
//	public void onBlockBreak(BlockBreakEvent e) {
//		Player p = e.getPlayer();
//		if(p.getGameMode() == GameMode.SURVIVAL) {
//			ItemStack hand = p.getInventory().getItemInMainHand();
//			if(hand != null && hand.getItemMeta() != null) {
//				List<String> lore = hand.getItemMeta().getLore();
//				if(lore != null) {
//					ToolType tool = getTool(lore.get(0));
//					if(tool != null) {
//						Block b = e.getBlock();
//						e.getPlayer().sendMessage(tool + "_" + b.getType());
//						randomDrop(tool + "_" + b.getType(), e);
//						e.setCancelled(true);
//					}
//				}
//			}
//		}
//	}
//	

	
	
}
