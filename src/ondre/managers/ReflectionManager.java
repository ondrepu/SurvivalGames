package ondre.managers;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class ReflectionManager {
	
	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
		IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subtitle + "'}");
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
		connection.sendPacket(titlePacket);
		connection.sendPacket(subtitlePacket);
	}
	
	public void sendActionbar(Player p, String message) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
	
	public void sendTabHF(Player player, String header, String footer){

		  CraftPlayer craftplayer = (CraftPlayer) player;
		  PlayerConnection connection = craftplayer.getHandle().playerConnection;
		  IChatBaseComponent headerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		  IChatBaseComponent footerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		  PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

		  try {
			  
		   Field headerField = packet.getClass().getDeclaredField("a");
		   headerField.setAccessible(true);
		   headerField.set(packet, headerJSON);
		   headerField.setAccessible(!headerField.isAccessible());

		   Field footerField = packet.getClass().getDeclaredField("b");
		   footerField.setAccessible(true);
		   footerField.set(packet, footerJSON);
		   footerField.setAccessible(!footerField.isAccessible());
		   
		  } catch (Exception e) {
			  
		   e.printStackTrace();
		   
		  }

		  connection.sendPacket(packet);
		}
}