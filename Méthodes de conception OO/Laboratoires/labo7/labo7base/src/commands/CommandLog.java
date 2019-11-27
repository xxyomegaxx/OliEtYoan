package commands;

import java.util.Stack;

public class CommandLog {
	
	Stack<EditDocumentCommand> list;
	
	public CommandLog()
	{
		list = new Stack<EditDocumentCommand>();
	}
	
	public void add(EditDocumentCommand c)
	{
		list.push(c);
	}
	
	public EditDocumentCommand removeLast()
	{
		System.out.println(list);
		if(list.size()>0) {
			return list.pop();
		}
		else return null;
	}
	

}
