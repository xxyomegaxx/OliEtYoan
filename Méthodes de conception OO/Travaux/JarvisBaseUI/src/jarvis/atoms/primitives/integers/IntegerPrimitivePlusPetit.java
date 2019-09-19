package jarvis.atoms.primitives.integers;

import java.util.ArrayList;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

//OPERATIONSPRIMITIVES
/*
* Cette classe implante la partie de l'op�ration primitive sp�cifique � la soustraction.
* 
*/

public class IntegerPrimitivePlusPetit extends IntegerPrimitiveOperation {
	
	@Override
	public String makeKey() {

		return "integerPrimitivePlusPetit";
	}

	@Override
	protected AbstractAtom calculateResult(JarvisInterpreter ji, IntAtom val1, IntAtom val2) {
		
		// C'est ici que l'op�ration r�elle a lieu
		boolean result = val1.getValue() < val2.getValue();
		
		// Ici, construit un objet int manuellement
		// � noter, on retourne un objet de type int, pas un atome de type int.
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();
		data.add(new BoolAtom(result));

		return new ObjectAtom(((ObjectAtom) ji.getEnvironment().get("bool")), data, ji);
	}
}
