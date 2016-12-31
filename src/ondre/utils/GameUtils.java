package ondre.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import ondre.managers.GameManager;
import ondre.managers.ScoreboardManager;

public class GameUtils {
		
	public void addAlive(Player player) {
		player.setHealth(20);
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		player.setExp(0);
		player.setTotalExperience(0);
		player.setFoodLevel(20);
		player.setMaxHealth(20);		
	}
	
	public void addDead(Player player) {
		GameManager.alive.remove(player);
		GameManager.dead.add(player);
		player.setGameMode(GameMode.SPECTATOR);
	}

	public void updateLobbyBoard() {
		for (Player player : Bukkit.getOnlinePlayers()) {							
			ScoreboardManager sm = new ScoreboardManager(ChatColor.YELLOW + "Survival Games");
			sm.blankLine();
			sm.add(ChatColor.GRAY + "" + "Map:");
			sm.add("Random");
			sm.blankLine();
			sm.build();
			sm.send(player);
		}
	}
	
	public void updateGameBoard() {
		for (Player player : Bukkit.getOnlinePlayers()) {							
			ScoreboardManager sm = new ScoreboardManager(ChatColor.YELLOW + "Survival Games");
			sm.blankLine();
			sm.add(ChatColor.GRAY + "" + "Tributes:");
			sm.add("" + GameManager.alive.size());
			sm.blankLine();
			sm.add(ChatColor.GRAY + "District:");
			sm.add("12");
			sm.blankLine();
			sm.build();
			sm.send(player);
		}
	}
	
	public void updatePreGameBoard() {
		for (Player player : Bukkit.getOnlinePlayers()) {							
			ScoreboardManager sm = new ScoreboardManager(ChatColor.YELLOW + "Starting Soon");
			sm.blankLine();
			sm.build();
			sm.send(player);
		}
	}
}