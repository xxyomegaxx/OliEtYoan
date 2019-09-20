package jarvis.atoms.primitives.booleans;

import java.util.ArrayList;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.BoolAtom;
import jarvis.atoms.IntAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.primitives.PrimitiveOperationAtom;
import jarvis.interpreter.JarvisInterpreter;

public class BooleanPrimitiveNot extends PrimitiveOperationAtom {

	@Override
	protected AbstractAtom execute(JarvisInterpreter ji, ObjectAtom self) {
		//Ici, on peut assumer que l'objet qui a re�u le message (self) est un bool et poss�de donc
				//le champ "value". 
				BoolAtom val1 = (BoolAtom) self.message("value");
									
				return calculateResult(ji, val1);
	}

	@Override
	protected void init() {
		argCount = 0;
		
	}

	@Override
	public String makeKey() {
		return "BooleanPrimitiveNot";
	}
	
	protected AbstractAtom calculateResult(JarvisInterpreter ji, BoolAtom val1) {

		// C'est ici que l'op�ration r�elle a lieu
		boolean result = !val1.getValue();

		// Ici, construit un objet int manuellement
		// � noter, on retourne un objet de type bool, pas un atome de type
		// bool.
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();
		data.add(new BoolAtom(result));

		return new ObjectAtom(((ObjectAtom) ji.getEnvironment().get("bool")), data, ji);
	}


	
	
}
