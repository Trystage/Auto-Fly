package win.trystage.autofly;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class AutoFly extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        new File(this.getDataFolder(), "config.yml");
        String permission = this.getConfig().getString("permission");
        String message = this.getConfig().getString("message");
        boolean byDefault = this.getConfig().getBoolean("byDefault");
        List<String> worlds = this.getConfig().getStringList("worlds");
        this.getServer().getPluginManager().registerEvents(new Listeners(permission, message, byDefault, worlds), this);
        if (this.getCommand("fly") != null) {
            this.getCommand("fly").setExecutor(new FlyCommand());
        }
    }
}
