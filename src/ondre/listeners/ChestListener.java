package ondre.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ondre.managers.GameManager;
import ondre.managers.ItemManager;
import ondre.utils.GameState;

public class ChestListener implements Listener {
	
	GameManager gm = new GameManager();
	ItemManager im = new ItemManager();
	
	private HashMap<Location, Inventory> chests;
	private ArrayList<ItemStack> loot;
	
	public ChestListener() {
		chests = new HashMap<Location, Inventory>();
		loot = new ArrayList<ItemStack>();
	}

	@EventHandler
	public void onChestOpen(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType() == Material.CHEST) {
				event.setCancelled(true);

				if (GameManager.alive.contains(player) && GameState.state == GameState.INGAME) {
					if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if (!chests.containsKey(event.getClickedBlock().getLocation())) {

							registerLoot();
							Inventory inv = Bukkit.createInventory(null, 9 * 3, "Crate");
							for (int i = 0; i < im.randomInt(3, 6); i++) {
								inv.setItem(im.randomInt(0, inv.getSize() - 1),
										loot.get(im.randomInt(0, loot.size() - 1)));
							}
							chests.put(event.getClickedBlock().getLocation(), inv);
						}
						event.getPlayer().openInventory(chests.get(event.getClickedBlock().getLocation()));
					}
				}
			}
		}
	}

	public void registerLoot() {
		loot.clear();
		
		//FOOD
		loot.add(im.create(Material.COOKED_FISH, 2));
		loot.add(im.create(Material.COOKED_CHICKEN, 2));
		loot.add(im.create(Material.APPLE, 5));
		loot.add(im.create(Material.GOLDEN_APPLE, 1));
		loot.add(im.create(Material.STONE_SWORD, 1));
		loot.add(im.create(Material.COOKED_FISH, 7));
		loot.add(im.create(Material.COOKED_MUTTON, 5));
		loot.add(im.create(Material.COOKED_RABBIT, 3));
		
		//ARMOUR
		loot.add(im.create(Material.IRON_CHESTPLATE, 1));
		loot.add(im.create(Material.IRON_LEGGINGS, 1));
		loot.add(im.create(Material.IRON_HELMET, 1));
		loot.add(im.create(Material.IRON_BOOTS, 1));
		
		loot.add(im.create(Material.LEATHER_HELMET, 1));
		loot.add(im.create(Material.LEATHER_LEGGINGS, 1));
		loot.add(im.create(Material.LEATHER_CHESTPLATE, 1));
		loot.add(im.create(Material.LEATHER_BOOTS, 1));
		
		loot.add(im.create(Material.GOLD_CHESTPLATE, 1));
		loot.add(im.create(Material.GOLD_HELMET, 1));
		loot.add(im.create(Material.GOLD_LEGGINGS, 1));
		loot.add(im.create(Material.GOLD_BOOTS, 1));
		
		loot.add(im.create(Material.CHAINMAIL_BOOTS, 1));
		loot.add(im.create(Material.CHAINMAIL_HELMET, 1));
		loot.add(im.create(Material.CHAINMAIL_LEGGINGS, 1));
		loot.add(im.create(Material.CHAINMAIL_CHESTPLATE, 1));

		//WEAPONS
		loot.add(im.createCustom(Material.WOOD_SWORD, 1, (short) 0, "Sword"));
		loot.add(im.createCustom(Material.STONE_SWORD, 1, (short) 0, "Sword"));
		loot.add(im.createCustom(Material.BOW, 1, (short) 0, "Bow"));
		loot.add(im.create(Material.STONE_AXE, 1));
		loot.add(im.create(Material.ARROW, 5));
		
		//MISC
		loot.add(im.create(Material.IRON_INGOT, 2));
		loot.add(im.create(Material.GOLD_INGOT, 2));
		loot.add(im.create(Material.STICK, 1));
	}
}
