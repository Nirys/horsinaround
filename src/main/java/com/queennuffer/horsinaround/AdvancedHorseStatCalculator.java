package com.queennuffer.horsinaround;

import java.util.Random;

public class AdvancedHorseStatCalculator {
	private EntityAdvancedHorse _theHorse;
	static boolean doneIncrease = false;
	public static final int HEALTH = 1;
	public static final int JUMP = 2;
	public static final int SPEED = 3;
	
	public static final int HEALTH_MAX = 30;
	public static final double SPEED_MAX = 14.5125;
	public static final int JUMP_MAX = 1;

	public static final int HEALTH_MIN = 15;
	public static final double SPEED_MIN = 0.1125;
	public static final double JUMP_MIN = 0.4;
	
	public AdvancedHorseStatCalculator(EntityAdvancedHorse horse){
		_theHorse = horse;		
	}
	
	private double doDecrease(double stat1Per, double stat2Per, double min, double max){
		double avg = (stat1Per + stat2Per)/2;
		double incPer = AdvancedHorseStatCalculator.randomPercent(0.05, 0.2);
		double theInc = ( (avg * max) * incPer );
		double newStat = (avg * max) - theInc ;
		if(newStat > max) newStat = max;
		if(newStat < min) newStat = min;
		return newStat;		
	}
	
	private double doIncrease(double stat1Per, double stat2Per, double max){
		double avg = (stat1Per + stat2Per)/2;
		double incPer = AdvancedHorseStatCalculator.randomPercent(0.05, 0.2);
		double theInc = ( (avg * max) * incPer );
		double newStat = (avg * max) + theInc ;
		if(newStat > max) newStat = max;
		return newStat;		
	}
	
	public double getNewHealth(AdvancedHorseStatCalculator stat2){
		return this.getNewStat(stat2, HEALTH, this.getHealthPercent(), stat2.getHealthPercent(), HEALTH_MIN, HEALTH_MAX);		
	}

	public double getNewSpeed(AdvancedHorseStatCalculator stat2){
		return this.getNewStat(stat2, SPEED, this.getSpeedPercent(), stat2.getSpeedPercent(), SPEED_MIN, SPEED_MAX);		
	}

	public double getNewJump(AdvancedHorseStatCalculator stat2){
		return this.getNewStat(stat2, JUMP, this.getJumpPercent(), stat2.getJumpPercent(), JUMP_MIN, JUMP_MAX);		
	}
	
	private double getNewStat(AdvancedHorseStatCalculator stat2, int theStat, double val1, double val2, double min, double max){		
		int best1 = this.getBestAttribute();
		int best2 = stat2.getBestAttribute();
		int sec1 = this.getSecondBestAttribute();
		int sec2 = stat2.getSecondBestAttribute();
		
		double theRand = Math.random();
		
		if( (best1==best2) && best1 == theStat){
			return this.doIncrease(val1, val2, max);						
		}else if((best1==theStat || best2==theStat) && (sec1==theStat || sec2==theStat)){		
			if(theRand < 0.70 && !doneIncrease){
				doneIncrease = true;
				return this.doIncrease(val1, val2, max);
			}else{
				return this.doDecrease(val1, val2, min, max);															
			}
		}else{
			if(theRand > 0.70 && !doneIncrease){
				doneIncrease = true;
				return this.doIncrease(val1, val2, max);
			}else{
				return this.doDecrease(val1, val2, min, max);
			}
		}
	}
	
	public static double randomPercent(double min, double max){
	   // nextInt is normally exclusive of the top value,
	   // so add 1 to make it inclusive
		Random rand = new Random();	
		int iMax = (int) Math.round(max * 100);
		int iMin = (int) Math.round(min * 100);
		int randomNum = rand.nextInt((iMax - iMin) + 1) + iMin;
		
		double thePer = (double)randomNum / 100;
		return thePer;
	}
	
	public int getSecondBestAttribute(){
		int best = this.getBestAttribute();
		switch(best){
		case AdvancedHorseStatCalculator.HEALTH:
			if(this.getJumpPercent() > this.getSpeedPercent()) 
				return AdvancedHorseStatCalculator.JUMP;
				else
					return AdvancedHorseStatCalculator.SPEED;
		case AdvancedHorseStatCalculator.JUMP:
			if (this.getHealthPercent() > this.getSpeedPercent())
				return AdvancedHorseStatCalculator.HEALTH;
			else
				return AdvancedHorseStatCalculator.SPEED;
		case AdvancedHorseStatCalculator.SPEED:
			if(this.getJumpPercent() > this.getHealthPercent())
				return AdvancedHorseStatCalculator.JUMP;
			else
				return AdvancedHorseStatCalculator.HEALTH;
		}
		return -1;
	}
	
	public int getBestAttribute(){
		double speedPer = this.getSpeedPercent();
		double jumpPer = this.getJumpPercent();
		double healthPer = this.getHealthPercent();
				
		if(speedPer > jumpPer && speedPer > healthPer){
			// Horse 1 has speed best stat
			return AdvancedHorseStatCalculator.SPEED;
		}else if(jumpPer > speedPer && jumpPer > healthPer){
			return AdvancedHorseStatCalculator.JUMP;
		}else{
			return AdvancedHorseStatCalculator.HEALTH;
		}		
		
	}
	
	/* Returns the health attribute for this horse
	 * as a percentage of maximum.
	 */
	public double getHealthPercent(){
		return _theHorse.getMaxHealth() / 30;	
	}
	
	/* Returns the horse's movement speed
	 * as a percentage of maximum.
	 */
	public double getSpeedPercent(){
		return (_theHorse.getMovementSpeed() / 43 )/ 0.3375;		
	}
	
	/* Returns the horse's jump strength
	 * as a percentage of maximum
	 */
	public double getJumpPercent(){
		return _theHorse.getJumpStrength() / 1;		
	}

}
