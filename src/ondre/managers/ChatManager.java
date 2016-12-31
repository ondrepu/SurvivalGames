package ondre.managers;

import org.bukkit.ChatColor;

public class ChatManager {
	
	GameManager gm = new GameManager();
		
	public String prefix = ChatColor.GOLD + "[SurvivalGames] " + ChatColor.GREEN;
	public String joinMessage = prefix + "has joined the fight!";
	public String leaveMessage = prefix + "";
	public String winMessage = ChatColor.YELLOW + "" + ChatColor.BOLD + "Winner: " + ChatColor.WHITE;
	public String deathMessage = prefix + "A tribute has fallen " + ChatColor.YELLOW + "(" + GameManager.alive.size() + " remaining)";
	public String noWinnerMessage = ChatColor.RED + "No one has won the survival games!";	
	public String line = ChatColor.GREEN + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬";	
	public String space = " ";
	public String choosingMapMessage = ChatColor.RED + "Random Map";
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getJoinMessage() {
		return joinMessage;
	}
	
	public String getLeaveMessage() {
		return leaveMessage;
	}
	
	public String getWinMessage() {
		return winMessage;
	}
	
	public String getDeathMessage() {
		return deathMessage;
	}
	
	public String getNoWinnerMessage() {
		return noWinnerMessage;
	}
	
	public String getLine() {
		return line;
	}
	
	public String getSpace() {
		return space;
	}
	
	public String getChoosingMapMessage() {
		return choosingMapMessage;
	}
}
