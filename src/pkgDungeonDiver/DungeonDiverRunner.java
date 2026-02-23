package pkgDungeonDiver;

//imports
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;

//screen imports
import pkgScreens.*;

public class DungeonDiverRunner {
	//statics
	//possible equipment positions & descriptors
	public static ArrayList<String> equipmentPositions = new ArrayList<>(Arrays.asList("HELMET", "ARMOR", "WEAPON", "BOOTS", "CHARM"));
	public static ArrayList<String> equipmentDescriptors = new ArrayList<>(Arrays.asList("COOL", "FUNKY"));
	//array of enemy types
	public static ArrayList<String> EnemyTypes = new ArrayList<>(Arrays.asList("Slime", "Skeleton", "Fairy", "Shroom", "Hog", "Boss"));

	//database of skills 
	public void initSkills(ArrayList<Skill> skillArray) {
		skillArray.add(new Skill());
	}
	
	//map storage info
	public static int mapRows = 34;
	public static int mapColumns = 9;
	public static String mapFunction[][] = new String[mapRows][mapColumns];
	public static boolean usedFunction[][] = new boolean[mapRows][mapColumns];
	public static int playerRow = 0;
	public static int playerCol = 4;
	public static String walkDirection = "DOWN";
	public static MapFrame activeMap;

	//main runner
	public static void main(String[] args) {		
		//generate map 
		generateMap();
		
		//initiate a music player
		MusicPlayer.playSound("IntroTwinkle.mp3");
		
		//show main screen
		new MainFrame().setVisible(true);
	}
	
	//initiate char stats
	public static Character playerChar = new Character();
	
	//function to add new equipment to inventory
	public static void addEquipment(Equipment newEquip) {
		int numSameName = 0;
		
		for (Equipment e:playerChar.charInventory) {
			if (e.name.contains(newEquip.name)) {
				numSameName += 1;
			}
		}
		
		//if same equipment name exists, change the name of the equipment
		newEquip.name = newEquip.name + " (" + numSameName + ")";
		
		//append to inventory
		playerChar.charInventory.add(newEquip);
	}
	
	//function to validate input strings
	public static String validateInputStr(String inputQuestion) {
		boolean validInput = false;
		boolean exitAttemptFlag = false;
		while (!validInput) {
			//store integer input for method
			String inputWord = JOptionPane.showInputDialog(null, inputQuestion);
			if (inputWord == null) {//no input
				//flag null
				exitAttemptFlag = true;
				//ask for exit confirmation
				int result = JOptionPane.showConfirmDialog(null, "Do you want to stop inputting?", "Stop", JOptionPane.YES_NO_OPTION);
				//process the choice
				if (result == JOptionPane.YES_OPTION) {//exit
					//show message and stop loop
					validInput = true;
				} else if (result == JOptionPane.NO_OPTION) {//no exit
					//show message
					JOptionPane.showMessageDialog(null, "Try inputting again!");
				} else {//canceled
					//show message
					JOptionPane.showMessageDialog(null, "Click yes if you want to stop!");
				}
			} else if (inputWord.trim().isEmpty() && !exitAttemptFlag) {
				//user inputted nothing
				JOptionPane.showMessageDialog(null, "Input cannot be nothing");
			} else {
				//return entered string 
				return inputWord;
			}
		}
		//catcher code so Java lets the function exist
		return " ";
	}
	
	//function to generate the map 
	public static void generateMap() {
		//store roomstate in 2d map
		for (int m = 0; m < mapRows; m++) {
			for (int n = 0; n < mapColumns; n++) {
				//set unused
				usedFunction[m][n] = false;
				//generate a random number for roomType
				int numFunction = (int)(Math.random()*100) + 1;
				//set random function
				if (numFunction <= 30) {
					mapFunction[m][n] = "VOID";
				} else if (numFunction <= 70) {
					mapFunction[m][n] = "BATTLE";
				} else if (numFunction <= 85) {
					mapFunction[m][n] = "OCCURRENCE";
				} else if (numFunction <= 95) {
					mapFunction[m][n] = "HEALING";
				} else {
					mapFunction[m][n] = "VENDOR";
				}
			}
		}
		
		//override set rooms (init room and boss location)
		usedFunction[playerRow][playerCol] = true;
		mapFunction[playerRow][playerCol] = "BATTLE";
		mapFunction[33][4] = "BOSS";
	}
	
	//function to reset game
	public static void resetGame() {
		//regenerate map
		generateMap();
		//reset position
		playerRow = 0;
	    playerCol = 4;
	    walkDirection = "DOWN";
		
		//refresh display if exists
		if (activeMap != null) {
			//repaint and revalidate the map
			activeMap.refresh();
		}
	}
}
