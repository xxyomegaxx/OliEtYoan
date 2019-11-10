package labo5.followers;

import labo5.Util;

public class PressAgent implements VetoFollower{
	private String name;
	String [] dict;
	
public PressAgent(String n,int dic){
		
		name=n;	
		if(dic==1)
		{
			dict = Util.TROLL_DICTIONNARY1;
		}
		else if(dic==2)
		{
			dict = Util.TROLL_DICTIONNARY2;
		}
	}

	@Override
	public boolean checkVeto(String message) {
		if(Util.contains(message, dict))
		{
			return false;
		}
		return true;
	}

}
