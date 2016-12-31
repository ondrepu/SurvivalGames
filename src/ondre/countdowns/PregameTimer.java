package ondre.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.managers.ChatManager;
import ondre.managers.GameManager;
import ondre.survivalgames.Main;
import ondre.utils.GameState;
import ondre.utils.GameUtils;

public class PregameTimer {
	
	ChatManager cm = new ChatManager();
	GameManager gm = new GameManager();
	GameTimer gt = new GameTimer();
	GameUtils gu = new GameUtils();
	
	public int pregameTime = gm.getpregameTime();
	
	public void startPregameTimer() {
		
		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);
			
			@Override
			public void run() {
				if(pregameTime > 0) {
					
					pregameTime--;
					
					if(pregameTime <= gm.getpregameTime() || pregameTime >= 0) {
							for(Player player : Bukkit.getOnlinePlayers()) {
								player.setExp(pregameTime / (float) gm.getpregameTime());
								player.setLevel(pregameTime);
								gu.updatePreGameBoard();
							}
					}
							
							if(pregameTime <= 10 && pregameTime > 0) {
								for(Player player : Bukkit.getOnlinePlayers()) {
									player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
								}
							}
								
								if(pregameTime == 0) {
									for(Player player : Bukkit.getOnlinePlayers()) {										
										player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 1);
										
										GameState.state = GameState.INGAME; gt.startGameTimer();
										Bukkit.getScheduler().cancelTask(taskID);
									}
								}
							}
						}
					};
				}
			}