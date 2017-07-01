package Connect4;
/**
 * @author Adrien Godoy
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Accueil.vueDepart;
import Util.soundPlayer;

/**
 * Created by hanshenrik on 04/11/14.
 */
public class C4Vue implements Observer, ActionListener 
{
	/*
	 * gestion de l'affichage l'heure dans un thread séparé
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
	            fieldHeure.setText(horloge);
	            
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
	
	/*
	 * données
	 */
    private C4Model model;
    private C4Control controller;
    private JFrame framePlateau;
    private JPanel panCommunication;
    private JPanel panBoutons;
    private C4Plateau panPlateau;

    // panel statistiques
    private JTextField fieldHeure = new JTextField("heure");
    private JTextField fieldScore = new JTextField();
    private JTextField fieldProchainJoueur = new JTextField();
    private JTextField fieldPremierJoueur = new JTextField();
    private JTextField fieldGagnant = new JTextField();
    private JTextField fieldInfo = new JTextField();
    private JLabel lblScore = new JLabel("Score");
    private JLabel lblHeure = new JLabel ("");
    private JLabel lblProchainJoueur = new JLabel("Joueur Suivant :");
    private JLabel lblPremierJoueur = new JLabel("premier à jouer :");
    private JLabel lblGagnantPrec = new JLabel("précédent gagnant :");
    private JLabel lblInfo = new JLabel("Infos");

    // boutons
    private JButton nvllePartie = new JButton("Nouvelle partie");
    private JButton quit = new JButton ("Quitter");

    private static final Dimension PAN_STAT_DIM = new Dimension(400, 400);
    private static final Dimension PAN_BOUTON_DIM = new Dimension(64, 64);
    private static final Dimension PAN_PLATEAU_DIM = new Dimension(448, 384);
    
    soundPlayer soundfx = new soundPlayer();
    
    /*
     * Constructeur
     */
    public C4Vue(C4Model model, C4Control controller)  
    {
        this.model = model;
        model.addObserver(this);
        this.controller = controller;
        
        framePlateau = new JFrame("Connect 4");
        
        framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container containerDispoGenerale = framePlateau.getContentPane();

        /***************************************
         * Panel de dialogue
         ***************************************/
        panCommunication = new JPanel();
        panCommunication.setLayout(new GridLayout(8,2));
        
        fieldHeure.setEditable(false);
        fieldScore.setEditable(false);
        fieldProchainJoueur.setEditable(false);
        fieldPremierJoueur.setEditable(false);
        fieldGagnant.setEditable(false);
        fieldInfo.setEditable(false);
        
        panCommunication.add(lblHeure);
        panCommunication.add(fieldHeure);
        panCommunication.add(lblScore);
        panCommunication.add(fieldScore);
        panCommunication.add(lblProchainJoueur);
        panCommunication.add(fieldProchainJoueur);
        panCommunication.add(lblPremierJoueur);
        panCommunication.add(fieldPremierJoueur);
        panCommunication.add(lblGagnantPrec);
        panCommunication.add(fieldGagnant);
        panCommunication.add(lblInfo);
        panCommunication.add(fieldInfo);
        panCommunication.setPreferredSize(PAN_STAT_DIM);
        
        /***************************************
         * Panel du plateau
         ***************************************/
        panPlateau = new C4Plateau(model, controller);
        panPlateau.setPreferredSize(PAN_PLATEAU_DIM);
        
        /***************************************
         * Panel des boutons
         ***************************************/
        panBoutons = new JPanel();
        panBoutons.setLayout(new GridLayout(1,4));

        nvllePartie.addActionListener(this);
        quit.addActionListener(this);
        panBoutons.add(nvllePartie);
        panBoutons.add(quit);

        panBoutons.setPreferredSize(PAN_BOUTON_DIM);
        
        /***************************************
         * ajout des panels au panel principal
         ***************************************/
        containerDispoGenerale.add(panPlateau, BorderLayout.WEST);
        containerDispoGenerale.add(panCommunication, BorderLayout.EAST);
        containerDispoGenerale.add(panBoutons, BorderLayout.SOUTH);
        
        /***************************************
         * cosmetique
         ***************************************/
        // change la couleur des fonds
        panCommunication.setBackground(Color.BLACK);
        quit.setBackground(Color.BLACK);
        nvllePartie.setBackground(Color.BLACK);
        fieldHeure.setBackground(Color.BLACK);
        fieldScore.setBackground(Color.BLACK);
        fieldGagnant.setBackground(Color.BLACK);
        fieldInfo.setBackground(Color.BLACK);
        fieldPremierJoueur.setBackground(Color.BLACK);
        fieldProchainJoueur.setBackground(Color.BLACK);
        fieldScore.setBackground(Color.BLACK);
        // change la couleur de la police
        lblScore.setForeground(Color.WHITE);
        lblProchainJoueur.setForeground(Color.WHITE);
        lblPremierJoueur.setForeground(Color.WHITE);
        lblGagnantPrec.setForeground(Color.WHITE);
        lblInfo.setForeground(Color.WHITE);
        quit.setForeground(Color.WHITE);
        nvllePartie.setForeground(Color.WHITE);
        fieldHeure.setForeground(Color.GREEN);
        fieldScore.setForeground(Color.GREEN);
        fieldGagnant.setForeground(Color.GREEN);
        fieldInfo.setForeground(Color.GREEN);
        fieldPremierJoueur.setForeground(Color.GREEN);
        fieldProchainJoueur.setForeground(Color.GREEN);
        fieldScore.setForeground(Color.GREEN);
        // alignements
        fieldHeure.setHorizontalAlignment(JTextField.CENTER);
        fieldScore.setHorizontalAlignment(JTextField.CENTER);
        fieldGagnant.setHorizontalAlignment(JTextField.CENTER);
        fieldInfo.setHorizontalAlignment(JTextField.CENTER);
        fieldPremierJoueur.setHorizontalAlignment(JTextField.CENTER);
        fieldProchainJoueur.setHorizontalAlignment(JTextField.CENTER);
        fieldScore.setHorizontalAlignment(JTextField.CENTER);
        
        framePlateau.getContentPane().setBackground(Color.BLACK);
        framePlateau.pack();
        framePlateau.setLocationRelativeTo(null);
        framePlateau.setResizable(false);
        framePlateau.setVisible(true);
        date raffraichDate = new date();
        controller.setVue(this);
        update(model, null);
        framePlateau.setLocationRelativeTo(null);
    }

    /*
     * Méhtodes 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    
    public void update(java.util.Observable o, Object arg) 
    {
        fieldScore.setText(model.getScore());
        // clolre le score de la couleur du joueur gaganant
        if (model.quiGagne() == C4JoueursPartie.JOUEUR1)
        {
        	fieldScore.setForeground(Color.BLUE);
        }
        else if (model.quiGagne() == C4JoueursPartie.JOUEUR2)
        {
        	fieldScore.setForeground(Color.RED);
        }
        else
        {
        	fieldScore.setForeground(Color.GREEN);
        }
        
        fieldProchainJoueur.setText(model.getNextJoueur() + "");
        // clolore le jtextfield fieldProchainJoueur de la couleur du joueur qui doit jouer
        if(model.getNextJoueur() == C4JoueursPartie.JOUEUR1)
        {
        	fieldProchainJoueur.setForeground(Color.BLUE);
        }
        else
        {
        	fieldProchainJoueur.setForeground(Color.RED);
        }
        
        
        fieldPremierJoueur.setText(model.getJoueurQuiCommence() + "");
        if (model.getJoueurQuiCommence() == C4JoueursPartie.JOUEUR1)
        {
        	fieldPremierJoueur.setForeground(Color.BLUE);
        }
        else
        {
        	fieldPremierJoueur.setForeground(Color.RED);
        }
        
        if (model.getGagnantPrecedent() == null)
        {
        	fieldGagnant.setText("aucun");
        }
        else
        {
        	fieldGagnant.setText(model.getGagnantPrecedent() + "");
        	// colore fieldGagnant de la couleur du joueur ayant gagné la dernière manche
        	if (model.getGagnantPrecedent() == C4JoueursPartie.JOUEUR1)
        	{
        		fieldGagnant.setForeground(Color.BLUE);
        	}
        	else
        	{
        		fieldGagnant.setForeground(Color.RED);
        	}
        }
        framePlateau.repaint();
    }

    public void setFieldMsg(String message) 
    {
        fieldInfo.setText(message);
    }

    public void setAutoriseNvllePartie(boolean b) 
    {
        nvllePartie.setEnabled(b);
    }

    public void actionPerformed(ActionEvent event) 
    {
    	
        if (event.getSource() == nvllePartie) 
        {
        	soundfx.playSound("sound/blip.wav");
            controller.nvllePartie();
        }
        else if (event.getSource() == quit)
        {
        	new vueDepart();
        	framePlateau.dispose();
        }
    }
}