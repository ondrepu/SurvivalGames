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

public class SetSpawn implements CommandExecutor {
	
	ArenaManager am = new ArenaManager();
	ChatManager cm = new ChatManager();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
			Player player = (Player) sender;
			
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/survivalgames", "arena.yml"));
			
		if(args.length != 2) {
			player.sendMessage(cm.prefix + "/setspawn <arena> <number>");
			return true;

			}
		
		else if(config.getStringList("ArenaList").contains(args[0])) {

			try {			
				
				int number = Integer.parseInt(args[1]);
				am.setSpawn(args[0], number, player.getLocation());
				player.sendMessage(cm.prefix + "You have added spawnpoint " + number + " for " + args[0].toUpperCase());

			} catch (NumberFormatException ex) {
				
				player.sendMessage(cm.prefix + "You need to identify a number!");

					}
				} else {
					player.sendMessage(cm.prefix + "That arena does not exist!");
				}
			}
		}
		return true;
	}
}
