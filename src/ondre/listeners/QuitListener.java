package ondre.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ondre.countdowns.RestartTimer;
import ondre.managers.ChatManager;
import ondre.managers.GameManager;
import ondre.survivalgames.Main;
import ondre.utils.GameState;
import ondre.utils.GameUtils;

public class QuitListener implements Listener {
	
	GameManager gm = new GameManager();
	RestartTimer rt = new RestartTimer();
	ChatManager cm = new ChatManager();
	GameUtils gu = new GameUtils();
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		Location location = player.getLocation();
		Inventory inventory = player.getInventory();		
		event.setQuitMessage(null);
		
		GameManager.alive.remove(player);
		
		if(GameState.state == GameState.INGAME) {
			
			for(ItemStack items : inventory.getContents()) {
				if(items != null) {
					location.getWorld().dropItemNaturally(location, items.clone());
				}
				inventory.clear();
			}
		}
		
		if(GameState.state == GameState.LOBBY || GameState.state == GameState.PREGAME) {
			gu.updateLobbyBoard();
		}
		
		if(GameState.state == GameState.LOBBY && GameManager.alive.size() < gm.minPlayers) {
			Bukkit.getScheduler().cancelTasks(Main.main);
			
		}
		
		if(GameState.state == GameState.PREGAME && GameManager.alive.size() == 0 || GameState.state == GameState.INGAME && GameManager.alive.size() == 0) {
			Bukkit.getScheduler().cancelTasks(Main.main); rt.startRestartTimer();
			
		}
		
		if(GameState.state == GameState.PREGAME && GameManager.alive.size() == 1 || GameState.state == GameState.INGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			
			Bukkit.broadcastMessage(cm.getLine() + "\n" + cm.getSpace() + "\n" + cm.getWinMessage() + winner + "\n" + cm.getSpace() + "\n" + cm.getLine());
			GameState.state = GameState.RESTARTING; rt.startRestartTimer();
		}
	}
}
