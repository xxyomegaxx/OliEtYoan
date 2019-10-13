package jarvis.atoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import jarvis.interpreter.JarvisInterpreter;

public class ListAtom extends AbstractAtom{
	
	
	
	//todo: JarvisAtom seulement
	private ArrayList<AbstractAtom> data;
	
	
	
	public ListAtom()
	{
		data=new ArrayList<AbstractAtom>();		
	}
	public ListAtom(ArrayList<AbstractAtom> values)
	{
		data=new ArrayList<AbstractAtom>();
		data.addAll(values);
	}
	public ListAtom(ListAtom source)
	{
		data=new ArrayList<AbstractAtom>();
		data.addAll(source.data);
	}
	
	public static ListAtom read(JarvisInterpreter ji)
	{
		ListAtom list = new ListAtom();
		
		CommandAtom.setDontPut(true);
		CommandAtom input = ji.readCommandFromInput();
		while (!input.isEndOfList())
		{	
			CommandAtom.setDontInterpret(true);	
			AbstractAtom atom = input.interpretNoPut(ji);
			CommandAtom.setDontInterpret(false);	
			list.data.add(atom);
			input = ji.readCommandFromInput();
		}
		CommandAtom.setDontPut(false);
		
		return list;
	}
	
	
	@Override
	public AbstractAtom interpretNoPut(JarvisInterpreter ji) {
		
		
		return this;
	}
	
	
	public int indexOf(Object obj)
	{
		return data.indexOf(obj);
	}

	
	
	public int size()
	{
		return data.size();
	}
	@Override
	public String makeKey() {
		String s="(";
		for (AbstractAtom obj : data) {
			s+=obj.makeKey()+",";			
		}
		s+=")";
		return s;
	}
	public int find(AbstractAtom selector) {
		
		int i=0;
		for(AbstractAtom atom : data)
		{
			if(atom.makeKey().compareTo(selector.makeKey())==0)
			{
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public AbstractAtom get(int pos)
	{
		if(pos<data.size())
		{
			return data.get(pos);
		}
		else
		{
			throw new NoSuchElementException("ListAtom: no such element: "+pos);
		}
	}
	
	public void add(AbstractAtom atom)
	{
		data.add(atom);
	}
	public void addAllVals(ListAtom source)
	{
		for(int i =0;i<source.data.size();i++)
		{
			
			if((boolean)((String)((StringAtom)source.data.get(i)).getValue()).isEmpty() == false)
			{
				data.add(source.data.get(i));
			}
			
		}
	}

	
	

}
