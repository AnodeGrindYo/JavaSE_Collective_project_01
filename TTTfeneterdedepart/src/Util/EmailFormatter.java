package Util;
//package EmailFormatter;

/*
 * @author Adrien Godoy
 */

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JOptionPane;

public class EmailFormatter extends AbstractFormatter 
{
	JOptionPane jop1 = new JOptionPane();
	@Override public Object stringToValue(String string) throws ParseException
	{
		Matcher matcher = regexp.matcher(string);
		if (matcher.matches())
			return string;
		ImageIcon img = new ImageIcon("img/chat_pas_content.png");
		jop1.showMessageDialog(null, "L'adresse mail n'a pas un format valide", "Erreur", JOptionPane.ERROR_MESSAGE, img);
		throw new ParseException("Pas une adresse mail valide", 0); //
	}
	@Override public String valueToString(Object value) 
	{
		return (String) value;
	} 
	final private Pattern regexp = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE); 
}
