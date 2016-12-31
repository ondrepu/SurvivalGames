package ondre.managers;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	
	public ItemStack create(Material mat, int amount) {
		return new ItemStack(mat, amount);
		
	}
	
	public ItemStack createCustom(Material mat, int amount, short id, String display) {
		ItemStack it = new ItemStack(mat, amount, id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(display);
		it.setItemMeta(meta);
		return it;
	}

	public int randomInt(int min, int max) {
		Random random = new Random();
		int i = random.nextInt((max - min) + 1) + min;
		return i;	
		
		}	

}
