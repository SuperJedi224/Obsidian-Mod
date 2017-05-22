package sj224.mods.obsidian;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.SidedProxy;

public class ItemGeneric extends Item {
	public ItemGeneric(String name){
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		r.register(this, name);
	}
	@SidedProxy(clientSide="sj224.mods.obsidian.RegisterClient", serverSide="sj224.mods.obsidian.Register")
	static Register r;
}
