package virtualPet;
/**
 * 
 * @author Rianna M
 *
 */
public class Snake extends Pet
{
	boolean onShoulder = false;
	
	Snake(int color, String name)
	{
		super(color, name);
	}
	

	@Override
	public String speak() {
		super.happyBoost();
		return String.format(this.getName() + " hisses and flicks their tounge in and out.");
		
	}

	@Override
	public String trick() {
		String snakeReturnString = new String();
		super.happyBoost();
		if(onShoulder) {
			snakeReturnString = String.format("You set " + this.getName() + " down from your shoulder, and they slither around.");
			this.onShoulder = false;
		}
		
		else if(!onShoulder) {
			snakeReturnString = String.format("You pick up " + this.getName() + " and place them on your shoulder.");
			this.onShoulder = true;
		}
		return snakeReturnString;
	}

	@Override
	public String specialAction() {
		super.happyBoost();
		String snakeReturnString = new String();
		if(onShoulder) {
			snakeReturnString = String.format(this.getName() + " coils around your arm, giving you a nice hug!");
		}
		
		else if(!onShoulder) {
			snakeReturnString = String.format(this.getName() + " coils into a little ball. They look snug and happy.");
		}
		return snakeReturnString;
	}


	@Override
	public String play() {
		super.happyBoost();
		super.healthBoost();
		return String.format("You give " + this.getName() + " some live prey to catch. They slither down to catch it! Yummy~");
	}
}
