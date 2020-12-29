package dev.benjaminc.hammer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
	
	private HammerMain plugin;
	
	public HammerEvent(HammerMain p) {
		plugin = p;
	}
	
	private enum Planes {
		XY, YZ, XZ;
	}
	
	private static final Material LOG_LIST[] = {Material.OAK_LOG, Material.BIRCH_LOG, Material.ACACIA_LOG, Material.SPRUCE_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG};
	
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
						p.sendMessage("Face was null");
						p.sendMessage("b:" + b);
						p.sendMessage("l:" + l);
						p.sendMessage("p:" + p);
					}
//				} else if(tool.getItemMeta().getLore().contains(ToolType.WOODAXE.getLore())) {
//					if(isOnList(b.getType(), LOG_LIST) || true) {
//						List<Location> logBlocks = new ArrayList<Location>();
//						searchNearCount = 0;
//						int max = 1;
//						switch(tool.getType()) {
//						case WOODEN_AXE: { max = plugin.woodaxe_woodMax; } break;
//						case STONE_AXE: { max = plugin.woodaxe_stoneMax; } break;
//						case IRON_AXE: { max = plugin.woodaxe_ironMax; } break;
//						case GOLDEN_AXE: { max = plugin.woodaxe_goldMax; } break;
//						case DIAMOND_AXE: { max = plugin.woodaxe_diamondMax; } break;
//						case NETHERITE_AXE: { max = plugin.woodaxe_nethariteMax; } break;
//						default: { max = 0; p.sendMessage("You need to use an axe!");} break;
//						}
//						searchNear(b.getLocation(), b.getType(), logBlocks, SearchDirection.VERT, max);
//						List<Location> alreadySearched = new ArrayList<Location>();
//						List<Location> toSearch = new ArrayList<Location>();
//						toSearch.addAll(logBlocks);
//						while(logBlocks.size() < max && toSearch.size() > 0) {
//							System.out.println("log-block:" + logBlocks.size());
//							sortTopDown(toSearch);
//							searchNear(toSearch, b.getType(), logBlocks, SearchDirection.ALL, max);
//							alreadySearched.addAll(toSearch);
//							toSearch.clear();
//							toSearch.addAll(logBlocks);
//							toSearch.removeAll(alreadySearched);
//						}
//						for(Location l : logBlocks) {
//							if(!l.equals(b.getLocation())) {
//								if(g != GameMode.CREATIVE) {
//									l.getBlock().breakNaturally();
//								} else {
//									l.getBlock().setType(Material.COBBLESTONE);
//								}
//							}
//						}
//					}
				}
			}
		}
	}
	
	private enum SearchDirection {
		HORIZ,
		VERT,
		X,
		Y,
		Z,
		ALL;
	}
	
	private static int searchNearCount;
	
	private void searchNear(Location loc, Material find, List<Location> found, SearchDirection dir, int max) {
		searchNearCount++;
		if(searchNearCount < 1023) {
			if(found.size() < max) {
				int xm = 0;
				int ym = 0;
				int zm = 0;
				switch(dir) {
				case HORIZ: { xm = 1; zm = 1; } break;
				case Y:
				case VERT: { ym = 1; } break;
				case X: { xm = 1; } break;
				case Z: { zm = 1; } break;
				case ALL: { xm = 1; ym = 1; zm = 1; } break;
				}
				for(int dx = -1*xm; dx <= xm; dx++) {
					for(int dy = -1*ym; dy <= ym; dy++) {
						for(int dz = -1*zm; dz <= zm; dz++) {
							Location nl = loc.clone().add(dx, dy, dz);
							if(!found.contains(nl)) {
								if(isOnList(nl.getBlock().getType(), LOG_LIST)) {
									if(found.size() < max) {
										found.add(nl);
										searchNear(nl, find, found, dir, max);
									} else {
										return;
									}
								}
							}
						}
					}
				}
			}
		} else {
			Exception problem = new Exception("HammerEvent#searchNear(loc) was called 1024 times.");
			problem.printStackTrace();
		}
	}
	
	private void searchNear(List<Location> near, Material find, List<Location> found, SearchDirection dir, int max) {
		searchNearCount++;
		if(searchNearCount < 1023) {
			if(found.size() < max) {
				int i = 0;
				while(found.size() < max && i < near.size() && searchNearCount < 1023) {
					System.out.println("Searching ... " + near.get(i));
					searchNear(near.get(i), find, found, dir, max);
					i++;
				}
			}
		} else {
			Exception problem = new Exception("HammerEvent#searchNear(list) was called 1024 times.");
			problem.printStackTrace();
		}
	}
	
	private void sortTopDown(List<Location> toSort) {
		int i = 0;
		while(i < toSort.size() - 1) {
			if(toSort.get(i).getBlockY() < toSort.get(i+1).getBlockY()) {
				toSort.add(i, toSort.remove(i+1));
				System.out.println("Sorting at " + i + " of " + toSort.size());
				i = 0;
			} else {
				i++;
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
	
	@EventHandler
	public void onWoodChop(BlockBreakEvent e) {
//		
//		Player p = e.getPlayer();
//		if(p.getGameMode() == GameMode.SURVIVAL) {
//			ItemStack hand = p.getInventory().getItemInMainHand();
//			if(hand != null && hand.getItemMeta() != null) {
//				List<String> lore = hand.getItemMeta().getLore();
//				if(lore != null && lore.get(0).equals(ToolType.WOODAXE.getLore())) {
//					
//					Block b = e.getBlock();
//					Location l = b.getLocation();
//					while(isOnList(l.clone().add(0, -1, 0).getBlock().getType(), lf)) {
//						l.add(0, -1, 0);
//					}
//					while(isOnList(l.getBlock().getType(), lf)) {
//						l.getBlock().breakNaturally(hand);
//						l.add(0, 1, 0);
//					}
//				}
//			}
//		}
	}
	
	private boolean isOnList(Material m, Material list[]) {
		for(Material t : list) {
			if(m == t) {
				return true;
			}
		}
		return false;
	}

}
