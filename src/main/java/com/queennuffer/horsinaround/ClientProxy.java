package com.queennuffer.horsinaround;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import com.queennuffer.horsinaround.ServerProxy;

public class ClientProxy extends ServerProxy {
	@Override
	public void registerRenderers(){
    	RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedHorse.class, 
      		new RenderHorse(Minecraft.getMinecraft().getRenderManager(), new ModelHorse(), 0.5F));

    		
      	 ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
            mesher.register(HorsinAround.item1, 0, new ModelResourceLocation("minecraft:rotten_flesh", "inventory"));
            mesher.register(HorsinAround.leatherItem, 0, new ModelResourceLocation("minecraft:leather", "inventory"));
            mesher.register(HorsinAround.staticatorItem, 0, new ModelResourceLocation("minecraft:ender_eye", "inventory"));
            mesher.register(HorsinAround.toffeeApple, 0, new ModelResourceLocation("minecraft:apple", "inventory"));            
	}
	
	public void showStaticator(EntityAdvancedHorse forHorse){
		  Minecraft.getMinecraft().displayGuiScreen(new StaticatorGUI(forHorse));
	}
}
