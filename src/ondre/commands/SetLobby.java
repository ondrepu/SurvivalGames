package ondre.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ondre.managers.ChatManager;
import ondre.managers.LobbyManager;

public class SetLobby implements CommandExecutor {
	
	ChatManager cm = new ChatManager();
	LobbyManager lm = new LobbyManager();


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
		if(args.length != 0) {
			player.sendMessage(cm.prefix + "Wrong usage: /setlobby");
			return true;
			
			}
		
		lm.setLocation("lobby", player.getLocation());
		player.sendMessage(cm.prefix + "You have added a lobby spawn!");
		
		}
		
		return true;
	}
}