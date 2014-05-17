
package com.dinnerbone.bukkit.sample;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Sample plugin for Bukkit
 *
 * @author Dinnerbone
 */
public class SamplePlugin extends JavaPlugin {
    private final SamplePlayerListener playerListener = new SamplePlayerListener(this);
    private final SampleBlockListener blockListener = new SampleBlockListener();
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        getLogger().info("Goodbye world!");
    }

    @Override
    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        /* TODO: re-enable after fixing UnsatistifiedLinkError
        getLogger().info("about to registerEvents");
        PluginManager pm = getServer().getPluginManager();
        getLogger().info("PluginManager = "+pm);
        pm.registerEvents(playerListener, this);
        getLogger().info("registered playerListener");
        pm.registerEvents(blockListener, this);
        getLogger().info("registered blockListener");
        */

        // Register our commands
        /* TODO: re-enable after fixing registering commands from plugin.yml
        getCommand("pos").setExecutor(new SamplePosCommand());
        getCommand("debug").setExecutor(new SampleDebugCommand(this));
        */

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );

        getLogger().info("dirt = " + Material.DIRT + ", name = "+Material.DIRT.name()+", ordinal="+Material.DIRT.ordinal());

        checkMaterial("STONE");
        checkMaterial("DIRT");
        checkMaterial("SAND");
        checkMaterial("invalid");
    }

    private void checkMaterial(String name) {
        Material m = Material.getMaterial(name);
        if (m == null) {
            getLogger().info("got null");
            return;
        }

        // test switch() on Material enum
        switch (m) {
            case STONE:
                getLogger().info("got stone");
                break;
            case DIRT:
                getLogger().info("got dirt");
                break;
            default:
                getLogger().info("got else");
        }
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
