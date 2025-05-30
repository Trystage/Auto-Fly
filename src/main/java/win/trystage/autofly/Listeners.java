package win.trystage.autofly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.List;

public class Listeners implements Listener {
    private String permission;
    private String message;
    private boolean byDefault;
    private List<String> worlds;

    Listeners(String permission, String message, boolean byDefault, List<String> worlds) {
        this.permission = permission;
        this.message = message;
        this.byDefault = byDefault;
        this.worlds = worlds;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(worlds.contains(event.getPlayer().getWorld().getName())){
            this.setFly(event.getPlayer());
        }
    }


    @EventHandler
    public void onWorldJoin(PlayerChangedWorldEvent event) {
        if(worlds.contains(event.getPlayer().getWorld().getName())){
            this.setFly(event.getPlayer());
        }
        else{
            event.getPlayer().setAllowFlight(false);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        this.setFly(event.getPlayer());
    }

    private void setFly(Player player) {
        if (player.hasPermission(this.permission)) {
            player.setAllowFlight(true);
            if (this.byDefault) {
                player.setFlying(true);
            }

            if (!this.message.isEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.message));
            }
        }
    }
}
