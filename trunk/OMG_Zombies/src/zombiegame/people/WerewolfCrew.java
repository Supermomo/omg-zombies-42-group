package zombiegame.people;

public class WerewolfCrew extends Werewolf
{

	private int CrewMembers;
	
	public WerewolfCrew(String name, int healthPoints,int members) 
	{
		super(name, healthPoints);
		this.CrewMembers = members;
	}
	
	

}
