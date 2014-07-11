package net.ironingot.goodhorse;

import net.minecraft.server.v1_7_R1.NBTTagCompound;
import net.minecraft.server.v1_7_R1.NBTTagList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class GoodHorseSpawnListener implements Listener {
    public GoodHorseBroadcaster plugin;

    public GoodHorseSpawnListener(GoodHorseBroadcaster plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Location location = event.getEntity().getLocation();
        World world = event.getEntity().getWorld();

        if (event.getEntityType() == EntityType.HORSE) {
            Horse horse = (Horse) event.getEntity();

            double speed = getSpeed(horse);
            double jump = horse.getJumpStrength();
            double hp = horse.getMaxHealth();
            double point = speed * 5 + jump;

            if (point > 2.44 ) {
                plugin.getServer().broadcastMessage(ChatColor.RED + "Special horse was born in " + world.getName() + "!!!");
            } else if (point > 2.38 ) {
                plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Super horse spawned in " + world.getName() + "!!");
            } else if (point > 2.32) {
                plugin.getServer().broadcastMessage(ChatColor.AQUA + "Good horse spawned in " + world.getName() + "!");
            }
        }
    }

    public double getSpeed(Horse horse) {
        CraftHorse craftHorse = (CraftHorse) horse;
        NBTTagCompound tag = new NBTTagCompound();

        craftHorse.getHandle().b(tag);
        NBTTagList attributes = (NBTTagList) tag.getList("Attributes", 10);

        double speed = 0.0f;
        for (int i = 0; i < attributes.size(); i++) {
            NBTTagCompound attr = attributes.get(i);
            if (attr.getString("Name").equals("generic.movementSpeed")) {
                speed = attr.getDouble("Base");
            }
        }
        return speed;
    }       
}
