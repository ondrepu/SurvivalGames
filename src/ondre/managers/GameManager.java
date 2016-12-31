package ondre.managers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class GameManager {
	
	public int minPlayers = 1;
	public int maxPlayers = 100;
	
	public int lobbyTime = 5;
	public int pregameTime = 16;
	public int gameTime = 601;
	public int deathmatchTime = 901;
	public int endTime = 11;
	
	public static ArrayList<Player> alive = new ArrayList<Player>();
	public static ArrayList<Player> dead = new ArrayList<Player>();
	
	public int getMinPlayers() {
		return minPlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public int getLobbyTime() {
		return lobbyTime;	
	}
	
	public int getpregameTime() {
		return pregameTime;
	}
	
	public int getGameTime() {
		return gameTime;
	}
	
	public int getDeathmatchTime() {
		return deathmatchTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
}
