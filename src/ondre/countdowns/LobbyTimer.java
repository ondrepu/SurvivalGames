package ondre.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.managers.ArenaManager;
import ondre.managers.ChatManager;
import ondre.managers.GameManager;
import ondre.managers.TeleportManager;
import ondre.survivalgames.Main;
import ondre.utils.GameState;
import ondre.utils.GameUtils;

public class LobbyTimer {

	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	PregameTimer pt = new PregameTimer();
	ArenaManager am = new ArenaManager();
	GameUtils gu = new GameUtils();
	TeleportManager tm = new TeleportManager();

	public int lobbyTime = gm.getLobbyTime();

	public void startLobbyTimer() {
		Bukkit.broadcastMessage(cm.getPrefix() + "The game will begin soon!");

		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (lobbyTime > 0) {

					lobbyTime--;

					if (lobbyTime <= lobbyTime || lobbyTime >= 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.setExp(lobbyTime / (float) gm.getLobbyTime());
							player.setLevel(lobbyTime);
						}
					}

					if (lobbyTime <= 5 && lobbyTime > 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							Location location = player.getLocation();
							player.playSound(location, Sound.CLICK, 1, 1);
						}
					}

					if (lobbyTime == 0) {
						GameState.state = GameState.PREGAME; tm.mapTeleport(); pt.startPregameTimer();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}
		};
	}
}
