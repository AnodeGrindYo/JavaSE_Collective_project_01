package Connexion;
//package Vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Accueil.vueDepart;
import BDD.BDD;
import ChoixDuJeu.VueChoixJeu;
import Partie.Joueur;
import Util.EmailFormatter;
import Util.RW;
import Util.soundPlayer;

public class vueConnexionAppli extends JFrame implements ActionListener {
	// crÃƒÂ©ation de Panels pour la saisie les boutons et les labels

	private JPanel panelconnexion= null;

	//private JFrame fenetreControle; //needed for dialogs
	private JPasswordField champMdp1= new JPasswordField(30);
	private JTextField champEmail1    = new JFormattedTextField(new EmailFormatter());

	private JTextField champEmail2    = new JFormattedTextField(new EmailFormatter());
	private JPasswordField champMdp2= new JPasswordField(30);

	//crÃƒÂ©ation des ÃƒÂ©tiquettes email et mdp
	private JLabel labelJoueur1 =new JLabel ("Joueur1");
	private JLabel labelEmail1  = new JLabel("Email : ");
	private JLabel labelMdp1    = new JLabel("Mot de passe: ");
	private JLabel labelJoueur2 =new JLabel ("Joueur2");
	private JLabel labelEmail2  = new JLabel("Email : ");
	private JLabel labelMdp2    = new JLabel("Mot de passe: ");

	//crÃƒÂ©ation des boutons ok et annuler
	private static String OK;
	private static String Annule;
	private JButton boutonOk       = new JButton(/*"OK"*/);
	private JButton boutonAnnule  = new JButton(/*"Annuler"*/);
	Icon icoQuitter = new ImageIcon("img/bttn_quitter.png");
	Icon icoQuitter_HOVER = new ImageIcon("img/bttn_quitter_over.png");
	Icon icoOk = new ImageIcon("img/bttn_valider.png");
	Icon icoOk_HOVER = new ImageIcon("img/bttn_valider_over.png");
	soundPlayer soundfx = new soundPlayer();
	BDD bdd = new BDD();
	
	private Joueur j1 = new Joueur();
	private Joueur j2 = new Joueur();
	
	RW rw = new RW();
	

	


	public vueConnexionAppli(){
		System.out.println("vue connection appli");
		//fenÃªtre panel de connexion
		panelconnexion=new JPanel ();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		boutonOk.setIcon(icoOk);
		boutonOk.setRolloverIcon(icoOk_HOVER);
		boutonAnnule.setIcon(icoQuitter);
		boutonAnnule.setRolloverIcon(icoQuitter_HOVER);
		boutonOk.setContentAreaFilled(false);
		boutonOk.setBorderPainted(false);
		boutonAnnule.setContentAreaFilled(false);
		boutonAnnule.setBorderPainted(false);
		
		panelconnexion.setOpaque(true);
		panelconnexion.setBackground(Color.BLACK);
		labelJoueur1.setOpaque(true);
		labelJoueur2.setOpaque(true);
		labelEmail1.setOpaque(true);
		labelEmail2.setOpaque(true);
		labelMdp1.setOpaque(true);
		labelMdp2.setOpaque(true);
		
		labelJoueur1.setBackground(Color.BLACK);
		labelJoueur2.setBackground(Color.BLACK);		
		labelEmail1.setBackground(Color.BLACK);
		labelEmail2.setBackground(Color.BLACK);
		labelMdp1.setBackground(Color.BLACK);
		labelMdp2.setBackground(Color.BLACK);
		labelJoueur1.setForeground(Color.GREEN);
		labelJoueur2.setForeground(Color.GREEN);
		labelEmail1.setForeground(Color.GREEN);
		labelEmail2.setForeground(Color.GREEN);
		labelMdp1.setForeground(Color.GREEN);
		labelMdp2.setForeground(Color.GREEN);
		champEmail1.setForeground(Color.GREEN);
		champEmail1.setBackground(Color.BLACK);
		champEmail2.setForeground(Color.GREEN);
		champEmail2.setBackground(Color.BLACK);
		
		champEmail1.setCaretColor(Color.GREEN);
		champEmail2.setCaretColor(Color.GREEN);
		champMdp1.setCaretColor(Color.GREEN);
		champMdp2.setCaretColor(Color.GREEN);
		
		champEmail1.setHorizontalAlignment(SwingConstants.CENTER);
		champEmail2.setHorizontalAlignment(SwingConstants.CENTER);
		champMdp1.setHorizontalAlignment(SwingConstants.CENTER);
		champMdp2.setHorizontalAlignment(SwingConstants.CENTER);

		//actions reliÃ©es au bouton ok concernant le mode passe
		champMdp1.setActionCommand(OK);
		champMdp1.addActionListener(this);  
		champMdp2.setActionCommand(OK);
		champMdp2.addActionListener(this);  
		champMdp1.setBackground(Color.BLACK);
		champMdp1.setForeground(Color.GREEN);
		champMdp2.setBackground(Color.BLACK);
		champMdp2.setForeground(Color.GREEN);

		boutonOk.setPreferredSize(boutonAnnule.getPreferredSize());  
		// initialistion du container

		panelconnexion.setLayout(new GridBagLayout());

		// ajout des composants en spÃƒÂ©cifiant les contraintes
		GridBagConstraints c=new GridBagConstraints();
		int  gridwidth;
		int  gridheight;
		c.gridx=1;
		c.gridy=0;
		c.gridheight = c.gridwidth = 1;
		c.insets = new Insets(100, 10, 0, 0);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		panelconnexion.add(labelJoueur1, c);
		

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.insets = new Insets(0, 10, 0, 0);
		panelconnexion.add(labelEmail1,c);

		c.gridx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 10, 0, 10);

		panelconnexion.add(champEmail1,c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridheight =c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 0);
		panelconnexion.add(labelMdp1, c);

		c.gridx = 1;
		c.gridy=2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 0, 10);

		panelconnexion.add(champMdp1, c);		

		//JOUEUR2////
		//LAbel Joueur2
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 0);

		panelconnexion.add(labelJoueur2, c);

		//Label Email2
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 0);

		panelconnexion.add(labelEmail2, c);

		//champ Email2
		c.gridx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 0, 10);

		panelconnexion.add(champEmail2,c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 0);

		panelconnexion.add(labelMdp2, c);

		c.gridx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 0, 10);


		panelconnexion.add(champMdp2, c);		

		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = c.gridwidth = 1;
		c.anchor = GridBagConstraints.BASELINE_TRAILING;
		c.weightx = 1.;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(200, -200, 10, 5);
		panelconnexion.add(boutonOk, c);


		c.gridx = 2;
		c.weightx = 0.;
		c.insets = new Insets(200, 0, 10, 10);
		panelconnexion.add(boutonAnnule, c);

		this.setMinimumSize(new Dimension(400, 600));
		this.add(panelconnexion);
		//   panelconnexion.setLocationRelativeTo(null);

		//ajout des listeners sur les boutons 
		boutonOk.addActionListener(this);
		boutonAnnule.addActionListener(this);
		//Action pour les mdp liÃ©es au bouton ok
		boutonOk.setActionCommand(OK);
		boutonAnnule.setActionCommand(Annule);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton leBoutonClique = (JButton) e.getSource();

		// on cherche qui a parlé // <== wut???
		// Object sourceEvennement = e.getSource();
		if (leBoutonClique == boutonOk) {
			System.out.println("Bouton OK");
			soundfx.playSound("sound/valider.wav");
			if ((champEmail1.getText().length() == 0) ||
					(champEmail2.getText().length() == 0)   )
			{
				String message = "un ou plusieurs champs sont vides";
				ImageIcon img = new ImageIcon("img/chat_pas_content.png");
				JOptionPane.showMessageDialog(null, message, "ATTENTION ERREUR", JOptionPane.ERROR_MESSAGE, img) ;
			}
			else
			{
				System.out.println("création d'une nouvelle partie (pas encore commencée)");
				
				// on essaie de connecter le premier joueur
				try
				{
					int id_joueur = bdd.ConnecterJoueur(champEmail1.getText(), new String(champMdp1.getPassword())/*.toString()*/);
					System.out.println(champEmail1.getText()+" "+new String(champMdp1.getPassword()/*.toString()*/));
//					int id_joueur = bdd.ConnecterJoueur("a@a.ab", "1234512345"); // TEST
					System.out.println("id joueur 1 = "+id_joueur);
					if (id_joueur != -1)
					{
						j1 = bdd.InfosJoueur(id_joueur);
//						j1.setId(id_joueur);
						// si le joueur arrive à se connecter, on écrit ses id dans un fichier
						rw.ecrireString(champEmail1.getText()+" ; "+new String(champMdp1.getPassword()), 
										"file/sav.ttt", 
										"UTF-8", 
										"overwrite");
					}
					System.out.println("le premier joueur est connecté : "+j1.getpseudo()+" ; id : "+j1.getId());
				} catch (SQLException e1)
				{
					System.out.println("connexion impossible pour le premier joueur");
					e1.printStackTrace();
				}
				
				// on essaie de connecter le deuxième joueur
				try
				{
					int id_joueur = bdd.ConnecterJoueur(champEmail2.getText(), new String(champMdp2.getPassword())/*.toString()*/);
					System.out.println("id joueur 2 = "+id_joueur);
					if (id_joueur != -1)
					{
						j2 = bdd.InfosJoueur(id_joueur);
//						j2.setId(id_joueur);
						// si le joueur arrive à se connecter, on écrit ses id dans un fichier
						rw.ecrireString(champEmail2.getText()+" ; "+new String(champMdp2.getPassword()), 
										"file/sav.ttt", 
										"UTF-8", 
										"append");
					}					
					System.out.println("le deuxieme joueur est connecté : "+j2.getpseudo()+" ; id : "+j2.getId());
				} catch (SQLException e1)
				{
					System.out.println("connexion impossible pour le deuxième joueur");
					e1.printStackTrace();
				}
				new VueChoixJeu();
				this.dispose();
			}
		}
		
		
		else if (leBoutonClique == boutonAnnule) {
			System.out.println("Bouton ANNULE");
			soundfx.playSound("sound/annuler.wav");
			//			champEmail1.setText("");
			//			champEmail2.setText("");
			//			champMdp1.setText("");
			//			champMdp2.setText("");
			this.dispose();
			new vueDepart();
		}
		//		else	{//passage ÃƒÂ©cran de jeux
		//					}
	}
	
//	public Partie getPartie()
//	{
//		return partie;
//	}
//
//
//	public void setPartie(Partie partie)
//	{
//		this.partie = partie;
//	}
}


