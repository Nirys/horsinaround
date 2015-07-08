package com.queennuffer.horsinaround;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PaddedLeather extends Item{
	
	public PaddedLeather(){
		super();
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("PaddedLeather");
	}
	
	@Override
	public boolean hasEffect(ItemStack a){
		return true;
	}
	
	public void addRecipes(){
		for(int i=0; i < 16; i++){
		  addRecipe(i);
		}

	}
	
	private void addRecipe(int meta){
    	ItemStack woolStack = new ItemStack(Blocks.wool, 1, meta);
    	ItemStack leatherStack = new ItemStack(Items.leather);
		
    	GameRegistry.addShapedRecipe(new ItemStack(this, 3,0), 
    	    	  "   ",
    	    	  "lll",
    	    	  "www",
    	    	  'l', leatherStack,
    	    	  'w', woolStack);
    	    	
    	    	GameRegistry.addShapedRecipe(new ItemStack(this, 3,0),
    	    	  "lll",
    	    	  "www",
    	    	  "   ",
    	    	  'l', leatherStack,
    	    	  'w', woolStack
    	    	);				
	}

}
