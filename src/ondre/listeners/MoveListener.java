package ondre.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import ondre.utils.GameState;

public class MoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (GameState.state == GameState.PREGAME) {
			if (((event.getTo().getX() != event.getFrom().getX()) || (event.getTo().getZ() != event.getFrom().getZ()))) {
				event.setTo(event.getFrom());
			}
		}
	}
}
