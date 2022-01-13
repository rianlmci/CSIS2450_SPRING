package virtualPet;
/**
 * 
 * @author Rianna M
 *
 */
public class Dog extends Pet
{
	boolean hasBall = false;
	
	Dog(int color, String name)
	{
		super(color, name);
	}
	

	@Override
	public String speak() {
		super.happyBoost();
		return String.format(this.getName() + " gives a hearty woof.");
		
	}

	@Override
	public String trick() {
		super.happyBoost();
		String dogReturnString = new String();
		if(hasBall) {
			dogReturnString = String.format(this.getName() + " returns the ball to you. It has a little drool on it though.");
			this.hasBall = false;
		}
		
		else if(!hasBall) {
			dogReturnString = String.format("You toss the rubber ball for " + this.getName() + " and they catch it midair in their mouth. GOOD DOG!");	
			this.hasBall = true;
		}
		return dogReturnString;
	}

	@Override
	public String specialAction() {
		super.healthBoost();
		super.happyBoost();
		return String.format(this.getName() + " rolls over and shows their belly. You give it lots of pets. " + this.getName() + " is SO happy!");
	}


	@Override
	public String play() {
		super.happyBoost();
		super.healthBoost();
		return String.format("You give " + this.getName() + " a kong with peanutbutter. They'll be busy for awhile.");
	}
}
