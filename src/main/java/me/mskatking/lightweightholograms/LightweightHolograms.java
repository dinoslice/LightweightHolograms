package me.mskatking.lightweightholograms;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public final class LightweightHolograms extends JavaPlugin {

    public static LightweightHolograms plugin;
    public static List<Hologram> holograms = new ArrayList<>();

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(Hologram.class);

        plugin = this;

        getLogger().log(Level.INFO, "Attempting to load holograms from the config.");
        try {
            if(getConfig().isSet("hologram")) {
                List<Map<String, Object>> holoIn = (List<Map<String, Object>>) getConfig().getList("hologram");
                for (Map<String, Object> holo : holoIn) {
                    holograms.add(new Hologram(holo));
                }
            }
        } catch (Exception ignored) {
            getLogger().log(Level.SEVERE, "Unable to read data from the config!");
        }

        getLogger().log(Level.INFO, "Loaded " + holograms.size() + " from the config.");

        getServer().getCommandMap().register("holograms", new HologramCommand());

        getLogger().log(Level.INFO, "Successfully loaded!");
    }

    @Override
    public void onDisable() {
        List<Map<String, Object>> out = new ArrayList<>();
        holograms.forEach(i -> out.add(i.serialize()));
        getConfig().set("hologram", out);
        saveConfig();
        holograms.forEach(i -> {
            i.destructor();
        });
        getLogger().log(Level.INFO, "Successfully cleaned up!");
    }
}
