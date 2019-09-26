package labo2;


/*
 * Classe représentant un vecteur. 
 */

public class Vecteur {
	public static final double EPSILON = 0.01;
	
	private double valeurs[];
	
	
	public Vecteur(double [] valeurs){
		
		this.valeurs = new double[valeurs.length];
		for(int i=0;i<valeurs.length;i++){
			
			this.valeurs[i]=valeurs[i];			
		}		
	}
	
	public String toString(){
		
		String res="[";
		for (double v:valeurs){
			res+=v+" ";
		}
		return  res.substring(0, res.length()-1)+"]";
	}
	
	public int taille(){
		return valeurs.length;
	}
	
	public double getValeur(int pos){
		return valeurs[pos];
	}
	
	public void setValeur(int pos,double val){
		valeurs[pos]=val;
	}
	@Override
	public boolean equals(Object vec)
	{
		if(valeurs.length == ((Vecteur)vec).taille())
		{
			for(int i=0;i<valeurs.length;i++)
			{
				if(Math.abs(valeurs[i]-((Vecteur)vec).getValeur(i))>EPSILON) return false;
			}
			return true;
		}
		else return false;

	}
	
	
	Vecteur sousVecteur(int size)
	{
		if(size<=taille())
		{
			double[] tab = new double[size];
			for (int i =0;i<size;i++)
			{
				tab[i]=valeurs[i];
			}
			return new Vecteur(tab);
		}
		else throw new IllegalArgumentException("blabla erreur bouhouhou");

	
	}
	

	
}
