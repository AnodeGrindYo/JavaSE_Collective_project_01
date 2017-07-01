package Connect4;
/**
 * @author Adrien Godoy
 */



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class C4Plateau extends JPanel {
//public class Plateau extends JLabel {
	private Color blue = new Color(0, 0, 255);
    private Color red = new Color(255, 0, 0);
    private Color darkGrey = new Color(25, 25, 25);
    private static final int LIGNE = C4Model.Ligne;
    private static final int COLONNE = C4Model.Colonne;
    private static final int LONG = 64;
    private static final int LARG = LONG;
    private static final Dimension PREFERRED_SIZE = new Dimension(LONG, LARG);
//    Image jetonJ1 = Toolkit.getDefaultToolkit().createImage("JetonBleu.png");
//    Image jetonJ2 = Toolkit.getDefaultToolkit().createImage("JetonRouge.png");
    private C4Model model;

    public C4Plateau(C4Model model, final C4Control controller) 
    {
        this.model = model;
        initPlateau();

        addMouseListener
        (
        		new MouseAdapter() 
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                JPanel panel = (JPanel) getComponentAt(e.getPoint());
//            	JLabel panel = (JLabel) getComponentAt(e.getPoint());
                if (panel == null || panel == C4Plateau.this)
                    return;
                int colonne = panel.getX() / LONG;
            }

            @Override
            public void mouseReleased(MouseEvent e) 
            {
                JPanel panel = (JPanel) getComponentAt(e.getPoint());
//            	JLabel panel = (JLabel) getComponentAt(e.getPoint());
                if (panel == null || panel == C4Plateau.this)
                    return;
                int colonne = panel.getX() / LONG;
                controller.joueurJoue(colonne);
                revalidate();
                repaint();
            }
        }
        );
    }
    
    private void initPlateau() 
    {
        setLayout(new GridLayout(LIGNE, COLONNE, 1, 1));
        for (int i = 0; i < LIGNE; i++) 
        {
            for (int j = 0; j < COLONNE; j++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(PREFERRED_SIZE);
                add(panel);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
//    	super.paintComponent(g);
        C4JoueursPartie[][] tabPlateau = model.getPlateau();
        for (int i = 0; i < LIGNE; i++) 
        {
            for (int j = 0; j < COLONNE; j++) 
            {
                if (tabPlateau[i][j] == C4JoueursPartie.JOUEUR1) // si JOUEUR 1 joue
                {
                    this.getComponent( (LIGNE*COLONNE-1) - (i*7) - (COLONNE-1-j) ).setBackground(blue);                    
//                    System.out.println(this.getComponent( (ROWS*COL-1) - (i*7) - (COL-1-j) ));
                }
                else if (tabPlateau[i][j] == C4JoueursPartie.JOUEUR2) // si JOUEUR 2 joue
                {
                    this.getComponent( (LIGNE*COLONNE-1) - (i*7) - (COLONNE-1-j) ).setBackground(red);
//                    System.out.println(this.getComponent( (ROWS*COL-1) - (i*7) - (COL-1-j) ));
                }
                else // cases vides
                {
                    this.getComponent( (LIGNE*COLONNE-1) - (i*7) - (COLONNE-1-j) ).setBackground(darkGrey);
                }
            }
        }
    }
}