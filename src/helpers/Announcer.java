package helpers;

import java.io.PrintStream;
import java.util.HashMap;

import data.ConsoleView;

public class Announcer 
{

	private static ConsoleView cv;
	
	public static void printf(String msg, LogLevel lvl)
	{
		String cls = getCallerClassName();
		switch(lvl)
		{
			case WARN:
				String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
				System.err.println(mts);
//				TowerSomething.cv.addConsoleItem(mts, LogLevel.WARN);
				break;
			case INFO:
				mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
				System.out.println(mts);
				break;
			case FATAL:
				mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
				System.err.println(mts);
				break;
		}
		
		
	}
	
	public static void printcon(String msg, LogLevel lvl)
	{
		String cls = getCallerClassName();
		switch(lvl)
		{
			case WARN:
				String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
//				System.err.println(mts);
				cv.addConsoleItem(mts, LogLevel.WARN);
				break;
			case INFO:
				mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
//				System.out.println(mts);
				cv.addConsoleItem(mts, LogLevel.INFO);
				break;
			case FATAL:
				mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
//				System.err.println(mts);
				cv.addConsoleItem(mts, LogLevel.FATAL);
				break;
		}
		
		
	}
	
	
	public static void printf(HashMap<?,?> map)
	{
		String cls = getCallerClassName();
		String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [INFO] " + map;
		System.out.println(mts);
	}
	
	public static void printf(Enum<?> enumz)
	{
		String cls = getCallerClassName();
		String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [INFO] " + enumz;
		System.out.println(mts);
	}
	
	public static void printf(String msg)
	{
		String cls = getCallerClassName();
		String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [INFO] " + msg;
		System.out.println(mts);
	}
	
	public static void printcon(String msg)
	{
		String cls = getCallerClassName();
		String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [INFO] " + msg;
//		System.out.println(mts);
		cv.addConsoleItem(mts, LogLevel.INFO);
	}
	
	 private static String getCallerClassName() { 
	        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	        for (int i=1; i<stElements.length; i++) {
	            StackTraceElement ste = stElements[i];
	            if (!ste.getClassName().equals(Announcer.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
	            	String returnaddr = ste.getFileName();
	            	if (returnaddr.indexOf(".") > 0)
	            	    returnaddr = returnaddr.substring(0, returnaddr.lastIndexOf("."));
	                return returnaddr;
	            }
	        }
	        return null;
	     }
	 
	 public static void initConsole(String[] args)
	 {
			if (args.length == 1)
				if (args[0].equalsIgnoreCase("console"))
				{
					cv = new ConsoleView();

					PrintStream myStream = new PrintStream(System.out)
					{
						@Override
						public void println(String x)
						{
							super.println(x);
							cv.addConsoleItem(x, LogLevel.INFO);
						}
					};
					System.setOut(myStream);
					PrintStream myStream2 = new PrintStream(System.err)
					{
						@Override
						public void println(String x)
						{
							super.println(x);
							cv.addConsoleItem(x, LogLevel.WARN);
						}
					};
					System.setErr(myStream2);
				}
	 }
	
}
