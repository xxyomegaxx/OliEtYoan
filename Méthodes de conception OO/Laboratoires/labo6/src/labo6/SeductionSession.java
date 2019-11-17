package labo6;

import javafx.collections.ListChangeListener;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.Picture.PictureKey;
import labo6.database.TextMessage.TextKey;
import labo6.session.Session;

public class SeductionSession extends Session{

	public SeductionSession(Labo6Main l, User u) {
		super(l, u);		
	}
	
	@Override
	public String generateAnswer(TextList li) {
		return li.random().getMessage();
	}

//	@Override
//	protected void initStudentList() {
//		list=TextDatabase.getAllMessages();
//		list.keep(TextKey.isSeductive, true);
//		setTextList(list);
//	}
	@Override
	protected TextList getSuitableMessages() {
		TextList list = new TextList();
		list = TextDatabase.getAllMessages();
		list.keep(TextKey.isSeductive, true);
		return list;
	}

	@Override
	protected PictureList getSuitablePictures() {
		PictureList picList = new PictureList();
		picList = PictureDatabase.getAllPictures();
		picList.keep(PictureKey.isSeductive, true);
		return picList;
	}
	
	@Override
	protected String generateGreeting(TextList li) {

		li.keep(TextKey.isGreeting,true);
		
		return li.random().getMessage();
	}
}
