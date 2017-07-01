package Util;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Adrien Godoy
 *
 */
public class Music implements Runnable
{
//	soundPlayer bckgrndMusic = new soundPlayer();
	
	static Thread trMusic;
	static soundPlayer bckgrndMusic = new soundPlayer();
	static ArrayList <String> playlist = new ArrayList();
	static Boolean PlayingNow = false;


	public Music()
	{
		initPlaylist();
		if (trMusic == null) 
        {
			trMusic = new Thread(this);
			trMusic.start();
        }
	}
	
	
	public void run()
	{
		int i=0;
		PlayingNow = true;
		
		while (PlayingNow==true)
		{
			if (i>=playlist.size())
			{
				i=0;
			}
			bckgrndMusic.playSound(playlist.get(i));
	        try 
	        { 
	        	Thread.sleep(0);
	        } 
	        catch(InterruptedException e)
	        {
	        }
	        i++;
		}
	}
	
	public static void stopMusic()
	{
		
		trMusic.interrupt();
        bckgrndMusic.stopSound();
        setPlayingNow(false);
	}
	
	public static void initPlaylist()
	{
		playlist.add("sound/music2loop.wav");
		playlist.add("sound/music2loop.wav"); // en raison d'une restriction de budget, la playlist sera limitée à un seul morceau
	}
	
	public Boolean getPlayingNow()
	{
		return PlayingNow;
	}


	public static void setPlayingNow(Boolean playingNow)
	{
		PlayingNow = playingNow;
	}
}
