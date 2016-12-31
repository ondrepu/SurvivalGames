package ondre.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import ondre.utils.GameState;

public class HungerListener implements Listener {
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		if(GameState.state == GameState.LOBBY || GameState.state == GameState.PREGAME || GameState.state == GameState.RESTARTING) {
			event.setCancelled(true);
			
		} else {
			
			event.setCancelled(false);
		}
	}	
}
