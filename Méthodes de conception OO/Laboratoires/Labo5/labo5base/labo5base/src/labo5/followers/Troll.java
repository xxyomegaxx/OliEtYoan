package labo5.followers;

import labo5.Labo5Main;
import labo5.Util;
import labo5.ui.MessageView;

public class Troll implements Follower{
	
	protected MessageView viewport;
	private String name;
	String[] dic;
	String reply;

	public Troll(Labo5Main ui,String n,int version){
		
		name=n;
		viewport = new MessageView(ui,name+" news feed");	
		if(version ==1)
		{
			dic = Util.TROLL_DICTIONNARY1;
			reply = Util.TROLL_REPLY1;
					
		}
		else if(version ==2)
		{
			dic = Util.TROLL_DICTIONNARY2;
			reply = Util.TROLL_REPLY2;
					
		}
	}

	@Override
	public void update(String name, String message) {
		if(Util.contains(message, dic))
		{
			viewport.appendMessage(message + reply);
		}
		
	}
	

}
