package Inscription;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Accueil.vueDepart;
import BDD.BDD;
import Connexion.vueConnexionAppli;
import Partie.Joueur;
import Util.EmailFormatter;
import Util.FieldFormatter;
import Util.soundPlayer;

/**
 *
 * @author  Laurent LEDERAC
 */
public class Formulaire extends JFrame implements ActionListener
{
	private JLabel label_nom;
	private JLabel label_prenom;
	private JLabel label_login;
	private JLabel label_mdp;
	private JLabel label_mail;
	private JLabel label_sexe;
	private JLabel label_ddn;

	private JTextField nom;
	private JTextField prenom;
	private JTextField login;
	private JTextField mdp;
	private JFormattedTextField mail;
	//	private JTextField ddn;

	private JButton valider;
	private JButton annuler;

	private JComboBox sexe;

	soundPlayer soundfx = new soundPlayer();

	Joueur j = new Joueur();

	DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
	JFormattedTextField ddn = new JFormattedTextField(format);
	
	Icon icoQuitter = new ImageIcon("img/bttn_quitter.png");
	Icon icoQuitter_HOVER = new ImageIcon("img/bttn_quitter_over.png");
	Icon icoOk = new ImageIcon("img/bttn_valider.png");
	Icon icoOk_HOVER = new ImageIcon("img/bttn_valider_over.png");
	
	BDD bdd = new BDD();


	public Formulaire()
	{
		setTitle("Connection/Inscription");

		//********************************************************
		//************ PANEL CREATION ****************************
		//********************************************************

		JPanel panelprincipal = new JPanel();
		JPanel panelboutons = new JPanel();
		panelboutons.setLayout(new BoxLayout(panelboutons, BoxLayout.X_AXIS));
		JPanel panelchamps = new JPanel(new GridLayout(7,2));


		//********************************************************
		//************ PANEL FIELDS et LABELS ********************
		//********************************************************

		label_nom = new JLabel("Nom : ", SwingConstants.RIGHT);
		label_nom.setBackground(Color.BLACK);
		label_nom.setForeground(Color.green);
		label_prenom = new JLabel("Prenom : ", SwingConstants.RIGHT);
		label_prenom.setBackground(Color.BLACK);
		label_prenom.setForeground(Color.green);
		label_login = new JLabel("Login : ", SwingConstants.RIGHT);
		label_login.setBackground(Color.BLACK);
		label_login.setForeground(Color.green);
		label_mdp = new JLabel("Mot de passe : ", SwingConstants.RIGHT);
		label_mdp.setBackground(Color.BLACK);
		label_mdp.setForeground(Color.green);
		label_mail = new JLabel("Adresse e-mail : ", SwingConstants.RIGHT);
		label_mail.setBackground(Color.BLACK);
		label_mail.setForeground(Color.green);
		label_sexe = new JLabel("Sexe : ", SwingConstants.RIGHT);
		label_sexe.setBackground(Color.BLACK);
		label_sexe.setForeground(Color.green);
		label_ddn = new JLabel("Date de naissance : ", SwingConstants.RIGHT);
		label_ddn.setBackground(Color.BLACK);
		label_ddn.setForeground(Color.green);

		nom = new JFormattedTextField(new FieldFormatter());
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setBackground(Color.BLACK);
		nom.setForeground(new Color(153,255,0));
		prenom = new JFormattedTextField(new FieldFormatter());
		prenom.setHorizontalAlignment(SwingConstants.CENTER);
		prenom.setBackground(Color.BLACK);
		prenom.setForeground(new Color(153,255,0));
		login = new JFormattedTextField(new FieldFormatter());
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBackground(Color.BLACK);
		login.setForeground(new Color(153,255,0));
		mdp = new JPasswordField();
		mdp.setHorizontalAlignment(SwingConstants.CENTER);
		mdp.setForeground(new Color(153,255,0));
		mdp.setBackground(Color.BLACK);
		mail = new JFormattedTextField(new EmailFormatter());
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBackground(Color.BLACK);
		mail.setForeground(new Color(153,255,0));

		Object[] sexes = new Object[]{"Homme", "Femme", "Non déterminé"};
		sexe = new JComboBox(sexes);
		sexe.setAlignmentX(SwingConstants.CENTER);
		sexe.setBackground(Color.BLACK);
		sexe.setForeground(new Color(153,255,0));
		//		ddn = new JTextField();
		ddn.setText("dd-mm-yyyy");
		ddn.setHorizontalAlignment(SwingConstants.CENTER);
		ddn.setBackground(Color.BLACK);
		ddn.setForeground(new Color(153,255,0));

		panelchamps.add(label_nom);
		panelchamps.add(nom);
		panelchamps.add(label_prenom);
		panelchamps.add(prenom);
		panelchamps.add(label_sexe);
		panelchamps.add(sexe);
		panelchamps.add(label_ddn);
		panelchamps.add(ddn);
		panelchamps.add(label_login);
		panelchamps.add(login);
		panelchamps.add(label_mdp);
		panelchamps.add(mdp);
		panelchamps.add(label_mail);
		panelchamps.add(mail);
		panelchamps.setBackground(Color.BLACK);

		//********************************************************
		//************ PANEL BOUTONS *****************************
		//********************************************************

		valider = new JButton(/*"Valider"*/);
		valider.setIcon(icoOk);
		valider.setRolloverIcon(icoOk_HOVER);
		valider.setContentAreaFilled(false);
		valider.setBorderPainted(false);
		annuler = new JButton(/*"Annuler"*/);
		annuler.setIcon(icoQuitter);
		annuler.setRolloverIcon(icoQuitter_HOVER);
		annuler.setContentAreaFilled(false);
		annuler.setBorderPainted(false);
		
		valider.addActionListener(this);
		annuler.addActionListener(this);

		valider.setPreferredSize(new Dimension(150,50));
		annuler.setPreferredSize(new Dimension(150,50));
		
		panelboutons.setLayout(new BorderLayout());
		panelboutons.add(valider, BorderLayout.EAST);
		panelboutons.add(annuler, BorderLayout.WEST);
		panelboutons.setBackground(Color.BLACK);
		

		//********************************************************
		//************ PANEL PRINCIPAL ***************************
		//********************************************************
		panelprincipal.setLayout(new BorderLayout());
		panelprincipal.add(panelchamps, BorderLayout.CENTER);
		panelprincipal.add(panelboutons, BorderLayout.SOUTH);
		panelprincipal.setBackground(Color.BLACK);
//		Container contprincipal = new Container();
//		contprincipal.add(panelboutons, BorderLayout.SOUTH);
//		contprincipal.add(panelchamps, BorderLayout.NORTH);
//		contprincipal.setVisible(true);
//		this.add(contprincipal);

		this.add(panelprincipal);

		this.setResizable(false);
//		this.setLocationRelativeTo(null);
		this.setSize(800,600);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	// Adrien Godoy
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==valider)
		{
			soundfx.playSound("sound/annuler.wav");
			/*
			 * assignation des bidules à une instance de Joueur
			 */
			j.setNom(nom.getText());
			j.setPrenom(prenom.getText());
			j.setpseudo(login.getText());
			j.setmdp(mdp.getText());
			j.setemail(mail.getText());
			System.out.println(sexe.getSelectedItem().toString());
			j.setSexe(sexe.getSelectedItem().toString());
			// récupération de la date... pas simple !!
			try
			{
				java.util.Date date = (java.util.Date) format.parse(ddn.getText());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				j.setdateDeNaissance(sqlDate);
			} catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			// on vérifie que le joueur n'existe pas déjà
			try
			{
				int i = bdd.VerifierJoueur(j);
				// si le joueur n'est pas dans la bdd, on peut l'ajouter
				if (i == -1)
				{
					// ajout du joueur dans la base de données
					bdd.AjouterJoueur(j);
					// on passe à la fenêtre de connexion
					this.dispose();
					new vueConnexionAppli();
				}
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getSource()==annuler)
		{
			soundfx.playSound("sound/annuler.wav");
			this.dispose();
			new vueDepart();
		}
	}

	//	public static void main(String[] args) 
	//	{
	//		
	//		new Formulaire();
	//	}

}
