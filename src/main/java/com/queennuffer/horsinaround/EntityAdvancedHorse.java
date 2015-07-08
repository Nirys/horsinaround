package com.queennuffer.horsinaround;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityAdvancedHorse extends EntityHorse {
    private static final IAttribute advHorseSwimStrength = (new RangedAttribute((IAttribute)null, "advhorse.swimStrength", 2.0D, 2.0D, 10.0D)).setDescription("Swim Strength").setShouldWatch(true);

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(advHorseSwimStrength);
        this.getEntityAttribute(advHorseSwimStrength).setBaseValue(2);        
    }
    
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setDouble("AdvSwimStrength", this.getSwimStrength());
    }    
    
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.getEntityAttribute(advHorseSwimStrength).setBaseValue(tagCompund.getDouble("AdvSwimStrength"));        
    }       
       
	@Override
    public EntityAgeable createChild(EntityAgeable ageable){
		EntityAdvancedHorse spouse = (EntityAdvancedHorse)ageable;
		EntityAgeable kid = super.createChild(ageable);
		EntityAdvancedHorse realKid = new EntityAdvancedHorse( (EntityHorse)kid, kid.worldObj);
		AdvancedHorseStatCalculator stat1 = new AdvancedHorseStatCalculator(this);
		AdvancedHorseStatCalculator stat2 = new AdvancedHorseStatCalculator(spouse);
		
		double newSpeed = stat1.getNewSpeed(stat2);
		double newJump = stat1.getNewJump(stat2);
		double newHealth = stat1.getNewHealth(stat2);
		realKid.setMovementSpeed( newSpeed );
		realKid.setJumpStrength( newJump );
		realKid.setMaxHealth( newHealth );
		
		return realKid;    	
    }
	
	public void setMaxHealth(double v){
        IAttributeInstance health = this.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        health.setBaseValue(v);		
	}
	
	
	public EntityAdvancedHorse(EntityHorse copyFrom, World worldIn){
		super(worldIn);
		BlockPos p = copyFrom.getPosition();
		this.copyDataFromOld(copyFrom);     
		this.setPosition( p.getX(), p.getY(), p.getZ() );
	}
	
	public EntityAdvancedHorse(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	public double getMovementSpeed(){
	    IAttributeInstance speed = this.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed");
	    double moveSpeed = (speed.getAttributeValue() * 43);
	    
	    return round(moveSpeed,2);
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}	
	
	public void setMovementSpeed(double value){
	    IAttributeInstance speed = this.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed");	    
	    value = value / 43;
	    speed.setBaseValue(value);
	}
	
	public double getJumpStrength(){
		return this.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").getAttributeValue();		
	}
	
	public void setJumpStrength(double v){
		this.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(v);				
	}
	
	public double getSwimStrength(){
		IAttributeInstance swim = this.getEntityAttribute(advHorseSwimStrength);
		return swim.getAttributeValue();
	}

}
