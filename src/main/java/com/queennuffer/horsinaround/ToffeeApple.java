package com.queennuffer.horsinaround;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ToffeeApple extends ItemFood {
	public ToffeeApple(int healAmount){
		super(healAmount, false);
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT){
		  this.setMaxStackSize(64);
		  this.setCreativeTab(CreativeTabs.tabFood);
		  this.setUnlocalizedName("ToffeeApple");
		}
	}		
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
	
	public void addRecipes(){
    	GameRegistry.addShapedRecipe(new ItemStack(this),
    	    	  "sss",
    	    	  "sas",
    	    	  "sss",
    	    	  's', new ItemStack(Items.sugar),
    	    	  'a', new ItemStack(Items.apple)
    	    	);
	}

	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
	{
		if(target instanceof EntityHorse){
			 return true;
		}else{
		  return false;
		}
	}	
	
    public void doInit(FMLInitializationEvent initEvent){
    	this.addRecipes();
    }
}
