package labo5.celebrities;

import java.util.ArrayList;

import labo5.followers.Follower;

public abstract class Sujet {
	ArrayList<Follower>  followerList = new ArrayList<Follower>();
	public void attach(Follower follower)
	{
		followerList.add(follower);
	}
	
	public void notifyFollowers(String message)
	{
		for(Follower each : followerList)
		{
			each.update(message);
		}
	}

}
