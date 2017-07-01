package Util;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FontLoader
{

//	    private static Font ttfBase = null;
//		private static Font font = null;
//	    
//	
//		private static InputStream myStream = null;
//	    private static final String FONT_PATH = "Font/SuperPlumberBrothers.ttf";
//	
//	    public Font createFont() 
//	    {	
//	            try 
//	            {
//	                myStream = new BufferedInputStream(
//	                        new FileInputStream(FONT_PATH));
//	                ttfBase = font.createFont(font.TRUETYPE_FONT, myStream);
//	                font = ttfBase.deriveFont(font.PLAIN, 48f);
//	                
//	            } catch (Exception ex) 
//	            {
//	                ex.printStackTrace();
//	                System.err.println("Font not loaded.");
//	            }
//	            return font;
//	    }
//	    
//	    public static Font getFont()
//		{
//			return font;
//		}
//	
//		public static void setFont(Font font)
//		{
//			FontLoader.font = font;
//		}
//		
//		public static Font getTtfBase()
//		{
//			return ttfBase;
//		}
//	
//		public static void setTtfBase(Font ttfBase)
//		{
//			FontLoader.ttfBase = ttfBase;
//		}

		
		
	File font_file = new File("Font/SuperPlumberBrothers.ttf");
	Font font;

	public Font getFont()
	{
		return font;
	}

	public void setFont(Font font)
	{
		this.font = font;
	}

	FontLoader()
	{
		
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(font_file)).deriveFont(font.PLAIN, 24);
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FontFormatException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}