package sj224.mods.obsidian;



import static sj224.mods.obsidian.ModItems.*;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = ModMain.MODID, version = ModMain.VERSION)
public class ModMain
{
    public static final String MODID = "sjobsidian";
    public static final String VERSION = "1.0";
    
    public static Block g_obsid;
    public static Block o_brick;
    public static Block o_glass;
    
    public static int obsid_hardness;
    public static int obsid_resist;
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Configuration cfg=new Configuration(event.getSuggestedConfigurationFile());
		obsid_hardness=cfg.get(Configuration.CATEGORY_GENERAL,"obsidian_hardness",50).getInt();
		obsid_hardness=Math.min(1,obsid_hardness);
		obsid_resist=cfg.get(Configuration.CATEGORY_GENERAL,"obsidian_resistance",6000).getInt()/3;
		cfg.save();
	}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Blocks.OBSIDIAN.setHardness(obsid_hardness).setResistance(obsid_resist);
    	g_obsid=new BlockGeneric("glowing_obsidian").setResistance((int)(obsid_resist*0.8)).setLightLevel(13f/15);
    	o_brick=new BlockGeneric("obsidian_bricks");
    	o_glass=new BlockGeneric("obsidian_glass"){
    		{this.setCreativeTab(CreativeTabs.DECORATIONS);}
    		@SideOnly(Side.CLIENT)
    	    public boolean isOpaqueCube(IBlockState state)
    	    {
    	        return false;
    	    }
    	    
    	    public boolean isFullCube(IBlockState state)
    	    {
    	        return false;
    	    }
    	    
    	    @SideOnly(Side.CLIENT)
    	    public BlockRenderLayer getBlockLayer()
    	    {
    	        return BlockRenderLayer.TRANSLUCENT;
    	    }

			@SideOnly(Side.CLIENT)
    	    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    	    {
    	        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
    	        Block block = iblockstate.getBlock();

    	        return block!=this;
    	    }

    	}.setHardness(Math.min(obsid_hardness,1+(int)(obsid_hardness*0.25))).setResistance((int)(obsid_resist*0.6));
    	
    	OreDictionary.registerOre("ingotObsidian", obsidian_ingot);
    	OreDictionary.registerOre("ingotObsidianPolished", obsidian_ingot_polished);
    	
    	GameRegistry.addRecipe(new ItemStack(g_obsid,3),"og","go",'g',Items.GLOWSTONE_DUST,'o',obsidian_ingot_polished);
    	GameRegistry.addRecipe(new ItemStack(o_brick,4),"oo","oo",'o',obsidian_ingot);
    	GameRegistry.addRecipe(new ItemStack(o_glass,5),"gog","ogo","gog",'o',obsidian_ingot,'g',Blocks.GLASS);
    	
    	GameRegistry.addRecipe(ob_helm.stack_ench,"ooo","o o",'o',obsidian_ingot_polished);
    	GameRegistry.addRecipe(ob_chestplate.stack_ench,"o o","ooo","ooo",'o',obsidian_ingot_polished);
    	GameRegistry.addRecipe(ob_leggins.stack_ench,"ooo","o o","o o",'o',obsidian_ingot_polished);
    	GameRegistry.addRecipe(ob_boots.stack_ench,"o o","o o",'o',obsidian_ingot_polished);
    	
    	GameRegistry.addRecipe(ob_helm.stack,"ooo","o o",'o',obsidian_ingot);
    	GameRegistry.addRecipe(ob_chestplate.stack,"o o","ooo","ooo",'o',obsidian_ingot);
    	GameRegistry.addRecipe(ob_leggins.stack,"ooo","o o","o o",'o',obsidian_ingot);
    	GameRegistry.addRecipe(ob_boots.stack,"o o","o o",'o',obsidian_ingot);
    	
    	
    	
    	GameRegistry.addRecipe(new ItemStack(obsidian_ingot_polished),"ob","bo",'o',obsidian_ingot,'b',Items.BLAZE_POWDER);
    	
    	GameRegistry.addSmelting(new ItemStack(Blocks.OBSIDIAN),new ItemStack(obsidian_ingot),1);
    	
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent e) {
		ItemStack s=e.getLeft();
		if(s.getItem() instanceof ArmorObsidian){
			e.setCanceled(true);
		}
    }
}
