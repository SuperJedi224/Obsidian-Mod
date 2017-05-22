package sj224.mods.obsidian;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.SidedProxy;

public class ArmorObsidian extends ItemArmor {
	@SidedProxy(clientSide="sj224.mods.obsidian.RegisterClient", serverSide="sj224.mods.obsidian.Register")
	static Register r;
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		// TODO Auto-generated method stub
		return false;
	}


	public final ItemStack stack=new ItemStack(this,1,0);
	public final ItemStack stack_ench=new ItemStack(this,1,0);
	public ArmorObsidian(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn,String name) {
		super(armor_obsidian, renderIndexIn, equipmentSlotIn);
		r.register(this, name);
		stack_ench.addEnchantment(Enchantments.FIRE_PROTECTION,4);
		stack_ench.addEnchantment(Enchantments.BLAST_PROTECTION,4);
		stack_ench.addEnchantment(Enchantments.UNBREAKING,2);
		stack_ench.addAttributeModifier("generic.knockbackResistance",new AttributeModifier("ObsidArmorModifier",0.1, 0),equipmentSlotIn);
		stack_ench.addAttributeModifier("generic.movementSpeed",new AttributeModifier("ObsidArmorSlowModifier",-0.008, 0),equipmentSlotIn);
		stack.addAttributeModifier("generic.knockbackResistance",new AttributeModifier("ObsidArmorModifier",0.1, 0),equipmentSlotIn);
		stack.addAttributeModifier("generic.movementSpeed",new AttributeModifier("ObsidArmorSlowModifier",-0.008, 0),equipmentSlotIn);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		// TODO Auto-generated method stub
		super.onArmorTick(world, player, itemStack);
		if(world.isRemote)return;
		if(this.getEquipmentSlot()!=EntityEquipmentSlot.CHEST);
		for(ItemStack s:player.getArmorInventoryList()){
			if(s.getUnlocalizedName().indexOf("obsidian_")==-1)return;
			if(!s.isItemEnchanted())return;
		}
		player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,10,0));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		subItems.add(stack);
		subItems.add(stack_ench);
	}
	

	public static final ArmorMaterial armor_obsidian=EnumHelper.addArmorMaterial("obsid_arm", ModMain.MODID+":obsidian",26, new int[]{3, 8, 6, 3}, 7,SoundEvents.BLOCK_STONE_FALL,3);

}
