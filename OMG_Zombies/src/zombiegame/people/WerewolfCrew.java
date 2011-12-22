package zombiegame.people;

import zombiegame.engine.Field;

public class WerewolfCrew extends Werewolf
{

	private int CrewMembers;
	
	public WerewolfCrew(String name, int healthPoints,int members,Field field) 
	{
		super(name, healthPoints,field);
		this.CrewMembers = members;
	}
	
	public void addMember()
	{
	    this.CrewMembers++;
	}

	protected void attack(Character c) 
	{
        super.attack(c);
        c.reduceHealthPoints(15*CrewMembers);
	}
	
	public boolean isWerewolfCrew()
	{
	    return true;
	}
	
	public int getCrewMembers()
	{
	    return CrewMembers;
	}
	
	public void encounterCharacter(Character c) 
    {
        if(c.isWerewolf())
        {
            if( this.getCrewMembers() < 5)
            {
                ((WerewolfCrew)c).addMember();
                field.clear(c.location);
            }
            if(c.isWerewolfCrew())
            {
               attack(c);
            }
        }
        else
        {
            if(c.isHuman() && (c.getHealthPoints() <= 25))
            {
                this.bite((Human)c);
            }
            else
            {
                attack(c);
                if(c.isHuman() && (c.getHealthPoints() <= 25))
                {
                    this.bite((Human)c);
                }
            }
                
        }
    }
}
