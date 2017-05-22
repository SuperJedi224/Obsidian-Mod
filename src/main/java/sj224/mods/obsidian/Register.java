package sj224.mods.obsidian;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Register {
	public void register(Item it,String name){
		it.setRegistryName(name);
		it.setUnlocalizedName(name);
		GameRegistry.register(it);
	}
}
