package Connect4;
/**
 * 
 */


/**
 * @author Adrien Godoy
 *
 */
public class C4JouerConnect4
{
	public static void jouerConnect4() 
    {
        C4Model model = new C4Model();
        C4Control control = new C4Control(model);
        C4Vue vue = new C4Vue(model, control);
        vue.setFieldMsg("");
    }
}
