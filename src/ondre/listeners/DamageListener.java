package ondre.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import ondre.managers.GameManager;
import ondre.utils.GameState;

public class DamageListener implements Listener {
	
	GameManager gm = new GameManager();
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {		
		if(GameState.state == GameState.LOBBY || GameState.state == GameState.PREGAME || GameState.state == GameState.RESTARTING) {
		event.setCancelled(true);
		
		} else {
			
			event.setCancelled( false);
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if(GameState.state == GameState.LOBBY || GameState.state == GameState.PREGAME || GameState.state == GameState.RESTARTING) {
		event.setCancelled(true);
		
		} else {
			
			event.setCancelled(false);
		}
	}
}
