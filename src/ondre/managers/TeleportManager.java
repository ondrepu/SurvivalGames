package ondre.managers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TeleportManager {

	public static File file = new File("plugins/" + "survivalgames", "arena.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

	ArenaManager am = new ArenaManager();
	ChatManager cm = new ChatManager();
	ReflectionManager rm = new ReflectionManager();

	public void mapTeleport() {

		int count = 1;

		Random random = new Random();
		List<String> availableArenas = new ArrayList<String>();
		availableArenas.addAll(config.getStringList("ArenaList"));
		String select = availableArenas.get(random.nextInt(availableArenas.size()));

		for (Player player : Bukkit.getOnlinePlayers()) {
			rm.sendTitle(player, ChatColor.YELLOW + "SurvivalGames", select.toString(), 1, 3, 1);

			for (Player alive : Bukkit.getOnlinePlayers()) {
				Location location = am.getSpawn(select, count);
				alive.teleport(location);
				count++;

			}
		}
	}
}
