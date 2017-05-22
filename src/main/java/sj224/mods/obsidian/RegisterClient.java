package sj224.mods.obsidian;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class RegisterClient extends Register{
	public void register(Item it,String name){
		super.register(it,name);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(it, 0, new ModelResourceLocation(ModMain.MODID+":"+name, "inventory"));
	}
}
