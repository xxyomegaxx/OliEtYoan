package jarvis.interpreter;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import jarvis.atoms.AbstractAtom;
import jarvis.atoms.CommandAtom;
import jarvis.atoms.DictionnaryAtom;
import jarvis.atoms.ListAtom;
import jarvis.atoms.NullAtom;
import jarvis.atoms.ObjectAtom;
import jarvis.atoms.StringAtom;
import jarvis.atoms.primitives.OperatorNewPrimitive;
import jarvis.atoms.primitives.booleans.BooleanPrimitiveAnd;
import jarvis.atoms.primitives.booleans.BooleanPrimitiveNot;
import jarvis.atoms.primitives.booleans.BooleanPrimitiveOr;
import jarvis.atoms.primitives.integers.IntegerPrimitiveAdd;
import jarvis.atoms.primitives.integers.IntegerPrimitiveEquals;
import jarvis.atoms.primitives.integers.IntegerPrimitiveMultiply;
import jarvis.atoms.primitives.integers.IntegerPrimitiveSubtract;
import jarvis.atoms.primitives.integers.IntegerPrimitivePlusPetit;
import jarvis.atoms.primitives.integers.IntegerPrimitivePlusGrand;



import jarvis.exceptions.UndefinedSymbolException;
import jarvis.ui.MainWindow;

public class JarvisInterpreter {

	
	// Rï¿½fï¿½rence ï¿½ l'environnement courant
	private JarvisEnvironment environment;

	// Rï¿½fï¿½rence ï¿½ l'interface graphique
	private MainWindow ui;

	// Dï¿½termine si les valeurs lues par l'interprï¿½teur sont rï¿½affichï¿½es sur la
	// console
	private boolean echo;
	// Dï¿½termine si l'invite de commande doit apparaï¿½tre
	private boolean prompt;

	//Dï¿½termine si on avance pas ï¿½ pas au lieu d'exï¿½cuter directement.
	private boolean step;
	
	//Objet utilisï¿½ pour la synchro avec l'interface usager.
	private Object uiLock;
	
	// File contenant les arguments pour le prochain envoi de message.
	// Les fonctions et closures peuvent avoir l'effet de bord de mettre des
	// atomes dans cette file.
	// C'est le mï¿½canisme de retour et de passage de paramï¿½tres.
	private Queue<AbstractAtom> argQueue;

	/*
	 * Pile contenant des commandes ï¿½ ï¿½valuer. Utilisï¿½e lorsqu'une closure est
	 * ï¿½valuï¿½eou qu'un fichier est chargï¿½ avec la commande CMD_LOAD.Les lignes
	 * de texte qui contiennent plus qu'une valeur placent les valeurs suivantes
	 * sur cette pile.Cette pile est doit ï¿½tre vide avant de lire des commandes
	 * dans un fichier ou sur la ligne de commande.
	 */
	private Stack<String> evalStack;

	// Indique qu'une errerur est survenue plus tï¿½t (outputError).
	private boolean hasCrashed;

	// Indique que la commande CMD_QUIT a ï¿½tï¿½ interprï¿½tï¿½e.
	private boolean hasQuit;
	
	private boolean fromFile;

	// Le flot d'entrï¿½e courant (fichier ou clavier)
	private BufferedReader reader;

	/*
	 * Constructeur. Au dï¿½marrage, un flot d'entrï¿½e doit ï¿½tre spï¿½cifiï¿½. Lorsque
	 * c'est un fichier, l'echo et le prompt devraient ï¿½tre mis ï¿½ false.
	 */
	public JarvisInterpreter(BufferedReader r, boolean e, boolean p,boolean f) {

		fromFile=f;
		echo = e;
		prompt = p;
		reader = r;
		step=false;
		uiLock = new Object();
		init();

	}

	/*
	 * Initialisation de l'interprï¿½teur.
	 */

	private void init() {

		ui = null;
		hasCrashed = false;
		hasQuit = false;

		// Crï¿½ation de l'environnement racine. Il contient uniquement les
		// fonctions tricheuses.
		environment = new JarvisEnvironment(this);

		// Ajout des fonctions tricheuses (primitives codï¿½es en Java).
		addCheaterCode();

		// Crï¿½ation de l'environnement d'interprï¿½tation de dï¿½part. Son parent
		// est l'environnement de base.
		environment = new JarvisEnvironment(this, environment);

		// Initialisation de la file d'arguments
		argQueue = new LinkedList<AbstractAtom>();

		// Initialisation de la pile d'ï¿½valuation
		evalStack = new Stack<String>();

		// Crï¿½ation de la classe de base.
		createClassClass();
	}

	// OPERATIONSPRIMITIVES
	// MUTATEUR
	/*
	 * Cette fonction ajoute les primitives du langage dans l'environnement. Les
	 * primitives sont des fonctions qui s'exï¿½cutent en Java. Elles sont cachï¿½es
	 * dans un environnement parent ï¿½ celui dans lequel l'interprï¿½teur dï¿½marre.
	 */
	private void addCheaterCode() {

		environment.put("_integerAddPrimitive", new IntegerPrimitiveAdd());
		environment.put("_integerSubtractPrimitive", new IntegerPrimitiveSubtract());
		environment.put("_integerMultiplyPrimitive", new IntegerPrimitiveMultiply());
		environment.put("_integerEqualsPrimitive", new IntegerPrimitiveEquals());
		environment.put("_operatorNewPrimitive", new OperatorNewPrimitive());
		environment.put("_integerPlusPetitPrimitive", new IntegerPrimitivePlusPetit());
		environment.put("_integerPlusGrandPrimitive", new IntegerPrimitivePlusGrand());
		
		environment.put("_booleanNotPrimitive", new BooleanPrimitiveNot());
		environment.put("_booleanAndPrimitive", new BooleanPrimitiveAnd());
		environment.put("_booleanOrPrimitive", new BooleanPrimitiveOr());


	}

	// HÉRITAGE
	// VARIABLESCLASSE
	/*
	 * Cette fonction crée la classe des classes. Celle-ci spécifie qu'une
	 * classe a deux champs: attributes et methods. Pour créer une classe
	 * valide, attributes doit étre une liste de StringAtom et methods doit être
	 * un dictionnaire de paires StringAtom:JarvisAtom.
	 * 
	 * Cette classe ne contient qu'une méthode: la fonction tricheuse
	 * OperatorNewPrimitive.
	 * 
	 * Cette classe ne devrait pas être cod.e en Jarvis. Un changement dans
	 * l'organisation des membres de cette classe implique aussi potentiellement
	 * un changement de l'algorithme d'interprétation de messages se trouvant
	 * dans ObjectAtom (JarvisObjet.message( ... )). De plus, cette classe doit
	 * être sa propre classe. Le symbole Class ne peut pas être résolu par
	 * l'interpreteur avant d'exister! La référence à la classe doit également
	 * demeurer un lien direct vers l'objet-classe en question. Si c'était
	 * uniquement un symbole à interpréter, on pourrait définir avec le même nom
	 * qu'une classe et ainsi empêcher les objets de retrouver leur classe.
	 */

	private void createClassClass() {

		/*
		 * Création de la liste de membres. Un objet instancié par cette classe
		 * comprend deux membres, la liste des attributs, ainsi que le
		 * dictionnaire des méthodes. ATTENTION! Rien ne garantit qu'une
		 * instance sera créée avec une liste et un dictionnaire comme
		 * arguments! Lisez bien les définitions des classes de base
		 * (basictypes.txt) pour avoir des exemples. Si vous modifiez
		 * l'organisation de Class, il faut aussi modifier comment instancier
		 * des classes. Il est possible que vous deviez changer toutes les
		 * définitions de classes de basictypes.txt.
		 */
		ListAtom members = new ListAtom();
		members.add(new StringAtom("attributes"));
		members.add(new StringAtom("methods"));
		members.add(new StringAtom("SuperClass"));

		HashMap<String, AbstractAtom> m = new HashMap<String, AbstractAtom>();
		DictionnaryAtom methods = new DictionnaryAtom(m);

		/*
		 * Cette classe ne contient qu'une méthode, new. Celle-ci fait usage de
		 * la fonction tricheuse OperatorNewPrimitive. Comme un objet résultant
		 * de !(Class new) est instance de Class, il supportera nécessairement
		 * new aussi (C'est donc une classe). Class supporte new parce qu'elle
		 * est instance d'elle-même.
		 */
		methods.put("new", new OperatorNewPrimitive());

		/*
		 * Création d'un objet qui sera instance de Class Ses données seront la
		 * liste des attributs dictionnaire de méthodes créés plus haut.
		 */
		NullAtom superClass = new NullAtom();
		
		ArrayList<AbstractAtom> data = new ArrayList<AbstractAtom>();

		
		
		data.add(members);
		data.add(methods);
		data.add(superClass);

		ObjectAtom ClassClass = new ObjectAtom(null, data, this);

		/*
		 * Cet objet contient la définition d'une classe et est instance de
		 * lui-même.
		 */
		ClassClass.setClass(ClassClass);

		/*
		 * Ajout de la classe de base dans l'environnement. Elle s'appelle
		 * Class, bien sûr. L'appeler Hippopotamme n'aurait pas été très
		 * pratique.
		 */
		environment.put("Class", ClassClass);

	}

	/*
	 * Cette fonction implante une partie de la boucle d'interprétation. Elle
	 * lit une commande à partir du flot d'entrée et l'interprète. Les
	 * exceptions lancées un peu partout dans l'interpréteur sont attrappées
	 * ici. Lorsqu'une exception survient, l'interpréteur tente de redémarrer
	 * afin que l'utilisateur puisse avoir un peu de contrôle après l'erreur.
	 * Pour déterminer où se trouve une erreur dans du code Jarvis, il faut
	 * ajouter des points d'arrêt dans le fichier de code avec la commande
	 * CMD_DEBUG. L'état de l'environnement n'est pas nécessairement correct
	 * après qu'une erreur soit survenue.
	 */
	public void run() {

		CommandAtom cmd;
		prompt();
		try {
			cmd = readCommandFromInput();
			cmd.interpret(this);
		} catch (UndefinedSymbolException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		} catch (IllegalArgumentException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		} catch (NoSuchElementException e) {
			outputErrorNoCrash(e.getMessage());
			reset();
		}

		if (getArgCount() > 0) {
			output(peekArg().makeKey());
		}

		while (!hasQuit()) {

			prompt();

			if(step){
				synchronized(uiLock){
					try {
						uiLock.wait();
					} catch (InterruptedException e) {}
				}
			}
			try {
				cmd = readCommandFromInput();
				cmd.interpret(this);
			} catch (UndefinedSymbolException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			} catch (IllegalArgumentException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			} catch (NoSuchElementException e) {
				outputErrorNoCrash(e.getMessage());
				reset();
			}

			if (hasCrashed) {

				outputError("Stopping due to earlier error");
				hasQuit = true;
			}

		}

	}

	/*
	 * Tentative de redï¿½marrage. Vide la liste d'arguments et la pile
	 * d'ï¿½valuation. Vous pouvez faire quelque-chose de plus intelligent ici.
	 */
	private void reset() {

		argQueue = new LinkedList<AbstractAtom>();
		evalStack = new Stack<String>();
	}

	/*
	 * Cas spï¿½cial qui survient lorsque l'interprï¿½teur dï¿½pile
	 * CMD_ENDOFENVIRONMENT. Cette commande se trouve empilï¿½e lorsqu'on entre
	 * dans une closure et qu'on crï¿½e son environnement.
	 */
	public void endOfEnvironment() {
		if (environment.hasParent()) {

			environment = environment.getParent();
		} else {
			outputErrorNoCrash("Cannot close global environment!");
		}
	}

	// SOUSLECAPOT
	/*
	 * Interprï¿½tation de message. Plusieurs trucs pas parfaitement propres ici
	 * Ceci est la partie de l'interprï¿½teur qui sert ï¿½ fabriquer des messages et
	 * les envoyer aux objets.
	 */

	public void message() {

		// Les atomes liï¿½s ï¿½ des symboles ne doivent pas ï¿½tre interprï¿½tï¿½s
		// rï¿½cursivement ici
		CommandAtom.setDontInterpret(true);

		CommandAtom cmd;
		CommandAtom objectName;

		ObjectAtom obj;

		// Les symboles indï¿½finis sont dï¿½tectï¿½s localement au cas oï¿½ un
		// traitement spï¿½cial
		// devrait ï¿½tre fait dans le futur
		CommandAtom.setAcceptUndefined(true);

		// Lecture de la premiï¿½re valeur: le destinataire du message.
		// Celui-ci peut se trouver dans la liste d'arguments.
		// Pour l'en rï¿½cupï¿½rer, il faut utiliser la commande CMD_ARG
		cmd = readCommandFromInput();
		AbstractAtom res = cmd.interpretNoPut(this);
		try {
			obj = (ObjectAtom) res;
		} catch (ClassCastException e) {

			throw new IllegalArgumentException(
					cmd.makeKey() + " is not an object. Type:" + res.getClass() + " , Value: " + res.makeKey());
		}

		if (res.isUndefined()) {
			throw new UndefinedSymbolException(cmd.makeKey() + ": symbole indï¿½fini");

		}

		objectName = cmd;
		// Lecture de la seconde valeur: le sï¿½lecteur.
		// Celui-ci peut se trouver dans la liste d'arguments.
		// Pour l'en rï¿½cupï¿½rer, il faut utiliser la commande CMD_ARG
		cmd = readCommandFromInput();
		AbstractAtom selector = cmd.interpretNoPut(this);
		if (selector.isUndefined()) {
			selector = new StringAtom(selector.makeKey());
		}

		CommandAtom.setAcceptUndefined(false);

		// L'objet qui recoit le message possï¿½de son propre environnement.
		// Dans cet environnement, on peut envoyer des messages ï¿½ self,
		// qui est un symbole rï¿½fï¿½renï¿½ant l'objet courant.
		JarvisEnvironment objectEnvironment = new JarvisEnvironment(this, getEnvironment());

		// Le symbole auquel est liï¿½ l'objet courant est self.
		objectEnvironment.put("self", obj);
		setEnvironment(objectEnvironment);

		// Lecture des arguments directement
		// Vous pouvez inclure des valeurs atomiques ou des symboles dï¿½finis
		// comme arguments. Les arguments restants seront rï¿½cupï¿½rï¿½s dans la
		// file d'arguments. Attention ï¿½ l'ordre! Ceux dans la parenthï¿½se
		// passent en prioritï¿½.
		cmd = readCommandFromInput();
		while (!cmd.isEndOfList()) {

			cmd.interpret(this);
			cmd = readCommandFromInput();
		}
		CommandAtom.setDontInterpret(false);

		// Empile une commande qui dï¿½truira l'environnement de l'objet aprï¿½s
		// l'ï¿½valuation du message.
		pushEval(CommandAtom.CMD_ENDOFENVIRONMENT);
		// Interprï¿½te le message, obtient le membre correspondant.

		res = obj.message(selector);

		// Interprï¿½tation du rï¿½sultat. Si c'est une mï¿½thode, elle sera
		// exï¿½cutï¿½e dans l'environnement de self, avec les arguments
		// prï¿½sentement dans la file.
		res.interpret(this);

		// Message interprï¿½tï¿½. On sort de l'environnement de l'objet.
		// BUG: Cette ligne a ï¿½tï¿½ retirï¿½e et remplacï¿½e par l'empilement d'une
		// commande
		// CMD_ENDOFENVIRONMENT au moment opportun. Empï¿½chait l'accï¿½s aux
		// paramï¿½tres d'une mï¿½thode.
		// setEnvironment(environment.getParent());

		// Vï¿½rification du retour.
		// Dï¿½commentez ces lignes si vous voulez un affichage de la valeur en
		// tï¿½te
		// de file aprï¿½s chaque message.
		// Il est mieux d'insï¿½rer une commande CMD_ARGS directement dans votre
		// code Jarvis pour voir la file au complet.
		// Un message d'avertissement demeure si vous obtenez "ComprendPas".
		// L'interprï¿½teur crash ï¿½galement.

		if (argQueue.size() > 0) {

			AbstractAtom resMsg = peekArgTail();
			if (resMsg instanceof StringAtom) {

				StringAtom resStr = (StringAtom) resMsg;
				if (resStr.getValue().startsWith("ComprendPas")) {
					throw new IllegalArgumentException("Object \"" + objectName.makeKey() + "\" of class "
							+ obj.findClassName(this) + " does not understand message \"" + selector.makeKey() + "\"");
				}
			}
		}
		/*
		 * if (argQueue.size() > 0) { print("Retour:");
		 * peekArg().interpretNoPut(this); }
		 */

	}

	// Appelï¿½ lorsque la commande CMD_QUIT est interprï¿½tï¿½e.
	public void quit() {
		hasQuit = true;
	}

	// Dï¿½compte du nombre d'arguments dans la file d'arguments.
	public int getArgCount() {
		return argQueue.size();
	}

	// Dï¿½file l'argument en tï¿½te de file.
	public AbstractAtom getArg() {
		return argQueue.remove();
	}

	// Enfile un argument. Utilisï¿½ lorsqu'un atome est interprï¿½tï¿½ normalement
	// (JarvisAtom.interpret)
	public void putArg(AbstractAtom obj) {
		argQueue.add(obj);
	}

	// Empile des commandes ï¿½ ï¿½valuer.
	public void pushEval(String expression) {
		evalStack.push(expression);
	}

	// Envoie un message d'erreur ï¿½ la console sans arrï¿½ter l'interprï¿½teur.
	private void outputErrorNoCrash(Object value) {
		System.err.println(value);
		println("");

	}

	// Permet de voir l'argument en tï¿½te de file.
	public AbstractAtom peekArg() {

		return argQueue.peek();
	}

	// Permet de voir l'argument en queue de file.
	public AbstractAtom peekArgTail() {

		return ((LinkedList<AbstractAtom>) argQueue).getLast();

	}

	// Utilisï¿½e pour obtenir la prochaine commande ï¿½ interprï¿½ter.
	public CommandAtom readCommandFromInput() {
		CommandAtom cmd = new CommandAtom(nextInput());
		return cmd;
	}

	// Affichage de la file d'arguments. Appelï¿½ par la commande CMD_ARGS.
	public void printArgs() {
		print("Args: (");
		for (AbstractAtom arg : argQueue) {
			print(arg + ",");
		}
		println(")");
	}

	// Lorsqu'un nouveau symbole est crï¿½ï¿½ avec la commande CMD_REF, il faut lier
	// celui-ci ï¿½ une valeur.
	public AbstractAtom getValue() {

		// Obtient d'abord ses valeurs de la file d'arguments
		if (argQueue.size() > 0) {

			return getArg();
		}

		// Si celle-ci est vide, assume que la valeur sera la prochaine
		// commande.
		return readAtomFromInput();
	}

	// Affichage de l'environnement courant. Appelï¿½ par la commande CMD_ENV
	public void printEnvironment() {

		environment.print();
	}

	// Raccourci d'affichage.
	public static void print(Object obj) {

		System.out.print(obj);

	}

	// Raccourci d'affichage.
	public static void println(Object obj) {

		System.out.println(obj);
	}

	/*
	 * Horrible fonction qui sert ï¿½ lire plusieurs commandes sur la mï¿½me ligne.
	 * Le seul semblant de dï¿½cortication de syntaxe se trouve ici, ainsi
	 * qu'ï¿½parpillï¿½ dans les diverses fonctions de CommandAtom. Ne regardez pas
	 * ï¿½a de trop prï¿½s... ï¿½a fait mal aux yeux.
	 */
	public String putTokensOnStack(String line) {
		final String delims = " ()[]{}";
		Stack<String> tokens = new Stack<String>();

		StringTokenizer tokenizer = new StringTokenizer(line, delims, true);

		while (tokenizer.hasMoreTokens()) {
			tokens.push(tokenizer.nextToken());
		}

		String previousToken = "";
		String currentToken = "";
		boolean glue = false;
		while (!tokens.isEmpty()) {
			currentToken = tokens.pop();
			currentToken = currentToken.trim();
			if (currentToken.compareTo("(") == 0 || currentToken.compareTo("{") == 0) {
				previousToken = currentToken;
				glue = true;
				continue;
			} else if (currentToken.compareTo("") == 0) {
				continue;
			}

			if (glue) {
				glue = false;
				if (currentToken.compareTo("") == 0) {
					evalStack.push(previousToken);
				} else {
					currentToken += previousToken;
					evalStack.push(currentToken);
				}
				previousToken = "";
			} else {
				evalStack.push(currentToken);
			}

		}
		if (glue) {
			evalStack.push(previousToken);
		}

		if (!evalStack.isEmpty()) {
			return evalStack.pop();
		} else {
			return "";
		}

	}

	/*
	 * Fonction appelï¿½e pour obtenir la prochaine commande sous forme de string.
	 * Regarde d'abord dans la pile d'ï¿½valuation.
	 */
	public String nextInput() {

		String s = "";
		if (evalStack.size() > 0) {
			s = evalStack.pop();
			if (s.length() == 0) {
				return nextInput();
			} else if (s.startsWith("#")) {
				return nextInput();
			}
			return s;

		} else {
			return readLine();
		}

	}

	/*
	 * Patch pour lire correctement des fichiers avec la commande CMD_LOAD.
	 * Devrait ï¿½tre utilisï¿½e par readLine, mais la combinaison est encore buggy.
	 */
	public String readSignificantLine(BufferedReader r) {
		String line = "";
		while (line.length() == 0 || line.startsWith("#")) {
			try {
				line = r.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line == null) {
				return null;
			}
			line = line.trim();
		}

		return line;
	}

	/*
	 * ï¿½pouvantable fonction servant ï¿½ lire la prochaine commande sous forme de
	 * string, lorsque la pile d'ï¿½valuation est vide. Un peu trop de singeries
	 * pour donner le bon comportement ï¿½ l'interprï¿½teur. Ne regardez pas de trop
	 * prï¿½s... ï¿½a fait saigner les yeux.
	 */
	public String readLine() {
		String s = "";

		try {
			s = reader.readLine();

			if (s == null) {
				println("End of file. Going to console...");
				prompt = true;
				prompt();
				reader = new BufferedReader(new InputStreamReader(System.in));
				fromFile=false;
				echo = false;
				return nextInput();
			} else {
				s = s.trim();

				if (s.length() == 0) {
					return nextInput();
				} else if (s.startsWith("#")) {
					return nextInput();
				} else {
					s = putTokensOnStack(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (echo) {
			println(s);
		}
		return s;
	}

	public void output(Object value) {
		println(value);

	}

	public void outputDebug(Object value) {
		print("#debug# | ");
		output(value);

	}

	public JarvisEnvironment getEnvironment() {
		return environment;
	}

	public void outputError(Object value) {

		hasCrashed = true;
		outputErrorNoCrash(value);
	}

	public void setEnvironment(JarvisEnvironment newEnvironment) {

		environment = newEnvironment;
	}

	/*
	 * Fonction utile pour dï¿½bugger. Affiche le contenu de la pile d'ï¿½valuation.
	 * Attention, un peu cryptique ï¿½ lire.
	 */

	public void printEvalStack() {

		println("----------Eval stack------------");

		for (String s : evalStack) {

			println(s);

		}
		println("----------Eval stack------------");

	}

	public boolean hasQuit() {

		return hasQuit;
	}

	public void setReader(BufferedReader r) {
		reader = r;

	}

	public void setEcho(boolean e) {
		echo = e;

	}

	public void clearArgs() {
		argQueue.clear();

	}

	public AbstractAtom readAtomFromInput() {
		CommandAtom cmd = readCommandFromInput();
		AbstractAtom atom = cmd.interpretNoPut(this);
		return atom;
	}

	public void prompt() {
		if (prompt && evalStack.isEmpty()) {
			print(">");
		}
	}

	public void setPrompt(boolean p) {
		prompt = p;
	}

	public void printAllEnvironment() {
		environment.printAll();

	}

	public void startUI() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (ui == null) {
					ui = new MainWindow(JarvisInterpreter.this);
				}
				ui.initUI();
				ui.setVisible(true);
			}
		});
		step=true;

	}

	public Queue<AbstractAtom> getArgQueue() {

		return argQueue;
	}

	public Stack<String> getEvalStack() {

		return evalStack;
	}
	
	public Object getUILock(){
		return uiLock;
	}
	public BufferedReader getReader(){
		return reader;
	}

	public boolean hasActiveFile() {
		
		return fromFile;
	}
}