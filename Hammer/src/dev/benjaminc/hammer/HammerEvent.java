package dev.benjaminc.hammer;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HammerEvent implements Listener{
	
	public HammerEvent() {
		
	}
	
	private enum Planes {
		XY, YZ, XZ;
	}
		
	@EventHandler
	public void onPlayerHammerEvent(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		GameMode g = p.getGameMode();
		
		ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
		
		if(tool != null && tool.getItemMeta() != null) {
			ItemMeta im = tool.getItemMeta();
			
			if(im.getLore() != null && im.getLore().size() > 0) {
				if(tool.getItemMeta().getLore().contains(ToolType.HAMMER.getLore())
						|| tool.getItemMeta().getLore().contains(ToolType.EXCAVATOR.getLore())) {
					Location l = b.getLocation();
					
					BlockFace face = getBlockFace(p);
					Planes a = null;
					
					if(face != null) {
						if(face.getModX() != 0) {
//							if(plugin.DEBUG) {p.sendMessage("YZ plane");}
							a = Planes.YZ;
						} else if(face.getModY() != 0) {
//							if(plugin.DEBUG) {p.sendMessage("XZ plane");}
							a = Planes.XZ;
						} else if(face.getModZ() != 0) {
//							if(plugin.DEBUG) {p.sendMessage("XY plane");}
							a = Planes.XY;
						}
						for(int i = -1; i <= 1; i++) {
							for(int j = -1; j <= 1; j++) {
								if(!(i == 0 && j == 0)) {
									Location bl = l.clone();
									switch(a) {
									case XY: { bl.add(i, j, 0); } break;
									case XZ: { bl.add(i, 0, j); } break;
									case YZ: { bl.add(0, i, j); } break;
									default: break;
									}
									Block bld = b.getWorld().getBlockAt(bl);
									if(!bld.getDrops(tool).isEmpty()) {
										if(g != GameMode.CREATIVE) {
											bld.breakNaturally();
										} else {
											bld.setType(Material.AIR);
										}
									}
								}
							}
						}
					} else {
						p.sendMessage("Something messed up when trying to break that block. "
								+ "You can try standing somewhere else, or using a different pick. "
								+ "Please do not report this as an error.");
//						p.sendMessage("THIS IS A DEBUG MESSAGE. PLEASE DO NOT REPORT IT AS AN ERROR");
//						p.sendMessage("Face was null");
//						p.sendMessage("b:" + b);
//						p.sendMessage("l:" + l);
//						p.sendMessage("p:" + p);
					}
				}
			}
		}
	}

	/**
	* Gets the BlockFace of the block the player is currently targeting.
	* https://www.spigotmc.org/threads/getting-the-blockface-of-a-targeted-block.319181/
	*
	* @param player the player's whos targeted blocks BlockFace is to be checked.
	* @return the BlockFace of the targeted block, or null if the targeted block is non-occluding.
	*/
	public BlockFace getBlockFace(Player player) {
	    List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
	    if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
	    Block targetBlock = lastTwoTargetBlocks.get(1);
	    Block adjacentBlock = lastTwoTargetBlocks.get(0);
	    return targetBlock.getFace(adjacentBlock);
	}
}
	
