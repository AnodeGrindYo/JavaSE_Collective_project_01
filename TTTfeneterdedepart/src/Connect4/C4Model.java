package Connect4;
/**
 * @author Adrien Godoy
 */


import java.awt.Point;
import java.util.Observable;

import javax.swing.JOptionPane;


public class C4Model extends Observable 
{

    public static final int Ligne = 6;
    public static final int Colonne = 7;
    private boolean ligneGagnante;
    private boolean jeuAvorte;
    private C4JoueursPartie joueurQuiCommence;
    private C4JoueursPartie nextJoueur;
    private C4JoueursPartie gagnant;
    private C4JoueursPartie[][] plateau;
    private int scoreJ1;
    private int scoreJ2;
    private int caseVide;
    
    
    public C4Model() 
    {
        nouvellePartie();
    }

    public String getScore() 
    {
        return scoreJ1 + " - " + scoreJ2;
    }
    
    public C4JoueursPartie quiGagne()
    {
    	if (scoreJ1 > scoreJ2)
    	{
    		return C4JoueursPartie.JOUEUR1;
    	}
    	else if (scoreJ2 > scoreJ1)
    	{
    		return C4JoueursPartie.JOUEUR2;
    	}
    	else
    	{
    		return null;
    	}
    }

    public void nouvellePartie() 
    {
        jeuAvorte = false;
        ligneGagnante = false;
        chgtPremierJoueur();
        nextJoueur = joueurQuiCommence;
        creerPlateau();
        caseVide = Ligne*Colonne;
        setChanged();
        notifyObservers();
    }

    public void finirPartie() 
    {
        jeuAvorte = true;
    }

    public boolean testPartieFinie() 
    {
        return jeuAvorte || ligneGagnante || plateauPlein();
    }


    public int jouerCasePleteau(int colonne) 
    {
        int ligne = applyGravity(colonne);

        if (ligne != -1 && !testPartieFinie()) 
        {
            plateau[ligne][colonne] = nextJoueur;
            caseVide--;
            chgtTour();
            setIsLigneGagnante(ligne, colonne);
            setChanged();
            notifyObservers();
        }
        return ligne;
    }

    public int applyGravity(int col) 
    {
        for (int i = 0; i < Ligne; i++) 
        {
            if (plateau[i][col] == null)
                return i;
        }
        return -1;
    }

   
    private void creerPlateau() 
    {
        plateau = new C4JoueursPartie[Ligne][Colonne];
        for (int i = 0; i < Ligne; i++) 
        {
            for (int j = 0; j < Colonne; j++)
                plateau[i][j] = null;
        }
    }

    public C4JoueursPartie[][] getPlateau() 
    {
        return plateau;
    }

    
    private void chgtTour() 
    {
    	if (nextJoueur == C4JoueursPartie.JOUEUR1)
    	{
    		nextJoueur = C4JoueursPartie.JOUEUR2;
    	}
    	else
    	{
    		nextJoueur = C4JoueursPartie.JOUEUR1;
    	}
    }

    private void chgtPremierJoueur() 
    {
    	if (joueurQuiCommence == C4JoueursPartie.JOUEUR1)
    	{
    		joueurQuiCommence = C4JoueursPartie.JOUEUR2;
    	}
    	else
    	{
    		joueurQuiCommence = C4JoueursPartie.JOUEUR1;
    	}
    }

 
    private void setIsLigneGagnante(int row, int col) 
    {
        ligneGagnante = verifLigne(new Point(row, 0), new Point(row, Colonne - 1)) || // horizontal
        				verifLigne(new Point(0, col), new Point(Ligne - 1, col)) || // vertical
        				verifLigne(getDiagonalFirstPoint(row, col, "southwest"), new Point(Ligne - 1, Colonne - 1)) || // diagonal 1
        				verifLigne(getDiagonalFirstPoint(row, col, "southeast"), new Point(Ligne - 1, 0)); // diagonal 2
    }

    
    private Point getDiagonalFirstPoint(int row, int col, String dir) 
    {
        if (!dir.equals("southwest") && !dir.equals("southeast"))
        {
            throw new IllegalArgumentException("La direction devrait être 'southwest' ou 'southeast'!");
        }
        Point point = new Point(row, col);
        while ( (point.x != 0) && (point.y != 0) && (point.y != Colonne - 1) && (point.x != Ligne - 1)) 
        {
            point.x--;
            if (dir.equals("southwest")) 
            {
                point.y--;
            }
            else if (dir.equals("southeast")) 
            {
                point.y++;
            }
        }
        return point;
    }

    private boolean verifLigne(Point start, Point end) // prévoir dolipranes
    {
        int similarInARow = 0;
        int dirX = 0;
        int dirY = 0;
        C4JoueursPartie jPrecedent = null;
        C4JoueursPartie joueurEnCours;

        if (start.x < end.x)
            dirX = 1;
        if (start.y < end.y)
            dirY = 1;
        else if (start.y > end.y)
            dirY = -1;

        int x = start.x;
        int y = start.y;

        while ( x < Ligne && ( (y < Colonne && dirY >= 0) || (y >= 0 && dirY == -1) ) ) 
        {
            joueurEnCours = plateau[x][y];
            if (joueurEnCours == jPrecedent && joueurEnCours != null)
                similarInARow++;
            else
                similarInARow = 1;

            if (similarInARow == 4) 
            {
                incrementeVictoire(joueurEnCours);
                gagnant = joueurEnCours;
                return true;
            }
            jPrecedent = joueurEnCours;
            x += dirX;
            y += dirY;
        }
        return false;
    }

    
    private void incrementeVictoire(C4JoueursPartie joueurGagnant) 
    {
        if (joueurGagnant == C4JoueursPartie.JOUEUR1)
        {
            scoreJ1++;
            JOptionPane annonceVainqueur = new JOptionPane ();
            annonceVainqueur.showMessageDialog(null, "Joueur 1 a gagné la partie !", "félicitations, ", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (joueurGagnant == C4JoueursPartie.JOUEUR2)
        {
            scoreJ2++;
            JOptionPane annonceVainqueur = new JOptionPane ();
            annonceVainqueur.showMessageDialog(null, "Joueur 2 a gagné la partie !", "félicitations, ", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public C4JoueursPartie getJoueurQuiCommence() 
    {
        return joueurQuiCommence;
    }

    public C4JoueursPartie getNextJoueur() 
    {
        return nextJoueur;
    }

    public C4JoueursPartie getGagnantPrecedent() 
    {
        return gagnant;
    }

    public boolean isLigneGagnante() 
    {
        return ligneGagnante;
    }

    public boolean plateauPlein() 
    {
        return caseVide == 0;
    }
}