package helpers;

public class Announcer 
{

	public static void printf(String msg, LogLevel lvl)
	{
		String cls = getCallerClassName();
		switch(lvl)
		{
			case WARN:
				String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [" + lvl.type + "] " + msg;
				System.err.println(mts);
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
	
	public static void printf(String msg)
	{
		String cls = getCallerClassName();
		String mts = "[" + Clock.getTimeStamp() + "] [" + cls + "] [INFO] " + msg;
		System.out.println(mts);
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
	
}
