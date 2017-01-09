package helpers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.lwjgl.Sys;

public class Clock 
{
	private static boolean paused = false;
	public static long lastFrame, totalTime;
	public static float d = 0, multiplier = 1;
	
	public static long getTime()
	{
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	public static float getDelta()
	{
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		if(delta * 0.001f > 0.05f)
			return 0.05f;
		return delta * 0.001f;
	}
	
	public static String getTimeStamp()
	{
		String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

		return timeStamp;
	}
	
	public static float Delta()
	{
		if(paused)
			return 0;
		else
			return d * multiplier;
	}
	
	public static float TotalTime()
	{
		return totalTime;
	}
	
	public static float Multiplier()
	{
		return multiplier;
	}
	
	public static void update()
	{
		d = getDelta();
		totalTime += d;
	}
	
	public static void changeMultiplier(float time)
	{
		if(multiplier + time < -1 || multiplier + time > 7)
		{
			
		} else
		{
			multiplier += time;
		}
	}
	
	public static void pause()
	{
		paused = !paused;
	}
}
