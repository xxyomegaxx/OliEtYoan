package labo2;

/*
 * Classe représentant une matrice. Composée d'une liste
 * de vecteurs. Chaque rangée de la matrice est un vecteur.
 */
public class Matrice {

	private Vecteur[] lignes;


	
	
	public Matrice(double[][] dat) {

		lignes = new Vecteur[dat.length];

		for (int i = 0; i < dat.length; i++) {

			lignes[i] = new Vecteur(dat[i]);

		}
	}
	
	private Matrice(int h,int l)
	{
		this.lignes = new Vecteur[h];
		for(int i = 0;i<h;i++)
		{
			this.lignes[i]=Vecteur.creerVecteurNul(l);
		}
		
	}
	
	public static Matrice creerMatriceNul (int h, int l)
	{
		Matrice retour = new Matrice(h,l);
		for(int i=0;i<h;i++)
		{
			retour.lignes[i] = Vecteur.creerVecteurNul(l);
		}
		return retour;
	}
	
	int dimensions()
	{
		return lignes.length*lignes[0].taille();
	}

	/*
	 * Élimination Gaussienne. Let me google that for you... Implémentation
	 * suivant le pseudo-code classique.
	 */
	public void Gauss() {
		
		if(lignes[0].taille()-lignes.length==1)
		{
			int noLigne = 0;
			for (Vecteur ligne : lignes) {
				double pivot = ligne.getValeur(noLigne);
				if (pivot != 0) {
					double pivotInverse = 1.0 / pivot;
					for (int i = 0; i < ligne.taille(); i++) {
						ligne.setValeur(i, ligne.getValeur(i) * pivotInverse);
					}
				}

				for (Vecteur ligneElim : lignes) {
					if (ligneElim != ligne) {
						double f = ligneElim.getValeur(noLigne);
						for (int i = 0; i < ligneElim.taille(); ++i) {
							ligneElim.setValeur(i, ligneElim.getValeur(i) - f * ligne.getValeur(i));
						}
					}
				}
				noLigne++;
			}
		}
		else throw new IllegalArgumentException("blabla erreur bouhouhou");

		
	}
	
	public Vecteur getVecteur(int x)
	{
		return lignes[x];
	}
	
	public String toString() {

		String res = "";

		for (Vecteur v : lignes) {
			res += v + "\n";
		}

		return res;
	}
	
	@Override
	public boolean equals(Object mat)
	{
		if(this.dimensions() == ((Matrice)mat).dimensions())
		{
			for(int i=0;i<lignes.length;i++)
			{
				if(!lignes[i].equals(((Matrice)mat).getVecteur(i))) return false;
			}
			return true;
		}
		else return false;

	}
	
	public Matrice sousMatrice(int h,int l)
	{
		
		if(h<=lignes.length)
		{			
			double[][] tab = new double[h][l];
			for (int i =0;i<h;i++)
			{
				for (int j = 0; j<l;j++)
				{
					if(l<lignes[i].taille())
					{
						tab[i][j]=lignes[i].getValeur(j);
					}
					else throw new IllegalArgumentException("blabla erreur bouhouhou");
					
				}
			}
			return new Matrice(tab);
		}
		else throw new IllegalArgumentException("blabla erreur bouhouhou");

	
	}
	

}
