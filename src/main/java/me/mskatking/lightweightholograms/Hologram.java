package me.mskatking.lightweightholograms;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Hologram implements ConfigurationSerializable {

    ArmorStand summonedEntity;

    public Hologram(Player p, Component n) {
        summonedEntity = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        summonedEntity.customName(n);
        summonedEntity.setCustomNameVisible(true);
        summonedEntity.setArms(false);
        summonedEntity.setAI(false);
        summonedEntity.setBasePlate(false);
        summonedEntity.setSmall(true);
        summonedEntity.setSilent(true);
        summonedEntity.setHealth(0.1);
        summonedEntity.setInvisible(true);
        summonedEntity.setInvulnerable(true);
        summonedEntity.setCanTick(false);
        summonedEntity.setDisabledSlots(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HAND, EquipmentSlot.LEGS, EquipmentSlot.HEAD, EquipmentSlot.OFF_HAND);
        summonedEntity.setCanMove(false);
        summonedEntity.setCanTick(false);
        summonedEntity.setGlowing(false);
        summonedEntity.setMarker(true);
        summonedEntity.getPersistentDataContainer().set(new NamespacedKey(LightweightHolograms.plugin, "persistent"), PersistentDataType.BYTE, (byte) 1);
    }

    public Hologram(Map<String, Object> datain) {
        summonedEntity = (ArmorStand) Location.deserialize((Map<String, Object>) datain.get("location")).getWorld().spawnEntity(Location.deserialize((Map<String, Object>) datain.get("location")), EntityType.ARMOR_STAND);
        summonedEntity.customName(MiniMessage.miniMessage().deserialize((String) datain.get("component")));
        summonedEntity.setCustomNameVisible(true);
        summonedEntity.setArms(false);
        summonedEntity.setAI(false);
        summonedEntity.setBasePlate(false);
        summonedEntity.setSmall(true);
        summonedEntity.setSilent(true);
        summonedEntity.setHealth(0.1);
        summonedEntity.setInvisible(true);
        summonedEntity.setInvulnerable(true);
        summonedEntity.setCanTick(false);
        summonedEntity.setDisabledSlots(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HAND, EquipmentSlot.LEGS, EquipmentSlot.HEAD, EquipmentSlot.OFF_HAND);
        summonedEntity.setCanMove(false);
        summonedEntity.setCanTick(false);
        summonedEntity.setGlowing(false);
        summonedEntity.setMarker(true);
        summonedEntity.getPersistentDataContainer().set(new NamespacedKey(LightweightHolograms.plugin, "persistent"), PersistentDataType.BYTE, (byte) 1);
    }

    public void destructor() {
        summonedEntity.remove();
        summonedEntity = null;
    }

    public void setName(Component n) {
        this.summonedEntity.customName(n);
    }

    public void moveHere(Location loc) {
        this.summonedEntity.teleport(loc);
    }

    public Location getLocation() {
        return this.summonedEntity.getLocation();
    }

    public String getName() {
        return MiniMessage.miniMessage().serialize(this.summonedEntity.customName());
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return Map.of("component", MiniMessage.miniMessage().serialize(summonedEntity.customName()), "location", summonedEntity.getLocation().serialize());
    }
}
