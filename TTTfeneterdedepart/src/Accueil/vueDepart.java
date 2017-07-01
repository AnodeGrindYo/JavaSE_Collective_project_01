package Accueil;
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

import Connexion.vueConnexionAppli;
import Inscription.Formulaire;
import Util.Music;
import Util.soundPlayer;

/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
public class vueDepart extends JFrame implements ActionListener
{
	/***********************************
	 **************DONNEES**************
	 **********************************/
//	private static vueDepart premiereInstance =null;
	protected JButton btnNouveauCompte 	= new JButton(/*"M'inscrire"*/);
	protected JButton btnConnect 		= new JButton(/*"Me connecter"*/);
	protected JButton btnQuit 			= new JButton(/*"Quitter"*/);
	protected JLabel heure 				= new JLabel("heure");
	protected JPanel panAccueil 		= new JPanel(new GridBagLayout()); // pan principal de cette vue
	soundPlayer soundfx = new soundPlayer();
	Music music;
	Icon icoConnexion = new ImageIcon("img/bttn_connexion.png");
	Icon icoConnexion_HOVER = new ImageIcon("img/bttn_connexion_over.png");
	Icon icoInscrire = new ImageIcon("img/bttn_inscription.png");
	Icon icoInscrire_HOVER = new ImageIcon("img/bttn_inscription_over.png");
	Icon icoQuitter = new ImageIcon("img/bttn_quitter.png");
	Icon icoQuitter_HOVER = new ImageIcon("img/bttn_quitter_over.png");
//	FontLoader customFont; //TO DO trouver une solution pour les fonts custom // EDIT : osef, les fonts !
	
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
	
	
	public vueDepart()
	{
		
		super("accueil");
		this.setTitle("Morpion");
		this.setSize(400,600);				
		
		Music music = new Music();
		
		/*
		 * BOUTONS************************************
		 */
		// CONNECT
		btnConnect.setPreferredSize(new Dimension(150, 50));
//		btnConnect.setFont(customFont.getFont());
		btnConnect.setBackground(Color.BLACK);
		btnConnect.setContentAreaFilled(false);/////////////////////////////////////////
		btnConnect.setIcon(icoConnexion);
		btnConnect.setRolloverIcon(icoConnexion_HOVER);
		btnConnect.setBorderPainted(false);
		btnConnect.setForeground(Color.GREEN);
		btnConnect.addActionListener(this);
		// INSCRIPTION
		btnNouveauCompte.setPreferredSize(new Dimension(150, 50));
//		btnNouveauCompte.setFont(customFont.getFont());
		btnNouveauCompte.setBackground(Color.BLACK);
		btnNouveauCompte.setIcon(icoInscrire);
		btnNouveauCompte.setRolloverIcon(icoInscrire_HOVER);
		btnNouveauCompte.setBorderPainted(false);
		btnNouveauCompte.setContentAreaFilled(false);
		btnNouveauCompte.setForeground(Color.GREEN);
		btnNouveauCompte.addActionListener(this);
		// QUITTER
		btnQuit.setBackground(Color.BLACK);
		btnQuit.setForeground(Color.GREEN);
		btnQuit.setContentAreaFilled(false);
		btnQuit.setIcon(icoQuitter);
		btnQuit.setRolloverIcon(icoQuitter_HOVER);
		btnQuit.setBorderPainted(false);
		btnQuit.addActionListener(this);
		
		heure.setBackground(Color.BLACK);
		heure.setForeground(Color.GREEN);
		
		// les infobulles
		btnConnect.setToolTipText("Tu as déjà un compte ? Viens jouer !");
		btnNouveauCompte.setToolTipText("Tu n'as pas encore de compte? Inscris-toi ici!");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(50, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		panAccueil.add(heure, c);
		c.gridx = 1;
		c.gridy = 1;
		panAccueil.add(btnNouveauCompte, c);
		c.gridx = 1;
		c.gridy = 2;
		panAccueil.add(btnConnect, c);
		c.gridx = 1;
		c.gridy = 3;
		panAccueil.add(btnQuit, c);
		
		panAccueil.setBackground(Color.BLACK);
		
		date raffraichDate = new date();
		
		this.add(panAccueil);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//        this.pack();
        this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// si on clique sur nouveau compte
		if(e.getSource()==btnNouveauCompte)
		{
			new Formulaire();
			this.dispose();
			soundfx.playSound("sound/valider.wav");
		}
		
		// si on clique sur connect
		else if(e.getSource()==btnConnect)
		{
			new vueConnexionAppli();
			this.dispose();
			soundfx.playSound("sound/valider.wav");
		}
		
		// si on clique sur quit
		else if(e.getSource()==btnQuit)
		{
			this.dispose();
			soundfx.playSound("sound/annuler.wav");
			music.stopMusic();
			System.exit(0);
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
