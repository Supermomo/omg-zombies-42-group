package zombiegame.people;

import zombiegame.engine.Field;

public class WerewolfCrew extends Werewolf
{

	private int CrewMembers;
	
	public WerewolfCrew(String name, int healthPoints,int members) 
	{
		super(name, healthPoints);
		this.CrewMembers = members;
	}
	
	public void addMember()
	{
	    this.CrewMembers++;
	}

	protected void attack(Character c) 
	{
        super.attack(c);
        //TODO modif ici : crewMembers-1 (car -15 deja fait dans le super)
        c.reduceHealthPoints(15*(CrewMembers-1));
	}
	
	public boolean isWerewolfCrew()
	{
	    return true;
	}
	
	public int getCrewMembers()
	{
	    return CrewMembers;
	}
	
	public void encounterCharacter(Character c, Field field) 
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
