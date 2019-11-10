package labo5.celebrities;

import java.util.ArrayList;

import labo5.followers.Follower;
import labo5.followers.VetoFollower;

public abstract class Sujet {
	protected String name;
	ArrayList<Follower>  followerList = new ArrayList<Follower>();
	ArrayList<VetoFollower>  vetoFollowerList = new ArrayList<VetoFollower>();
	public void attach(Follower follower)
	{
		followerList.add(follower);
	}
	public void attachVeto(VetoFollower follower)
	{
		vetoFollowerList.add(follower);
	}
	
	public boolean notifyFollowers(String message)
	{
		boolean value = true;
		for(VetoFollower vet : vetoFollowerList)
		{
			value = value && vet.checkVeto(message);
		}
		if(value)
		{
			for(Follower each : followerList)
			{
				each.update(name,message);
			}
			return true;
		}
		else return false;
	}

}
