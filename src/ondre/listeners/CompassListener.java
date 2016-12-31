package ondre.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import ondre.managers.GameManager;

public class CompassListener implements Listener {

	@EventHandler
	public void OpenPlayerSpec(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((((e.getAction() == Action.RIGHT_CLICK_AIR ? 1 : 0)
				| (e.getAction() == Action.RIGHT_CLICK_BLOCK ? 1 : 0)) != 0) && (e.getMaterial() == Material.WATCH)) {
			if (!GameManager.alive.contains(p)) {
				Inventory inv = Bukkit.getServer().createInventory(null, 54, ("Spectate a Player"));
				for (Player players : Bukkit.getOnlinePlayers()) {
					if ((players != p) && (GameManager.alive.contains(players))) {
						ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
						SkullMeta meta = (SkullMeta) item.getItemMeta();

						meta.setOwner(players.getName());
						meta.setDisplayName(players.getDisplayName());

						item.setItemMeta(meta);
						inv.addItem(new ItemStack[] { item });
					}
				}
				p.openInventory(inv);
			} else {
				p.sendMessage("Can not open menu!");
			}
		}
	}

	@EventHandler
	public void OpenPlayerSpecClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals(("Spectate a Player"))) {
			e.setCancelled(true);
			if ((e.getSlot() == e.getRawSlot()) && (e.getCurrentItem() != null)) {
				String playername = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
				Player alive = Bukkit.getPlayer(playername);
				if (alive != null) {
					p.teleport(alive.getLocation());
					p.setAllowFlight(true);
					p.setFlying(true);
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.hidePlayer(p);
					}
				}
			}
		}
	}
}