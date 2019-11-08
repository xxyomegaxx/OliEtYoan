package labo5.followers;

import labo5.Labo5Main;
import labo5.Util;
import labo5.celebrities.Sujet;
import labo5.ui.MessageView;

public class Hater extends Sujet implements Follower{

	protected MessageView viewport;
	private String name;
	String status = "Poubelle";

	@Override
	public void update(String namee, String message) {
		String tempMess = Util.getRandomString(Util.HATER_DICTIONNARY);
		viewport.appendMessage(Util.personnalize(namee,tempMess ));
		setStatus(tempMess);
		
	}
	
public Hater(Labo5Main ui,String n){
		
		name=n;
		viewport = new MessageView(ui,name+" news feed");		
	}
protected void setStatus(String ns) {
	status = ns;
	notifyFollowers(ns);

}


}
