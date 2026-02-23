package pkgDungeonDiver;

import java.util.ArrayList;
import java.util.Arrays;

public class Unit {
	//global unit stats
	double maxhp, currhp, atk, def;
	String name, imgFile;
	public ArrayList<Skill> availableSkills = new ArrayList<>(Arrays.asList());
	
	//fetchers
	public String getName() {
		return name;
	}
	
	public double getMaxHP() {
		return maxhp;
	}
	
	public double getCurrHP() {
		return currhp;
	}
	
	public double getATK() {
		return atk;
	}
	
	public double getDEF() {
		return def;
	}
	
	public String getIMG() {
		return imgFile;
	}
	
	//setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setMaxHP(double newHP) {
		maxhp = newHP;
	}
	
	public void setCurrHP(double newHP) {
		currhp = newHP;
	}
	
	public void setATK(double newATK) {
		atk = newATK;
	}
	
	public void setDEF(double newDEF) {
		def = newDEF;
	}
	
	public void setIMG(String newIMG) {
		imgFile = newIMG;
	}
}
