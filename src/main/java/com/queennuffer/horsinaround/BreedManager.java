package com.queennuffer.horsinaround;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreedManager {
	@SubscribeEvent
	public void itemInteract(EntityInteractEvent event){
		EntityPlayer player = (EntityPlayer) event.entityPlayer;
		final EntityAdvancedHorse theRealTarget;
		if(event.entity.worldObj.isRemote) return;
		if (event.target instanceof EntityHorse){				
			if( !(event.target instanceof EntityAdvancedHorse)){			
				World theWorld = event.entity.worldObj;
				EntityHorse theHorse = (EntityHorse)event.target;
				theRealTarget = new EntityAdvancedHorse( ((EntityHorse)event.target), theWorld);
				theWorld.spawnEntityInWorld(theRealTarget);
				theRealTarget.setPosition(event.target.posX, event.target.posY, event.target.posZ);
				theWorld.removeEntity(event.target);
				event.setCanceled(true);								
				return;
			}else{
				theRealTarget = (EntityAdvancedHorse)event.target;
			}

			if (player.inventory.getCurrentItem() != null){
				if(player.inventory.getCurrentItem().getItem() instanceof ToffeeApple){
					ItemStack itemstack = event.entityPlayer.inventory.getCurrentItem();
    		
					if (horseCanLove(theRealTarget)){
						if (!event.entityPlayer.capabilities.isCreativeMode){
							--itemstack.stackSize;		
							if (itemstack.stackSize <= 0){
								player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
							}
						}
						theRealTarget.setInLove(player);
					}
				}
			}
		}
	}
	
	private boolean horseCanLove(EntityHorse horse){
		boolean flag = false;
		if(!horse.isSterile()){
			if (horse.isTame() && horse.getGrowingAge() == 0){
				flag = true;
			}
		}
        return flag;

		
	}
}
