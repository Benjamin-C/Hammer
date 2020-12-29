package dev.benjaminc.hammer;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WSCommand implements CommandExecutor {

	HammerMain plugin;
	
	public WSCommand(HammerMain p) {
		plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}
}
