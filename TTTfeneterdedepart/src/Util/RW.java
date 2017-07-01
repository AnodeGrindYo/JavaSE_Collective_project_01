/**
 * 
 */
package Util;

import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
public class RW
{
	String currentPath = new File("").getAbsolutePath();
	
	public String getCurrentPath()
	{
		return currentPath;
	}
	
	/**
	 * 
	 * @param writeThis is the string to write in the file
	 * @param path is the path where the file is, inluding its name
	 * @param encoding 
	 * @param mode only accepts "append" and "overwrite"
	 */
	public void ecrireString(String writeThis, String path, String encoding, String mode)
	{
		try
		{
			
			switch(mode)
			{
				case "append": // writes at the end of the file
				{
					FileWriter writer = new FileWriter(path, true);
					writer.write(writeThis);
					writer.close();
					break;
				}
				case "overwrite": // deletes the content of the file and write the String writeThis
				{
					PrintStream ps = new PrintStream(path, encoding);
					ps.println(writeThis);
					ps.close();
					break;
				}
				default:
				{
					JOptionPane errorMsg = new JOptionPane();
					errorMsg.showMessageDialog(null, "Le mode d'écriture \""+mode+"\" n'existe pas", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author Adrien Godoy
	 * @param path is where we can find the file we want to read
	 */
	public void lireFichierTxt(String path)
	{
		try
		{
			Scanner sc = new Scanner(new FileReader(path));
			String txt = "";
			while (sc.hasNextLine())
			{
				txt += sc.nextLine() + "\n";
			}
			sc.close();
			System.out.println(txt);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param path is the path where we can find the file
	 * @param splitStr is the sequence used to separate words we want to collect
	 * @return 2d ArrayList of String
	 */
	public ArrayList<ArrayList<String>> extractSeparateWords (String path, String splitStr)
	{
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		try
		{
			Scanner sc = new Scanner(new FileReader(path));
			String line = "";
			while(sc.hasNextLine())
			{
				line = sc.nextLine();
				ArrayList<String> temp = new ArrayList<String>(Arrays.asList(line.split(splitStr)));
				array.add(temp);
			}
			sc.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 
	 * @author Adrien Godoy
	 * @param array is the 2d String ArrayList we want to write ine the file
	 * @param path is where we can find the file
	 * @param encoding
	 * @param mode only accepts "append" and "overwrite"
	 * @param splitStr is the sequence separating words
	 */
	public void write2dStringArrayList(ArrayList<ArrayList<String>> array, String path, String encoding, String mode, String splitStr)
	{
		int i = 0;
		try
		{
			
			switch(mode)
			{
				case "append": // writes at the end of the file
				{
					FileWriter writer = new FileWriter(path, true);
					for (ArrayList<String> al :  array)
					{
						for (String str : array.get(i))
						{
							writer.write(str + splitStr);
						}
						i++;
					}
					writer.close();
					break;
				}
				case "overwrite": // deletes the content of the file and write the String writeThis
				{
					PrintStream ps = new PrintStream(path, encoding);
					for (ArrayList<String> al :  array)
					{
						for (String str : array.get(i))
						{
							ps.print(str + splitStr);
						}
						ps.print("\n");
						i++;
					}
					ps.close();
					break;
				}
				default:
				{
					JOptionPane errorMsg = new JOptionPane();
					errorMsg.showMessageDialog(null, "Le mode d'écriture \""+mode+"\" n'existe pas", "ERROR", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * note: doesn't work if there is another '.' than the one before the extension of the file
	 * @author Adrien Godoy
	 * @param wordToFind
	 * @param replacement
	 * @param path
	 * @param encoding
	 * @param mode
	 * @param splitStr
	 */
	public void replaceWordInFile(String wordToFind, String replacement, String path, String encoding, String splitStr)
	{
		int i = 0;
		int j = 0;
		Boolean found = false;
		// on récupère les champs/mots du fichier dans un ArrayList 2d de String
		ArrayList<ArrayList<String>> array = extractSeparateWords (path, splitStr);
		// on parcourt ce tableau 2d et on compare chaque string avec le string recherché
		// si on le trouve, on le remplace
		for (ArrayList<String> al :  array)
		{
			j = 0;
			if (found == true)
			{
				break;
			}
			for (String str : al)
			{
				if (str.equals(wordToFind))
				{
					System.out.println("le mot \""+wordToFind+"\" a été trouvé");
					array.get(i).set(j, replacement);
					found = true;
					break;
				}
				j++;
			}
			i++;
		}
		/* on écrit l'arraylist 2d dans un nouveau fichier, puis on supprime l'ancien, puis on renomme le nouveau
		* comme l'ancien. Pas utile en soi (quoi que ça permet de se prémunir contre une interruption du programme
		* cela permet aussi de faire évoluer le code plus tard, pour proposer deux modes: remplacer ou archiver l'ancien fichier
		*/
		// définition du nom temporaire du nouveau fichier
		for(i = 0; i < path.length(); i++)
		{
			if (path.charAt(i) == '.')
			{
				break;
			}
		}
		String subPath = path.substring(0, i-1);
		String extension = path.substring(i, path.length()-1);
		subPath += "_copy";
		subPath += extension;
		// écriture dans le nouveau fichier
		write2dStringArrayList(array, subPath, encoding, "overwrite", splitStr);
		// suppression de l'ancien fichier
		File f = new File(path);
		if(f.exists())
		{
			f.delete();
		}
		// renommage du nouveau fichier
		File f2 = new File(subPath);
		if(f2.exists())
		{
			f2.renameTo(new File(path));
		}
	}
	
	
}
