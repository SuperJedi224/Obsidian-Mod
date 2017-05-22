package sj224.mods.obsidian;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockGeneric extends Block {
	private BlockGeneric(String name,Material m,MapColor c,int l){
		super(m,c);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setHarvestLevel("pickaxe",l);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		GameRegistry.register(this);
		Item i=new ItemBlock(this);
		i.setRegistryName(name);
		GameRegistry.register(i);
		this.setHardness(ModMain.obsid_hardness);
		this.setResistance(2000);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(ModMain.MODID+":"+name, "inventory"));
	}
	public BlockGeneric(String name){
		this(name,Material.ROCK,MapColor.BLACK,3);
	}
	public BlockGeneric(String name,Material m,int l){
		this(name,m,m.getMaterialMapColor(),l);
	}
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.BLOCK;
	}
}
