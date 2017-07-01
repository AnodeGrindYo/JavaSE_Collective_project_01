package Connect4;
/**
 * @author Adrien Godoy
 */


public class C4Control {
    private C4Model model;
    private C4Vue vue;


    public C4Control(C4Model model) 
    {
        this.model = model;
    }

    public void setVue(C4Vue view) 
    {
        this.vue = view;
    }

    public void nvllePartie() 
    {
        model.nouvellePartie();
        vue.setFieldMsg("Nouvelle partie commencée!");
    }

    public void resetScore() 
    {
        vue.setFieldMsg("Score reset!");
    }

    public void joueurJoue(int col) 
    {
        if (model.testPartieFinie())
        {
            vue.setFieldMsg("Partie terminée!");
        }
        else if (model.plateauPlein())
        {
            vue.setFieldMsg("le plateau est plein !");
        }
        else if (model.applyGravity(col) == -1)
        {
            vue.setFieldMsg("la colonne est pleine !");
        }
        else 
        {
            model.jouerCasePleteau(col);
            vue.setFieldMsg("a joué colonne " + col);
        }
    }
}