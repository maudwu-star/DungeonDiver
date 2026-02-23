package pkgDungeonDiver;

public class Enemy extends Unit{	
	//default constructor
	public Enemy() {
		name = "default enemy (you gon goofed)";
		maxhp = ((int)(Math.random()*10)+30)/10.0;
		currhp = maxhp;
		atk = ((int)(Math.random()*5)+10)/10.0;
		def = ((int)(Math.random()*10)+2)/10.0;
		imgFile = "/Images/GreenSlime.jpeg";
	}
	
	//specific enemy
	public Enemy(String enemyType) {
		//use default constructor in case enemy entered is nonexistant
		this();
		
		//use enemy constructor depending on type
		if (DungeonDiverRunner.EnemyTypes.contains(enemyType)) {//checking that the input exists 
			name = enemyType;
			//initiating stats determined by enemy type
			if (enemyType.equals("Slime")) {//slime stats
				maxhp = ((int)(Math.random()*10)+200)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*5)+20)/10.0;
				def = ((int)(Math.random()*10)+25)/10.0;
				imgFile = "/Images/GreenSlime.jpeg";
			}
			if (enemyType.equals("Skeleton")) {//skeleton stats
				maxhp = ((int)(Math.random()*50)+100)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*50)+40)/10.0;
				def = ((int)(Math.random()*20)+10)/10.0;
				imgFile = "/Images/SkeletonStanding.jpg";
			}
			if (enemyType.equals("Fairy")) {//fairy stats
				maxhp = ((int)(Math.random()*100)+25)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*75)+20)/10.0;
				def = ((int)(Math.random()*50))/10.0;
				imgFile = "/Images/FairyEnemy.jpg";
			}
			if (enemyType.equals("Shroom")) {//shroom stats
				maxhp = ((int)(Math.random()*100)+50)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*20)+20)/10.0;
				def = ((int)(Math.random()*25)+50)/10.0;
				imgFile = "/Images/StandingMushroom.jpg";
			}
			if (enemyType.equals("Hog")) {//hog stats
				maxhp = ((int)(Math.random()*50)+120)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*25)+20)/10.0;
				def = ((int)(Math.random()*40)+20)/10.0;
				imgFile = "/Images/SmallHog.jpg";
			}
			if (enemyType.equals("Boss")) {//boss stats
				maxhp = ((int)(Math.random()*120)+500)/10.0;
				currhp = maxhp;
				atk = ((int)(Math.random()*150)+300)/10.0;
				def = ((int)(Math.random()*110)+250)/10.0;
				imgFile = "/Images/SkeletonStanding.jpg";
			}
		} 
		
		//scale enemy stats based on current char stats
		//def adjustments based on player atk
		int maxSafeDef = (int)(DungeonDiverRunner.playerChar.getATK() * 0.8);
		if (def > maxSafeDef) {
		    def = maxSafeDef;
		}
		if (def < 0) def = 0;
		//hp adjustments based on player atk to prevent easy one shots
		double maxPlayerDamage = DungeonDiverRunner.playerChar.getATK()*2;
		while (maxPlayerDamage - def >= maxhp*1.2) {
			//boost hp & def
			maxhp *= 1.1 + (Math.random() * 0.2); 
			def *= 1.05;
		    //prevent infinite loops
		    if (maxhp > 10000) break; 
		}
		//update hp
		currhp = maxhp;
	}
}
