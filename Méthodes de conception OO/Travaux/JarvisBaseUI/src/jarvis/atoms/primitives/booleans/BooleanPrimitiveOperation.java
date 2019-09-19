package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;

public abstract class BooleanPrimitiveOperation extends PrimitiveOperationAtom {

	/* 
	 * Le nombre d'argument pour toutes les opérations dérivées de celle-ci sera
	 * de 2 (self + 1 autre). Il devrait donc rester un argument dans la file à cette étape-ci
	 * Ne supporte que des opérations binaires (2 arguments).
	 */
	protected void init() {
		argCount = 1;
	}
	
	//Cette méthode ne fera que la partie spécifique à chaque opération (voir les sous-classes). 
	//protected abstract AbstractAtom calculateResult(JarvisInterpreter ji,BoolAtom val1, BoolAtom val2);
	
	protected abstract AbstractAtom calculateResult(JarvisInterpreter ji,BoolAtom val1);
	
	//Cette méthode fait quelques vérifications générales avant d'appeler calculateResult, qui fait l'opération réelle.	
	@Override
	protected AbstractAtom execute(JarvisInterpreter ji,ObjectAtom self) {

		
		//Ici, on peut assumer que l'objet qui a reçu le message (self) est un int et possède donc
		//le champ "value". 
		BoolAtom val1 = (BoolAtom) self.message("value");
		AbstractAtom arg2=ji.getArg();


		//Le second argument est pris de la file d'arguments. Il peut avoir n'importe quelle forme.
		BoolAtom val2;
		
			
		
		 if(arg2 instanceof BoolAtom)
		{
			//Si l'argument est de type IntAtom, alors l'opération se fera directement avec lui.
			val2=(BoolAtom)arg2;
			
		}
		else
		{
			//Sinon, il faut vérifier si on a obtenu un objet jarvis.
			if(arg2 instanceof ObjectAtom){
				
				//Si c'est un objet de type int, alors il devrait avoir un champ "value".
				AbstractAtom res = ((ObjectAtom) arg2).message("value");
				
				//Si le champ "value" existe et s'il contient bien un intAtom, l'opération se fera avec lui.
				if(res instanceof BoolAtom){
					val2 = (BoolAtom)res;
				}
				//Si le champ n'existe pas ou qu'il ne contient pas le bon type d'atome, alors on ne peut pas continuer				
				else throw new IllegalArgumentException(makeKey()+", argument 2: object does not contain a \"value\" field of type IntAtom. Class = " + ((ObjectAtom)arg2).findClassName(ji)+", object contents = ["+arg2.makeKey()+"]");
			}
			//Si ce n'est pas un IntAtom ou un ObjectAtom, ça ne peut pas être le bon type d'argument.
			else throw new IllegalArgumentException(makeKey()+", argument 2: wrong atom type " + arg2.getClass().getName()+", value = "+arg2.makeKey());
			
		}
		//Procède finalement au calcul spécifique à chaque primitive concernant 2 int (voir classes filles).	
		return calculateResult(ji, val1);
	}
}
