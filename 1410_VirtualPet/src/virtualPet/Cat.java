package virtualPet;
/**
 * 
 * @author Nathan J
 * abstract method overrides by
 * @author Rianna M
 *
 */
public class Cat extends Pet
{
	Cat(int color, String name)
	{
		super(color, name);
	}
	

	@Override
	public String speak() {
		super.happyBoost();
		return String.format(this.getName() + " meows sweetly.");
		
	}

	@Override
	public String trick() {
		super.happyBoost();
		return String.format(this.getName() + " chases a laser pointer.");
	}

	@Override
	public String specialAction() {
		super.healthBoost();
		super.happyBoost();
		return String.format(this.getName() + " catches a mouse. Eek!");
	}
	
	//play with yarn method changed to abstract play method by Rianna
	@Override
	public String play() {
		super.happyBoost();
		super.healthBoost();
		return "Your cat plays with yarn and is happy.";
		
	}
}
