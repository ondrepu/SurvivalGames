package ondre.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ondre.countdowns.LobbyTimer;
import ondre.managers.GameManager;
import ondre.managers.LobbyManager;
import ondre.managers.ReflectionManager;
import ondre.utils.GameState;
import ondre.utils.GameUtils;

public class JoinListener implements Listener {

	GameManager gm = new GameManager();
	LobbyManager lm = new LobbyManager();
	LobbyTimer lt = new LobbyTimer();
	GameUtils gu = new GameUtils();
	ReflectionManager rm = new ReflectionManager();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
		if(GameState.state == GameState.LOBBY) {
			GameManager.alive.add(player);
			gu.addAlive(player); player.teleport(lm.getLocation("lobby")); 
			gu.updateLobbyBoard();
			rm.sendTabHF(player, ChatColor.YELLOW + "You are playing " + ChatColor.GREEN + "Survival Games", ChatColor.RED + "Eliminate the other tributes!");
			Bukkit.broadcastMessage(ChatColor.RED + player.getDisplayName() + ChatColor.YELLOW + " has joined the fight!");
		}
		
		if(GameState.state == GameState.LOBBY && Bukkit.getOnlinePlayers().size() == gm.minPlayers) {
			lt.startLobbyTimer();
			
		} 
		
		if(GameState.state == GameState.PREGAME || GameState.state == GameState.INGAME || GameState.state == GameState.DEATHMATCH || GameState.state == GameState.RESTARTING) {
			gu.addDead(player); player.teleport(lm.getLocation("lobby"));
			
		}
	}
}
