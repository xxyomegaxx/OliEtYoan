package labo2;


/*
 * Classe repr�sentant un vecteur. 
 */

public class Vecteur {
	
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
				if(valeurs[i]!=((Vecteur)vec).getValeur(i)) return false;
			}
			return true;
		}
		else return false;

	}
	
	

	
}
