package commands;

import java.util.ArrayList;

public class CommandLog {
	
	ArrayList<EditDocumentCommand> list;
	
	public CommandLog()
	{
		list = new ArrayList<EditDocumentCommand>();
	}
	
	public void add(EditDocumentCommand c)
	{
		list.add(c);
	}
	
	public EditDocumentCommand removeLast()
	{
		if(list.size()>0) return list.remove(list.size()-1);
		else return null;
		
	}
	

}
