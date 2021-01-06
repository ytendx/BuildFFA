package net.minebaum.buildffa.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    public static File file = new File("plugins//Lobby//locations.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setLocation(String name, Location loc) throws IOException {
        cfg.set(name+".world", loc.getWorld().getName());
        cfg.set(name+".x", loc.getX());
        cfg.set(name+".y", loc.getY());
        cfg.set(name+".z", loc.getZ());
        cfg.set(name+".yaw", loc.getYaw());
        cfg.set(name+".pitch", loc.getPitch());

        cfg.save(file);
    }
    public static Location getLocation(String name) {
        World w = Bukkit.getWorld(cfg.getString(name+".world"));
        double x = cfg.getDouble(name+".x");
        double y = cfg.getDouble(name+".y");
        double z = cfg.getDouble(name+".z");
        float yaw = (float) cfg.getDouble(name+".yaw");
        float pitch = (float) cfg.getDouble(name+".pitch");
        Location loc = new Location(w,x,y,z,yaw,pitch);
        return loc;
    }

}
