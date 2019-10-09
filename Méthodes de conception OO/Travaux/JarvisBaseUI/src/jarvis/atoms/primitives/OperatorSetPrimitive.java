package jarvis.atoms.primitives;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.ListAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.interpreter.JarvisInterpreter;

public class OperatorSetPrimitive extends PrimitiveOperationAtom {

	@Override
	protected AbstractAtom execute(JarvisInterpreter ji, ObjectAtom self) {


		ListAtom members = (ListAtom) self.getJarvisClass().getValues().get(ObjectAtom.ATTRIBUTE_FIELD);

		// Vérifie si c'est un attribut
		int pos = members.find(ji.getArg());
		self.setAttribute(pos, ji.getArg());

		return self;

	}

	@Override
	protected void init() {
		argCount = 2;

	}

	@Override
	public String makeKey() {
		return "OperatorSetPrimitive";
	}

}
