package com.queennuffer.horsinaround;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CookedZombieFlesh extends ItemFood {
	public CookedZombieFlesh(int healAmount){
		super(healAmount, false);
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT){
		  this.setMaxStackSize(64);
		  this.setCreativeTab(CreativeTabs.tabFood);
		  this.setUnlocalizedName("CookedZombieFlesh");
		}
	}	
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
	
	public void addRecipes(){
    	GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(this),(float) 0.1);		
	}
}
