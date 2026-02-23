package pkgDungeonDiver;

public class Equipment {
	//default variables
	public String name;
	public double HPModifier = 0;
	public double ATKModifier = 0;
	public double DEFModifier = 0;
	public String equipPosition;
	public Skill uniqueSkill = null;
	public int equipmentCost;
	
	public Equipment() {
		//generate a random equipPosition
		equipPosition = DungeonDiverRunner.equipmentPositions.get((int)(Math.random()*DungeonDiverRunner.equipmentPositions.size()));
		
		new Equipment(equipPosition);
	}
	
	public Equipment(String equipPosition) {
		//randomize stats
		if (equipPosition.equals("WEAPON") || equipPosition.equals("CHARM")) {//attack piece
			//attack stats
			ATKModifier = Math.random()*20;
			HPModifier = 0;
			DEFModifier = 0;
		} else {//def piece
			//def stats
			ATKModifier = 0;
			HPModifier = Math.random()*20;
			DEFModifier = Math.random()*30;
		}
		
		//randomize cost depending on value of stats
		equipmentCost = (int) (ATKModifier*2 + (HPModifier + DEFModifier)*1.5);
		
		//choose a random descriptor 
		String randDesc = DungeonDiverRunner.equipmentDescriptors.get((int)(Math.random()*DungeonDiverRunner.equipmentDescriptors.size()));
		name = randDesc + " " + equipPosition;
	}
}