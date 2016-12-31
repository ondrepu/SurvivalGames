package ondre.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ondre.commands.CreateArena;
import ondre.commands.SetLobby;
import ondre.commands.SetSpawn;
import ondre.listeners.BlockListener;
import ondre.listeners.ChestListener;
import ondre.listeners.CompassListener;
import ondre.listeners.DamageListener;
import ondre.listeners.DeathListener;
import ondre.listeners.HungerListener;
import ondre.listeners.InventoryListener;
import ondre.listeners.JoinListener;
import ondre.listeners.MoveListener;
import ondre.listeners.QuitListener;
import ondre.utils.GameState;

public class Main extends JavaPlugin {
	
	public static Main main;
	
	public void onEnable() {
		main = this;
		
		GameState.state = GameState.LOBBY;
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new HungerListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new MoveListener(), this);
		pm.registerEvents(new ChestListener(), this);
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new CompassListener(), this);

		getCommand("setlobby").setExecutor(new SetLobby());
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("create").setExecutor(new CreateArena());
	}
}
