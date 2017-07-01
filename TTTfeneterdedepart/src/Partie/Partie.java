/*
 * @author Adrien Godoy
 */

package Partie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import BDD.BDD;

public class Partie implements Observer // pas sur...
{
	private Joueur listeJoueur[] = {null, null};
	private ArrayList <Integer> listeCoups = new ArrayList();
	private int id_partie;
	private Date debut_partie;
	private int resultat;
	BDD bdd = new BDD();
	private int randomNum = 1 + (int)(Math.random() * 2);
	JOptionPane jop1 = new JOptionPane();

	public Partie()
	{

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		// TODO Auto-generated method stub

	}

	public void Demarrer()
	{
		debut_partie = new Date();
		// si les deux joueurs sont connectés, on crée la partie
		if (listeJoueur[0] != null && listeJoueur[1] != null)
		{
			try
			{
				id_partie = bdd.CreerPartie(listeJoueur[0].getId(), randomNum);
				System.out.println("Demarrer : id joueur1 = "+listeJoueur[0].getId());
				System.out.println("randomNum = "+randomNum);
				listeJoueur[0].setNumero(randomNum); // vérifier qu'il s'agit du bon id
				
				// le deuxième joueur rejoint la partie (pourra être déplacé si on fait une interface réseau)
				bdd.RejoindrePartie(id_partie, listeJoueur[1].getId(), (randomNum == 1)?2:1);
				listeJoueur[1].setNumero((randomNum == 1)?2:1); // vérifier qu'il s'agit du bon id
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public void addJoueur(Joueur j)
	{
		// on ajoute le joueur
		if (listeJoueur[0] == null)
		{
			listeJoueur[0]=j;
		}
		else if (listeJoueur[1] == null)
		{
			listeJoueur[1]=j;
		}
		else
		{
			ImageIcon img = new ImageIcon("img/chat_embarassé.png");
			jop1.showMessageDialog(null, "désolé, cette partie ne peut plus accepter de joueur", "Partie pleine", JOptionPane.ERROR_MESSAGE, img);
		}
	}

	public void addCoup(int coord, Joueur j)
	{
		listeCoups.add(coord);
		bdd.JouerCoup(j.getId(), id_partie, coord);
		System.out.println(j.getpseudo()+" a joué sur la case "+coord);
	}

	public Joueur[] getListeJoueur()
	{
		return listeJoueur;
	}

	public void setListeJoueur(Joueur[] listeJoueur)
	{
		this.listeJoueur = listeJoueur;
	}

	public ArrayList<Integer> getListeCoups()
	{
		return listeCoups;
	}

	//	public void setListeCoups(ArrayList<Integer> listeCoups)
	//	{
	//		this.listeCoups = listeCoups;
	//	}

	public int getId_partie()
	{
		return id_partie;
	}

	public void setId_partie(int id_partie)
	{
		this.id_partie = id_partie;
	}

	public Date getDebut_partie()
	{
		return debut_partie;
	}

	public void setDebut_partie(Date debut_partie)
	{
		this.debut_partie = debut_partie;
	}

	public int getResultat()
	{
		return resultat;
	}

	public void setResultat(int resultat)
	{
		this.resultat = resultat;
	}


	public Joueur getJoueur(int i)
	{
		if(i >= 0 && i < 3)
			return listeJoueur[i];
		else
		{
			System.err.println("getJoueur : indice non valide");
			return null;
		}
	}









	//	/**
	//	 * 
	//	 */
	//	private int tour;
	//	private int joueur;
	//	private boolean fin;
	//	
	//	//Constructors
	//	public Partie() 
	//	{
	//		// TODO Auto-generated constructor stub
	//		this.tour=0;
	//		this.joueur=1;
	//		this.fin=false;
	//	}
	//	
	//	//Setters
	//	public void setTour(int t)
	//	{
	//		this.tour=t;
	//	}
	//	public void setJoueur(int j)
	//	{
	//		this.joueur=j;
	//	}
	//	public void setFin(boolean b)
	//	{
	//		this.fin=b;
	//	}
	//	
	//	//Getters
	//	public int getTour()
	//	{
	//		return this.tour;
	//	}
	//	public int getJoueur()
	//	{
	//		return this.joueur;
	//	}
	//	public boolean getFin()
	//	{
	//		return this.fin;
	//	}
	//	
	//	public void changerTour()
	//	{
	//		if(getTour()<8){
	//			setTour(getTour()+1);
	//			if(getTour()%2==0)
	//			{
	//				setJoueur(2);
	//			}else
	//			{
	//				setJoueur(1);
	//			}
	//		}else
	//		{
	//			setFin(true);
	//		}
	//	}
	//
	//	/* (non-Javadoc)
	//	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	//	 */
	//	@Override
	//	public void update(Observable arg0, Object arg1)
	//	{
	//		// TODO Auto-generated method stub
	//		
	//	}

}