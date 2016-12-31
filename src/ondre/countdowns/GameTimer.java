package ondre.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.managers.ChatManager;
import ondre.managers.GameManager;
import ondre.survivalgames.Main;
import ondre.utils.GameState;
import ondre.utils.GameUtils;

public class GameTimer {

	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	RestartTimer rt = new RestartTimer();
	GameUtils gu = new GameUtils();

	public int gameTime = gm.getGameTime();

	public void startGameTimer() {

		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (gameTime > 0) {

					gameTime--;

					if (gameTime <= gameTime && gameTime > 0) {
							gu.updateGameBoard();
						}

					if (gameTime <= 10 && gameTime >= 0) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
						}
					}

					if (gameTime == 0) {
						GameState.state = GameState.RESTARTING; rt.startRestartTimer();
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}
		};
	}
}