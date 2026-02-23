package pkgDungeonDiver;

//imports
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

public class OccurrenceDatabase {
	//class variables
	public static String occurrenceName;
	public static ArrayList<String> occurrenceScript;
	
	//database common variables
	public static ArrayList<String> occurrenceTypes = new ArrayList<>(Arrays.asList("ITEM", "HEALING"));
	private DecimalFormat df = new DecimalFormat("0.#");
	
	//occurrence database
	ArrayList<String> occurrenceDatabase = new ArrayList<>(Arrays.asList("ABYSS", "STARS", "DRAGON", "HOT CHOCOLATE"));
	
	public OccurrenceDatabase() {
		//choose a random occurrence
		int occurrenceIndex = (int)(Math.random()*occurrenceDatabase.size());
		
		//set occurrence
		occurrenceName = occurrenceDatabase.get(occurrenceIndex);
		if (occurrenceIndex == 0) {
			//generate a random item to drop + add to inventory
			Equipment randItem = new Equipment();
			DungeonDiverRunner.addEquipment(randItem);
			
			//script
			occurrenceScript = new ArrayList<>(Arrays.asList("The abyss gazes back.",
															"It rolls its eyes.", 
															"It smiles.",
															"It grants you an item!",
															randItem.name + " recieved."));
		} else if (occurrenceIndex == 1) {
			//generate a random amount of hp to lose 
			double randHPLoss = (((Math.random()*50)+10)/100) * DungeonDiverRunner.playerChar.getCurrHP();
			//reduce hp
			DungeonDiverRunner.playerChar.setCurrHP(DungeonDiverRunner.playerChar.getCurrHP() - randHPLoss);
			//script
			occurrenceScript = new ArrayList<>(Arrays.asList("You stumble upon a convention of golden stars.",
															"They pay you no mind.",
															"Slowly, you feel your body's decay.",
															df.format(randHPLoss) + " HP lost."));
		} else if (occurrenceIndex == 2) {
			//generate a random amount of gold
			int randGold = (int)(Math.random()*19)+1;
			//add gold
			DungeonDiverRunner.playerChar.currGold += randGold;
			//script
			occurrenceScript = new ArrayList<>(Arrays.asList("You stumble upon a slumbering dragon.",
															"It does not notice as you swipe away a tad of its treasures.",
															randGold + " gold aquired!"));
			
		} else if (occurrenceIndex == 3) {
			//generate a random amount of hp to heal
			double randExtraHP = ((Math.random()*0.40) + 0.01) * DungeonDiverRunner.playerChar.getMaxHP();
			//increase hp
			DungeonDiverRunner.playerChar.setCurrHP(DungeonDiverRunner.playerChar.getCurrHP() + randExtraHP);
			//if hp is now over max, reduce
			if (DungeonDiverRunner.playerChar.getCurrHP() > DungeonDiverRunner.playerChar.getMaxHP()) {//current hp > max
				//reduce hp to max
				DungeonDiverRunner.playerChar.setCurrHP(DungeonDiverRunner.playerChar.getMaxHP());
			}
			//script
			occurrenceScript = new ArrayList<>(Arrays.asList("You stumble upon a cottage with a warm fireplace.",
															"A short old lady comes up to you.",
															"She hands you a cup of hot chocolate.",
															"You rest and listen to her stories.",
															"You have healed " + df.format(randExtraHP) + " HP!"));
		}  else {//catch errors
			System.out.print("asd");
			occurrenceName = "ERROR";
			occurrenceScript = new ArrayList<>(Arrays.asList("error."));
		}
	}
}
