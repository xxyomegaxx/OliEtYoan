package labo5.followers;

/*
 * Journal syst�me. Affiche dans la console le message en question.
 * Utilisez-le pour voir si les messages sont produits comme pr�vu
 * par les vedettes.
 */
public class MessageLog  implements Follower {
	
	@Override
	public void update(String name,String message){
		
		System.out.println(message);
		
	}

}
