package dev.benjaminc.hammer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WSCommandTabComplete implements TabCompleter {
	
	HammerMain plugin;
	
	public WSCommandTabComplete(HammerMain plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String lavel, String[] args) {
//		List<String> options = new ArrayList<String>();
//		List<String> possible = new ArrayList<String>();
//		
//		boolean isAdmin = sender.hasPermission(Keys.PERMISSION_CONTROL);
//		boolean isChanger = sender.hasPermission(Keys.PERMISSION_CHANGE);
//		
//		if(isChanger) {
//			switch(args.length) {
//			case 1: {
//				possible.add(Keys.COMMAND_WS_LIST); // add join leave list remove
//				possible.add(Keys.COMMAND_WS_SELECT);
//				if(isAdmin) { 
//					possible.add(Keys.COMMAND_WS_ADD);
//					possible.add(Keys.COMMAND_WS_RM);
//				}
//				options = getPossibleCompletes(possible, args[0]);
//			} break;
//			case 2: {
//				switch(args[0]) {
//				case Keys.COMMAND_WS_LIST: break;
//				case Keys.COMMAND_WS_SELECT: {
//					possible.addAll(plugin.worlds);
//				} break;
//				case Keys.COMMAND_WS_RM: {
//					if(isAdmin) {
//						possible.addAll(plugin.worlds);
//					}
//				}
//				}
//				options = getPossibleCompletes(possible, args[1]);
//			} break;
//			}
//		}
//		
//		return options;
		return null;
	}

	private List<String> getPossibleCompletes(List<String> possible, String part) {
		List<String> options = new ArrayList<String>();
		for(String s : possible) {
			if(part.equalsIgnoreCase(s.substring(0, Math.min(part.length(), s.length())))) {
				options.add(s);
			}
		}
		return options;
	}
}
