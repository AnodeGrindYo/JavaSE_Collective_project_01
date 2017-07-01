package ChoixDuJeu;
/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Accueil.vueDepart;
import Connect4.C4JouerConnect4;
import TicTacToe.Control;
import Util.soundPlayer;

//import connect4.C4JouerConnect4;

/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
public class VueChoixJeu extends JFrame implements ActionListener
{
	/**
	 * 
	 */
//	private static vueDepart premiereInstance =null;
	protected JButton btnConnect4 	= new JButton(/*"Puissance 4"*/);
	protected JButton btnMorpion 		= new JButton(/*"Morpion"*/);
	protected JButton btnQuit 			= new JButton(/*"Quitter"*/);
	protected JLabel heure 				= new JLabel("heure");
	protected JPanel panChoixJeu 		= new JPanel(new GridBagLayout()); // pan principal de cette vue
	soundPlayer soundfx = new soundPlayer();
	Icon icoC4 = new ImageIcon("img/bttn_c4.png");
	Icon icoC4_HOVER = new ImageIcon("img/bttn_c4_over.png");
	Icon icoTTT = new ImageIcon("img/bttn_ttt.png");
	Icon icoTTT_HOVER = new ImageIcon("img/bttn_ttt_over.png");
	Icon icoQuit = new ImageIcon("img/bttn_quitter.png");
	Icon icoQuit_HOVER = new ImageIcon("img/bttn_quitter_over.png");
	
	/*
	 * la classe imbriquée ci-dessous s'occupe de mettre à jour l'heure en temps réel
	 */
	public class date implements Runnable
	{
		Thread trDate;
		private String horloge;
		
		public date() 
		{
	        if (trDate == null) 
	        {
	            trDate = new Thread(this);
	            trDate.start();
	        }
	    }
		
		public void run()
		{
			while (true)
			{
				Date date1 = new Date();
	            DateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	            horloge=(formatdate.format(date1));
	            heure.setText(horloge);
	            
	            try 
	            { 
	            	Thread.sleep(1000);
	            } 
	            catch(InterruptedException e)
	            {
	            	
	            }
			}
		}
	}
	
	
	public VueChoixJeu()
	{
		
		super("Choix du jeu");
		
		this.setTitle("Choix du jeu");
		this.setSize(400,600);
		
		// design des boutons
		btnQuit.setContentAreaFilled(false);
		btnQuit.setBorderPainted(false);
		btnQuit.setIcon(icoQuit);
		btnQuit.setRolloverIcon(icoQuit_HOVER);
		btnConnect4.setContentAreaFilled(false);
		btnConnect4.setBorderPainted(false);
		btnConnect4.setIcon(icoC4);
		btnConnect4.setRolloverIcon(icoC4_HOVER);
		btnMorpion.setContentAreaFilled(false);
		btnMorpion.setBorderPainted(false);
		btnMorpion.setIcon(icoTTT);
		btnMorpion.setRolloverIcon(icoTTT_HOVER);
		
		// on définit une taille préférée pour les boutons
		btnMorpion.setPreferredSize(new Dimension(150, 50));
		btnMorpion.setBackground(Color.BLACK);
		btnMorpion.setForeground(Color.GREEN);
		btnMorpion.addActionListener(this);
		btnConnect4.setPreferredSize(new Dimension(150, 50));
		btnConnect4.setBackground(Color.BLACK);
		btnConnect4.setForeground(Color.GREEN);
		btnConnect4.addActionListener(this);
		btnQuit.setBackground(Color.BLACK);
		btnQuit.setForeground(Color.GREEN);
		btnQuit.addActionListener(this);
		// les infobulles
		btnMorpion.setToolTipText("Dépasse toi et terasse ton adversaire en alignant 3 de tes pions !");
		btnConnect4.setToolTipText("Réalise ton but ultime : aligne 4 de tes pions avant ton adversaire !");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(50, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		panChoixJeu.add(heure, c);
		c.gridx = 1;
		c.gridy = 1;
		panChoixJeu.add(btnConnect4, c);
		c.gridx = 1;
		c.gridy = 2;
		panChoixJeu.add(btnMorpion, c);
		c.gridx = 1;
		c.gridy = 3;
		panChoixJeu.add(btnQuit, c);
		
		panChoixJeu.setBackground(Color.BLACK);
		
		date raffraichDate = new date();
		
		this.add(panChoixJeu);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//        this.pack();
        this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// si on clique sur nouveau compte
		if(e.getSource()==btnConnect4)
		{
			C4JouerConnect4.jouerConnect4();
			soundfx.playSound("sound/valider.wav");
			this.dispose();
		}
		
		// si on clique sur connect
		else if(e.getSource()==btnMorpion)
		{
			soundfx.playSound("sound/valider.wav");
			new Control();
			this.dispose();
		}
		
		// si on clique sur quit
		else if(e.getSource()==btnQuit)
		{
			soundfx.playSound("sound/annuler.wav");
			this.dispose();
			new vueDepart();
		}
	}
	// restreint l'instanciation du jeu à une seule instance (on ne peut le lancer qu'une seule fois
//		public static vueDepart getInstace()
//		{
//			if (premiereInstance==null)
//				premiereInstance = new vueDepart();
//
//			return premiereInstance;
//		}
}

