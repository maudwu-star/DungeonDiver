package pkgDungeonDiver;

import java.util.ArrayList;
import java.util.Arrays;

public class Character extends Unit{
	//variables 
	public int currGold;
	public ArrayList<Equipment> charInventory = new ArrayList<>(Arrays.asList());
	public Equipment currCharm = null;
	public Equipment currWeapon = null;
	public Equipment currHelmet = null;
	public Equipment currArmor = null;
	public Equipment currBoots = null;
	
	//default random constructor
	public Character() {
		name = "John";
		maxhp = ((int)(Math.random()*100)+200)/10.0;
		currhp = maxhp;
		atk = ((int)(Math.random()*30)+25)/10.0;
		def = ((int)(Math.random()*50)+50)/10.0;
		currGold = 0;
	}
}
