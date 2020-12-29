package dev.benjaminc.hammer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class HammerMain extends JavaPlugin {
	
	public List<String> worlds;
	
	private FileConfiguration cfg = this.getConfig();
	
	protected boolean DEBUG = true;
	
	protected int woodaxe_woodMax = 16;
	protected int woodaxe_stoneMax = 24;
	protected int woodaxe_ironMax = 32;
	protected int woodaxe_goldMax = 32;
	protected int woodaxe_diamondMax = 48; 
	protected int woodaxe_nethariteMax = 64;
	
	/**
	 * Fired with the plugin is enabled
	 */
    @Override
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(new HammerEvent(this), this);
    	Bukkit.broadcastMessage("Hammers loaded");
    	Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> Bukkit.broadcastMessage("Hammers loadead a moment ago. Bang bang."), 1);
    	addCraftings();
    	
    	cfg = getConfig();
    	saveDefaultConfig();
    	
    	woodaxe_woodMax = cfg.getInt(Keys.CONFIG_WOODAXE_WOOD_MAX);
    	woodaxe_stoneMax  = cfg.getInt(Keys.CONFIG_WOODAXE_STONE_MAX);
    	woodaxe_ironMax  = cfg.getInt(Keys.CONFIG_WOODAXE_IRON_MAX);
    	woodaxe_goldMax  = cfg.getInt(Keys.CONFIG_WOODAXE_GOLD_MAX);
    	woodaxe_diamondMax  = cfg.getInt(Keys.CONFIG_WOODAXE_DIAMOND_MAX);
    	woodaxe_nethariteMax = cfg.getInt(Keys.CONFIG_WOODAXE_NETHERITE_MAX);

//    	getCommand(Keys.COMMAND_WS).setExecutor(new WSCommand(this));
//    	getCommand(Keys.COMMAND_WS).setTabCompleter(new WSCommandTabComplete(this));
    }
    
    /*
     *  Fired when plugin is disabled
     */
    @Override
    public void onDisable() {
    	Bukkit.broadcastMessage("Hammers unloaded");
//    	cfg.set(Keys.CONFIG_WORLDS, worlds);
//    	saveConfig();
    }
    
    private static final String TOOL_MATERIALS[] = {"WOODEN", "STONE", "GOLDEN", "IRON", "DIAMOND", "NETHERITE"};
    
    private RecipeSetup toolRecipeSetup = new RecipeSetup() {
		@Override public void craft(ShapedRecipe recipe, Material mat) {
			recipe.setIngredient('p', mat);
		}
	};
	
    public void addCraftings() {
    	for(String s : TOOL_MATERIALS) {
    		addHammerCrafting(Material.valueOf(s + "_PICKAXE"), ToolType.HAMMER);
    		addHammerCrafting(Material.valueOf(s + "_SHOVEL"), ToolType.EXCAVATOR);
    		addHammerCrafting(Material.valueOf(s + "_AXE"), ToolType.WOODAXE);
    	}
    }
    
    public void addHammerCrafting(Material mat, ToolType type) {
    	String matname = mat.toString();
    	matname = matname.substring(0, matname.indexOf("_")).toLowerCase();
    	String name = matname.substring(0, 1).toUpperCase() + matname.substring(1);
    	name += " " + type.getSuffix();
    	String key = type.getSuffix().toLowerCase() + "_" + matname;
    	addRecipe(name, type.getLore(), null, mat, mat, key, "ppp", "ppp", "ppp", toolRecipeSetup);
    }
    
    private void addRecipe(String name, String lore, Map<Enchantment, Integer> enchants,
    		Material dest, Material ingred, String key, String row0, String row1, String row2, RecipeSetup setup) {
    	ItemStack item = new ItemStack(dest);
    	ItemMeta meta = item.getItemMeta();
    	List<String> im = meta.getLore();
    	if(im == null) {
    		im = new ArrayList<String>();
    	}
    	im.add(0, lore);
    	meta.setLore(im);
    	if(enchants != null) {
	    	for(Enchantment e : enchants.keySet()) {
	    		meta.addEnchant(e, enchants.get(e), false);
	    	}
    	}
    	meta.setDisplayName("\u00A7r" + name);
    	item.setItemMeta(meta);
    	NamespacedKey nskey = new NamespacedKey(this, key);
    	ShapedRecipe recipe = new ShapedRecipe(nskey, item);
    	recipe.shape(row0, row1, row2);
    	setup.craft(recipe, ingred);
        Bukkit.addRecipe(recipe);
    }
    
    private interface RecipeSetup {
    	public void craft(ShapedRecipe recipe, Material mat);
    }
}
