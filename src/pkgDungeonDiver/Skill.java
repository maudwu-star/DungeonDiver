package pkgDungeonDiver;

public class Skill {	
	//global variables
	public static double multiplier;
	public static int cd;
	public static String name;
	
	//full constructor 
	private Skill(String name, double multiplier, int cd) {
		this.name = name;
		this.multiplier = multiplier;
		this.cd = cd;
	}
	
	//Skill constructors
	public static Skill createBasic() {
		return new Skill("Slash", 1, 0);
	}
	
	//default
	public Skill() {
		name = "default";
		cd = 2;
		multiplier = 1;
	}
}
