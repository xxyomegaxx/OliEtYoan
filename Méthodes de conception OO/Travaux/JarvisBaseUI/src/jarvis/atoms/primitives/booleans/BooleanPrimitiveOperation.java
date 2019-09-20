package jarvis.atoms.primitives.booleans;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;

public abstract class BooleanPrimitiveOperation extends PrimitiveOperationAtom {
		
		/* 
		 * Le nombre d'argument pour toutes les op�rations d�riv�es de celle-ci sera
		 * de 2 (self + 1 autre). Il devrait donc rester un argument dans la file � cette �tape-ci
		 * Ne supporte que des op�rations binaires (2 arguments).
		 */
		protected void init() {
			argCount = 1;
		}
		
		//Cette m�thode ne fera que la partie sp�cifique � chaque op�ration (voir les sous-classes). 
		protected abstract AbstractAtom calculateResult(JarvisInterpreter ji,BoolAtom val1, BoolAtom val2);
		
		//Cette m�thode fait quelques v�rifications g�n�rales avant d'appeler calculateResult, qui fait l'op�ration r�elle.	
		@Override
		protected AbstractAtom execute(JarvisInterpreter ji,ObjectAtom self) {

			
			//Ici, on peut assumer que l'objet qui a re�u le message (self) est un int et poss�de donc
			//le champ "value". 
			BoolAtom val1 = (BoolAtom) self.message("value");
			
			//Le second argument est pris de la file d'arguments. Il peut avoir n'importe quelle forme.
			AbstractAtom arg2=ji.getArg();
			BoolAtom val2;
			
			if(arg2 instanceof BoolAtom)
			{
				//Si l'argument est de type IntAtom, alors l'op�ration se fera directement avec lui.
				val2=(BoolAtom)arg2;
				
			}
			else
			{
				//Sinon, il faut v�rifier si on a obtenu un objet jarvis.
				if(arg2 instanceof ObjectAtom){
					
					//Si c'est un objet de type int, alors il devrait avoir un champ "value".
					AbstractAtom res = ((ObjectAtom) arg2).message("value");
					
					//Si le champ "value" existe et s'il contient bien un intAtom, l'op�ration se fera avec lui.
					if(res instanceof BoolAtom){
						val2 = (BoolAtom)res;
					}
					//Si le champ n'existe pas ou qu'il ne contient pas le bon type d'atome, alors on ne peut pas continuer				
					else throw new IllegalArgumentException(makeKey()+", argument 2: object does not contain a \"value\" field of type IntAtom. Class = " + ((ObjectAtom)arg2).findClassName(ji)+", object contents = ["+arg2.makeKey()+"]");
				}
				//Si ce n'est pas un IntAtom ou un ObjectAtom, �a ne peut pas �tre le bon type d'argument.
				else throw new IllegalArgumentException(makeKey()+", argument 2: wrong atom type " + arg2.getClass().getName()+", value = "+arg2.makeKey());
				
			}
			//Proc�de finalement au calcul sp�cifique � chaque primitive concernant 2 int (voir classes filles).	
			return calculateResult(ji, val1, val2);
		}
}
