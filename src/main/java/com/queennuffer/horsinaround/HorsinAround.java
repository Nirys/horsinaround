package com.queennuffer.horsinaround;

import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.queennuffer.horsinaround.CookedZombieFlesh;

@Mod(modid = HorsinAround.MODID, name="HorsinAround", version = HorsinAround.VERSION, acceptableRemoteVersions = "*")
public class HorsinAround
{
	@Instance("HorsinAround")
	public static HorsinAround instance;
	
	@SidedProxy(clientSide="com.queennuffer.horsinaround.ClientProxy", serverSide="com.queennuffer.horsinaround.ServerProxy")
	public static ServerProxy proxy;
	
	public static CookedZombieFlesh item1;
	public static PaddedLeather leatherItem;
	public static Staticator staticatorItem;
	public static ToffeeApple toffeeApple;

    public static final String MODID = "HorsinAround";
    public static final String VERSION = "1.0";
    
    public HorsinAround(){
            	    
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent preInitEvent)
    {    	
    	int ID = 0;
    	EntityRegistry.registerModEntity(EntityAdvancedHorse.class, "AdvancedHorse", ID, this, 80, 3, true);
    	MinecraftForge.EVENT_BUS.register(new BreedManager());
    	
    	item1 = (new CookedZombieFlesh(1));
    	GameRegistry.registerItem(item1, "CookedZombieFlesh");
    	
    	leatherItem = (new PaddedLeather());    	
    	GameRegistry.registerItem(leatherItem, "PaddedLeather");
    	
    	staticatorItem = new Staticator();
    	GameRegistry.registerItem(staticatorItem, "staticator");
    	
    	toffeeApple = new ToffeeApple(2);
    	GameRegistry.registerItem(toffeeApple, "ToffeeApple");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent initEvent){    	    
    	proxy.registerRenderers();
    	leatherItem.addRecipes();
    	item1.addRecipes();
    	toffeeApple.doInit(initEvent);
    	staticatorItem.doInit(initEvent);
    	
    	ItemStack leatherStack = new ItemStack(Items.leather, 1, 0);
    	ItemStack cookedStack = new ItemStack(item1, 1,0);
    	
    	GameRegistry.addShapelessRecipe(leatherStack, cookedStack,cookedStack,cookedStack,cookedStack);    	
    	GameRegistry.addShapedRecipe(new ItemStack(Items.saddle),
    	  "lll",
    	  "ppp",
    	  " s ",
    	  'l', leatherStack,
    	  'p', new ItemStack(leatherItem),
    	  's', new ItemStack(Items.string)
    	);
    	
    }
}
