package TicTacToe;
/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
class Modele
{
	int tab[] = new int[9]; // O = 1 - X = 2
	int nbClics=1;

	public void setClick(int position, int XorO)
	{ 
		tab[position]=XorO; // ajoute 1 ou 2 à la case du tableau
		nbClics++; // passe au tour suivant

	}


	// réinitialise le tableau
	public void resetMorpion()
	{
		nbClics=0;
		for(int i = 0; i < 9; i++)
		{
			tab[i]=0;
		}
	}
	/*
	 * cette méthode vérifie si un joueur gagne.
	 * retourne 1 si les ronds gagnent, 2 si les croix gagnent, 0 si ex-aequo
	 */
	public int verifVictoire()
	{ 
		// vérification victoire horizontale
		if (tab[0]==1 && tab[1]==1 && tab[2]==1)
			return 1; // O gagne
		if (tab[3]==1 && tab[4]==1 && tab[5]==1)
			return 1; // O gagne
		if (tab[6]==1 && tab[7]==1 && tab[8]==1)
			return 1; // O gagne 
		if (tab[0]==2 && tab[1]==2 && tab[2]==2)
			return 2; // X gagne		
		if (tab[3]==2 && tab[4]==2 && tab[5]==2)
			return 2; // X gagne		
		if (tab[6]==2 && tab[7]==2 && tab[8]==2)
			return 2; // X gagne


		// vérification victoire verticale
		if (tab[0]==1 && tab[3]==1 && tab[6]==1)
			return 1; // O gagne
		if (tab[1]==1 && tab[4]==1 && tab[7]==1)
			return 1; // O gagne
		if (tab[2]==1 && tab[5]==1 && tab[8]==1)
			return 1; // O gagne
		if (tab[0]==2 && tab[3]==2 && tab[6]==2)
			return 2; // X gagne		
		if (tab[1]==2 && tab[4]==2 && tab[7]==2)
			return 2; // X gagne		
		if (tab[2]==2 && tab[5]==2 && tab[8]==2)
			return 2; // X gagne 

		// vérification victoire diagonale
		if (tab[0]==1 && tab[4]==1 && tab[8]==1)
			return 1; // O gagne
		if (tab[2]==1 && tab[4]==1 && tab[6]==1)
			return 1; // O gagne
		if (tab[0]==2 && tab[4]==2 && tab[8]==2)
			return 2; // X gagne		
		if (tab[2]==2 && tab[4]==2 && tab[6]==2)
			return 2; // X gagne

		if (nbClics==9)
			return 3; // Ex-aequo

		return 0; // personne n'a gagné
	}
}