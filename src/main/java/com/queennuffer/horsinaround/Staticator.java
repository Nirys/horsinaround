package com.queennuffer.horsinaround;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Staticator extends Item{
	private final String name = "staticator";
	
	public Staticator(){
		super();
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName(name);
	}
	
	public String getName(){
		return name;	
	}
	
	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
	{
		if(target instanceof EntityAdvancedHorse){
			HorsinAround.proxy.showStaticator((EntityAdvancedHorse)target);
			//playerIn.openGui(mod, modGuiId, world, x, y, z);
  		//playerIn.openG
		  return true;
		}else{
		  return false;
		}
	//Your code
	}	
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
	
    public void doInit(FMLInitializationEvent initEvent){
    	this.addRecipes();
    }
    
    public void addRecipes(){
    	GameRegistry.addShapedRecipe(new ItemStack(this),
  	    	  "   ",
  	    	  " e ",
  	    	  " s ",
  	    	  'e', new ItemStack(Items.ender_eye),
  	    	  's', new ItemStack(Items.stick)
  	    	);
    
    }
    	
	
/*	@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
//    	if(playerIn.canRiderInteract()){
    		
//    	}
    	return itemStackIn;	
    }*/
}
