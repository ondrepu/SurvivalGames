package ondre.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	ItemStack clicked = event.getCurrentItem();
	Inventory inventory = event.getInventory(); 
		if (inventory.getName().equals(ChatColor.DARK_GRAY + "Kit Selector")) {
			
			if (clicked.getType() == Material.BOW) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Katniss" + ChatColor.WHITE + " kit!");
					
			}
			
			if (clicked.getType() == Material.POTION) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Peeta" + ChatColor.WHITE + " kit!");
				
			}
			
			if (clicked.getType() == Material.FISHING_ROD) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Finnick" + ChatColor.WHITE + " kit!");
				
			}
			
			if (clicked.getType() == Material.APPLE) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Rue" + ChatColor.WHITE + " kit!");
				
			}
			
			if (clicked.getType() == Material.STONE_AXE) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Johanna" + ChatColor.WHITE + " kit!");
				
			}
			
			if (clicked.getType() == Material.REDSTONE) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Beetee" + ChatColor.WHITE + " kit!");
				
			}
			
			if (clicked.getType() == Material.BROWN_MUSHROOM) {
				event.setCancelled(true);
				player.closeInventory();
				player.sendMessage("You have selected the " + ChatColor.GREEN + "Foxface" + ChatColor.WHITE + " kit!");
				
			}
		}
	}	
}
