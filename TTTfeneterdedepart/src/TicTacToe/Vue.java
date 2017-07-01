package TicTacToe;
/**
 * @author Adrien Godoy
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BDD.BDD;
import Partie.Partie;
import Util.RW;
import Util.soundPlayer;


class Vue extends JFrame
{
	// définit les boutons et la grille
	private static Vue premiereInstance =null;
	protected JButton bouton[];
	protected GridLayout grid;
//	ImageIcon X,O; /* x et o contiennent les images des pions */
	Icon X = new ImageIcon("img/daffy.png");
	Icon O = new ImageIcon("img/eido.png");
	protected JPanel panelPlateau = new JPanel();///////////////////////////////////
	protected JPanel panelInfo = new JPanel();
	protected JPanel panelGeneral = new JPanel(new BorderLayout());
//	JLabel quivientdejouer;////////////////
//	JLabel quivajouer;
	BDD bdd = new BDD();
	Partie partie;
	ArrayList <ArrayList <String> > idJoueurs;
	RW rw = new RW();


    soundPlayer soundfx = new soundPlayer();

	protected Vue()
	{
		super("Jeu de Morpion");
		
		bouton = new JButton[9]; 
		grid= new GridLayout(3, 3, 5, 5); 
		panelPlateau.setLayout(grid);//////////////////////////////////
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		panelPlateau.setSize(400, 400);////////////////////////////////////
//		setLayout(grid);
//		X=new ImageIcon("img/daffy.png");
//		O=new ImageIcon("img/eido.png");
		
		
		// Construit les boutons
		for(int i = 0; i < 9; i++)
		{
			bouton[i] = new JButton(); 
//			add(bouton[i]);
			panelPlateau.add(bouton[i]);////////////////////////////
		}
		this.setGridColor(Color.cyan);
		
		panelPlateau.setBackground(Color.BLACK);
//		panelInfo.setLayout(new GridLayout(2, 1));
//		panelInfo.add(quivientdejouer);
//		panelInfo.add(quivajouer);
		
//		panelGeneral.add(panelPlateau, BorderLayout.EAST);
		
		this.add(panelPlateau);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		setVisible(true);
		resetGame();
	}

	// réinitialise l'interface
	public void resetGame()
	{
//		if(idJoueurs.size()!=0)
//		{
//			for (int i = 0; i<2; i++)
//			{
//				idJoueurs.get(i).clear();
//			}
//		}
		for(int i = 0; i < 9; i++)
		{
//			bouton[i].setText("");
			bouton[i].setIcon(null);
		}
		// on crée une nouvelle partie et on récupère les id des joueurs
		partie = new Partie();
		idJoueurs = rw.extractSeparateWords("file/sav.ttt", " ; ");
		try
		{
			partie.addJoueur(bdd.InfosJoueur(bdd.ConnecterJoueur(idJoueurs.get(0).get(0), idJoueurs.get(0).get(1))));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			partie.addJoueur(bdd.InfosJoueur(bdd.ConnecterJoueur(idJoueurs.get(1).get(0), idJoueurs.get(1).get(1))));
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partie.Demarrer();
	}

	// restreint l'instanciation du jeu à une seule instance (on ne peut le lancer qu'une seule fois)
	public static Vue getInstace()
	{
		if (premiereInstance==null)
			premiereInstance = new Vue();

		return premiereInstance;
	}
	
	public void setGridColor(Color c)
	{
		for (int i = 0; i < 9; i++)
		{
			bouton[i].setBackground(c);
		}
	}
	
	public Partie getPartie()
	{
		return partie;
	}
}

