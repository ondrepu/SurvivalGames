package ondre.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ArenaManager {
		
	public static File file = new File("plugins/survivalgames", "arena.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public void addAvailableArena(String name) {
		ArrayList<String> list = (ArrayList<String>) config.getStringList("ArenaList");
		list.add(name);
		config.set("ArenaList", list);
		saveConfig();
	}

	public void setSpawn(String arenaName, int number, Location loc) {
		config.set("arenas." + arenaName + "." + number + ".world", loc.getWorld().getName());
		config.set("arenas." + arenaName + "." + number + ".x", loc.getX());
		config.set("arenas." + arenaName + "." + number + ".y", loc.getY());
		config.set("arenas." + arenaName + "." + number + ".z", loc.getZ());
		config.set("arenas." + arenaName + "." + number + ".yaw", loc.getYaw());
		config.set("arenas." + arenaName + "." + number + ".pitch", loc.getPitch());
		saveConfig();

	}

	public Location getSpawn(String arenaName, int number) {
		Location loc;
		
		try {
			
			World w = Bukkit.getWorld(config.getString("arenas." + arenaName + "." + number + ".world"));
			double x = config.getDouble("arenas." + arenaName + "." + number + ".x");
			double y = config.getDouble("arenas." + arenaName + "." + number + ".y");
			double z = config.getDouble("arenas." + arenaName + "." + number + ".z");

			loc = new Location(w, x, y, z);
			loc.setYaw(config.getInt("arenas." + arenaName + "." + number + ".yaw"));
			loc.setPitch(config.getInt("arenas." + arenaName + "." + number + ".pitch"));

			} catch (Exception ex) {
				loc = new Location(Bukkit.getWorlds().get(0), 0, 100, 0);
			
		}
		return loc;

	}

	public void saveConfig() {

		try {
			
			config.save(file);
			
			} catch (IOException e) {
				e.printStackTrace();
		}
	}
}
