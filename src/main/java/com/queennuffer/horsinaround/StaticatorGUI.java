package com.queennuffer.horsinaround;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.minecraft.client.gui.GuiScreen;

public class StaticatorGUI extends GuiScreen {
	public static final int GUI_ID = 20;
	private EntityAdvancedHorse _horse;
	
    public StaticatorGUI(EntityAdvancedHorse theHorse) {
    	_horse = theHorse;
    }
    
    @Override
    public boolean doesGuiPauseGame(){
    	return false;
    }

    @Override
    public void updateScreen() {
    	this.drawScreen(0, 0, 0);
    }
    
    
    @Override
    public void drawScreen(int par1, int par2, float par3) {
      //this.drawDefaultBackground();
      
      double moveSpeed = _horse.getMovementSpeed();// Math.round( (speed.getAttributeValue() * 43));      
      double maxHealth = _horse.getMaxHealth();
      
      int statCount = 6;
      if(!_horse.isAdultHorse()){
    	  statCount++;
      }else if(_horse.isTame() && _horse.getGrowingAge() > 0){
    	  statCount++;    	  
      }
            
      drawRect(2, 2, 170, (4 + (statCount*fontRendererObj.FONT_HEIGHT)), 0xffffffff);      
      double jumpStrength = _horse.getHorseJumpStrength();
      jumpStrength = jumpStrength * 10;
      jumpStrength = horound(jumpStrength, 2);
      
      String status = "";
      if(_horse.isTame()) status = "Tame"; else status = "Wild";
      int y = 4;
      String horseName = _horse.getCustomNameTag();
      if(horseName.equals("")) horseName = "(None)";
      addStat("Name", horseName, y);      
      y += fontRendererObj.FONT_HEIGHT;
      String age = "";
      if(_horse.isAdultHorse()){
    	  age = "Adult";
      }else{
    	  age = "Foal";
      }
      addStat("Age", age, y);
      y += fontRendererObj.FONT_HEIGHT;
      addStat("Status", status, y);
      y += fontRendererObj.FONT_HEIGHT;
      addStat("Speed", String.valueOf(moveSpeed) + "/bps", y);
      y += fontRendererObj.FONT_HEIGHT;
      addStat("Max Health", String.valueOf(horound(maxHealth,2)), y);
      y += fontRendererObj.FONT_HEIGHT;
      addStat("Jump Strength", String.valueOf(jumpStrength), y);
      y += fontRendererObj.FONT_HEIGHT;
      if( (age == "Foal") || (_horse.isTame() && _horse.getGrowingAge() > 0)){
    	  double mins = 0;
    	  double secs = _horse.getGrowingAge();
    	  if(age=="Foal") secs = secs * -1;
    	  secs = secs/20;
    	  if(secs > 0) mins = Math.floor(secs/60);
    	  secs = secs - (mins*60);
    	  
    	  
    	  String theTime = String.valueOf( (int)mins) + ":" + String.valueOf((int)secs);    	  
    	  addStat("Cooldown", String.valueOf(theTime), y);      
      }
      //y += fontRendererObj.FONT_HEIGHT;
      //addStat("Swim Strength", String.valueOf(swimStrength), y);
      
      
      //fontRendererObj.drawString("Speed: " + String.valueOf(moveSpeed) + "/bps", 4, 4, 0x000000);
    }
    
    private double horound(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }    
    
    private void addStat(String Name, String value, int y) {
      fontRendererObj.drawString(Name + ": ", 4, y, 0x000000);
      fontRendererObj.drawString(value, 90, y, 0x000000 );
    }
}
