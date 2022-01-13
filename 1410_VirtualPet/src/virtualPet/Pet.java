package virtualPet;

import java.io.Serializable;

/** 
 * Pet superclass for all of the pets for our virtual pet game.
 * @author Nathan J, all methods unless otherwise stated.
 * @author Rianna M, if stated.
 * <p>- 11/20/21: Rianna added abstract methods. String returns were also added to a few methods.</p>
 * <p>- 11/29/21: Rianna turned play into an abstract method.</p>
 * <p>- 12/01/21: Rianna added doc comments.</p>
 */
public abstract class Pet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7376240750408284001L;
	
	protected String name;
	protected int color;
	protected int health;
	protected int happiness;
	
	protected Pet(int color, String name)
	{
		this.name = name;
		this.color = color;
		this.health = 90;
		this.happiness = 90;
		
		if (this.color > 3)
		{
			this.color = this.color % 3;
			this.color++;
		}
	}
	
	/**
	 * @author Rianna M
	 * @return the way the pet would speak.
	 */
	public abstract String speak();
	
	/**
	 * @author Rianna M
	 * @return description of pet's trick.
	 */
	public abstract String trick();
	
	/**
	 * @author Rianna M
	 * @return description of pet's special action.
	 */
	public abstract String specialAction();
	
	/**
	 * 
	 * @return description of play time with pet.
	 */
	public abstract String play();
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public int color()
	{
		return color;
	}
	
	public int health()
	{
		return health;
	}
	
	public int happiness()
	{
		return happiness;
	}
	
	/**
	 * 
	 * @return description of petting your pet.
	 */
	public String petThePet()
	{
		happyBoost();
		return String.format("You stroke %s , and they look very happy!", name);
	}
	
	/**
	 * 
	 * @return description of feeding your pet.
	 */
	public String meal()
	{
		healthBoost();
		return String.format("You feed %s their favorite meal, and they devour it with gusto.", name);
	}
	
	protected void happyBoost()
	{
		if(happiness < 90)
		{
			happiness = happiness + 30;
		}
	}
	
	protected void healthBoost()
	{
		if(health < 90)
		{
			health = health + 30;
		}
	}

	
	public void incrementColor()
	{
		if(color == 3)
			color = 1;
		else
		{
			color = color + 1;
		}
	}
	
	/**
	 * @author Nathan J added 11/29/21.
	 * <p>- decreases a pets health.</p>
	 */
	public void healthDrop()
    {
        if(health > 0)
        {
            health = health - 30;
        }
    }
	
	/**
	 * @author Nathan J added 11/29/21.
	 * <p>- decreases a pets happiness.</p>
	 */
	public void happyDrop()
    {
        if(happiness > 0)
        {
            happiness = happiness - 30;
        }
    }
	
}
