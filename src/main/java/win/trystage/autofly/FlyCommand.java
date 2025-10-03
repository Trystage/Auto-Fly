package win.trystage.autofly;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("group.vip")) {
            player.sendMessage("§fUnknown command. Type \"/help\" for help.");
            return true;
        }

        boolean enable = !player.getAllowFlight();
        player.setAllowFlight(enable);
        player.setFlying(enable);

        if (enable) {
            player.sendMessage("[lang]hub.fly.on[/lang]");
        } else {
            player.sendMessage("[lang]hub.fly.off[/lang]");
        }

        return true;
    }
}