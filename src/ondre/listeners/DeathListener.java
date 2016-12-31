package ondre.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ondre.countdowns.RestartTimer;
import ondre.managers.ChatManager;
import ondre.managers.GameManager;
import ondre.utils.GameState;

public class DeathListener implements Listener {

	GameManager gm = new GameManager();
	ChatManager cm = new ChatManager();
	RestartTimer rt = new RestartTimer();
	
	public static HashMap<Player, Integer> totalKills = new HashMap<Player, Integer>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player dead = event.getEntity().getPlayer();
		Player killer = event.getEntity().getKiller();
		
		GameManager.alive.remove(dead);
		GameManager.dead.add(dead);
		
		totalKills.put(killer, 0);

		dead.setHealth(20);
		dead.setGameMode(GameMode.SPECTATOR);
		event.setDeathMessage(null);

		Bukkit.broadcastMessage(cm.getDeathMessage());

		if (GameState.state == GameState.INGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			Bukkit.broadcastMessage(cm.getLine() + "\n" + cm.getSpace() + "\n" + cm.getWinMessage() + winner + "\n"
					+ cm.getSpace() + "\n" + cm.getLine());

			GameState.state = GameState.RESTARTING;
			rt.startRestartTimer();

		}

		if (GameState.state == GameState.INGAME && GameManager.alive.size() == 0) {
			Bukkit.broadcastMessage(cm.getLine() + "\n" + cm.getSpace() + "\n" + cm.getNoWinnerMessage() + "\n"
					+ cm.getSpace() + "\n" + cm.getLine());
			rt.startRestartTimer();

		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
			dead.getLocation().getWorld().spawnEntity(dead.getLocation(), EntityType.LIGHTNING);

		}
	}
}
