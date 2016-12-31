package ondre.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ondre.managers.ArenaManager;
import ondre.managers.ChatManager;

public class CreateArena implements CommandExecutor{
	
	ArenaManager am = new ArenaManager();
	ChatManager cm = new ChatManager();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("create")) {
			Player player = (Player) sender;
			
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/survivalgames", "arena.yml"));
			
		if(args.length != 1) {
			player.sendMessage(cm.prefix + "/create <arena>");
			return true;

			}
		
		else if(!config.getStringList("ArenaList").contains(args[0])) {
	
			try {			
				
				am.addAvailableArena(args[0]);
				player.sendMessage(cm.prefix + "You have created an arena: " + args[0]);

			} catch (NumberFormatException ex) {
				player.sendMessage(cm.prefix + "ERROR!");
		
					}
				} else {
					player.sendMessage(cm.prefix + "That arena already exists!");
				}
			}
		}
		return true;
	}
}
