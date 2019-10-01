package labo2;

import java.util.ArrayList;

/*
 * Classe représentant un vecteur. 
 */

public class Vecteur {
	public static final double EPSILON = 0.01;
	
	protected double valeurs[];
	protected Vecteur(int size)
	{
		this.valeurs = new double[size];
	}
	public Vecteur(double [] valeurs){
		
		this.valeurs = new double[valeurs.length];
		for(int i=0;i<valeurs.length;i++){
			
			this.valeurs[i]=valeurs[i];			
		}		
	}
	
    public Vecteur(ArrayList<Integer> valeurs){
		
		this.valeurs = new double[valeurs.size()];
		for(int i=0;i<valeurs.size();i++){
			
			this.valeurs[i]=valeurs.get(i);			
		}		
	}
	public static Vecteur creerVecteurNul (int taille)
	{
		Vecteur retour = new Vecteur(taille);
		for(int i=0;i<taille;i++)
		{
			retour.valeurs[i]=0;
		}
		return retour;
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
	public double[] getTableau()
	{
		return valeurs;
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
	
	
	public Vecteur sousVecteur(int size)
	{
		int val = taille();
		System.out.println(size +" <= " + val);
		if(size<=val)
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
