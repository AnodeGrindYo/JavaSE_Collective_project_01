package TicTacToe;

/**
 * @author Adrien Godoy
 *
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Accueil.vueDepart;
import Util.soundPlayer;

public class Control extends Vue implements ActionListener
{
	private Modele newModel = new Modele();
	private int result = 0;
	private boolean quiJoue = false; // le joueur O commence
	soundPlayer soundfx = new soundPlayer();


	public Control()
	{

//		partie.Demarrer();
		System.out.println("demarrage d'une partie de Tic Tac Toe");
		// ajout des actionListener aux boutons
		for(int x=0; x<9; x++)
			super.bouton[x].addActionListener(this);
		// démarrage d'une nouvelle partie
	}


	public void goAgain()
	{
//		soundfx.playSound("sound/Victory2.wav");
		bdd.FinPartie(result, partie.getId_partie());
		Icon img = new ImageIcon("img/chat_content.png");
		JOptionPane jop = new JOptionPane();
		jop.setIcon(img);
		String nomGagnant = (partie.getJoueur(0).getNumero() == result)?partie.getJoueur(0).getNom():partie.getJoueur(1).getNom();
		int reply = jop.showConfirmDialog
				(null, nomGagnant/*"Joueur "+result*/+" a gagné.\nVoulez-vous rejouer ?", "Morpion" , JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, img);
		
		if (reply == JOptionPane.YES_OPTION)
		{
			newModel.resetMorpion();
			super.resetGame();
		}
		else
		{
			dispose();
			new vueDepart();
		}
	}


	public void actionPerformed (ActionEvent event)
	{
		Object objClicked=event.getSource();

		for(int i = 0;  i < 9; i++)
		{
			if(objClicked==super.bouton[i])
			{
				soundfx.playSound("sound/blip.wav");
//				if (super.bouton[i].getText()=="")
				if (super.bouton[i].getIcon()==null)
				{
					if (quiJoue==false)
					{
						super.setGridColor(Color.pink);
//						super.bouton[x].setText("X");
						super.bouton[i].setIcon(X);
						newModel.setClick(i,1);
						quiJoue=true;  // changement de joueur
						result=newModel.verifVictoire();
						System.out.println("Control / actionPerformed : num joueur = "+partie.getJoueur(0).getNumero());
						partie.addCoup(i, (partie.getJoueur(0).getNumero() == 1?partie.getJoueur(0):partie.getJoueur(1)));
//						System.out.println(partie.getJoueur(1)+" a joué sur la case "+i);
//						partie.addCoup(i, partie.getJoueur(1));
						

					}
					else
					{
						super.setGridColor(Color.cyan);
//						super.bouton[i].setText("O");
						super.bouton[i].setIcon(O);
						newModel.setClick(i, 2);
						quiJoue=false; // changement de joueur
						result=newModel.verifVictoire();
						partie.addCoup(i, (partie.getJoueur(0).getNumero() == 2?partie.getJoueur(0):partie.getJoueur(1)));
//						System.out.println(partie.getJoueur(2)+" a joué sur la case "+i);
//						partie.addCoup(i, partie.getJoueur(2));

					}
				}
			}
		}
		if(result!=0)
		{
			partie.setResultat(result);
			goAgain();
		}
	}
}