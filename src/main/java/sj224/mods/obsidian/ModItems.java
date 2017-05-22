package sj224.mods.obsidian;

import net.minecraft.inventory.EntityEquipmentSlot;

public class ModItems {
	public static final ArmorObsidian ob_helm=new ArmorObsidian(1,EntityEquipmentSlot.HEAD,"obsidian_helmet");
	public static final ArmorObsidian ob_chestplate=new ArmorObsidian(1,EntityEquipmentSlot.CHEST,"obsidian_chestplate");
	public static final ArmorObsidian ob_leggins=new ArmorObsidian(2,EntityEquipmentSlot.LEGS,"obsidian_leggings");
	public static final ArmorObsidian ob_boots=new ArmorObsidian(1,EntityEquipmentSlot.FEET,"obsidian_boots");
    public static final ItemGeneric obsidian_ingot=new ItemGeneric("obsidian_ingot");
    public static final ItemGeneric obsidian_ingot_polished=new ItemGeneric("obsidian_ingot_polished");
}
