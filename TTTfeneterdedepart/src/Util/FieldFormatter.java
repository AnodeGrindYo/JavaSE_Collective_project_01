package Util;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
public class FieldFormatter extends AbstractFormatter 
{
	JOptionPane jop1 = new JOptionPane();
	@Override public Object stringToValue(String string) throws ParseException
	{
		Matcher matcher = regexp.matcher(string);
		if (matcher.matches())
			return string;
		ImageIcon img = new ImageIcon("img/chat_pas_content.png");
		jop1.showMessageDialog(null, "seuls les caractères alphanumériques sont autorisés", "Erreur", JOptionPane.ERROR_MESSAGE, img);
		throw new ParseException("Caractères non autorisés", 0); // remplacer par une fenÃªtre d'erreur
	}
	@Override public String valueToString(Object value) 
	{
		return (String) value;
	} 
	final private Pattern regexp = Pattern.compile("^[a-zA-Z0-9._]*$");
}
