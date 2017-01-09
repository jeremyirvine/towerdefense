package data;

import static helpers.Artist.BeginSession;
import static helpers.Artist.DestroySession;
import static helpers.Artist.FRAME_CAP;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import java.io.PrintStream;

import org.lwjgl.opengl.Display;

import helpers.Announcer;
import helpers.Artist;
import helpers.Clock;
import helpers.LogLevel;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class TowerSomething 
{

	public static int enemyLimit = 50;
	
	public TowerSomething()
	{
		Announcer.printf("Starting session...", LogLevel.INFO);
		BeginSession();		
		
//		int[][] map = {
//			/*0*/	{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			/*1*/	{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			/*2*/	{0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//			/*3*/	{0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//			/*4*/	{0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//			/*5*/	{0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
//			/*6*/	{0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			/*7*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			/*8*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			/*9*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
//			/*10*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0},
//			/*11*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0},
//			/*12*/	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0},
//		};
		
		Announcer.printf("Enemy Limit: " + enemyLimit);
		Announcer.printf("Using OpenGL ver " + glGetString(GL_VERSION));
		Announcer.printf("Graphics Enabler: (" + glGetString(GL_VENDOR) + ") " + glGetString(GL_RENDERER));
//		Game game = new Game(map);
		while(!Display.isCloseRequested())
		{
			Clock.update();

//			game.update();
			StateManager.update();
			
			Display.update();
			Display.sync(FRAME_CAP);
		}
		
		DestroySession();
	}
	
	public static void main(String args[])
	{
		// Begin console
		if(args.length == 1)
			if(args[0].equalsIgnoreCase("console")) {
				ConsoleView cv = new ConsoleView();
				
				PrintStream myStream = new PrintStream(System.out) {
				    @Override
				    public void println(String x) {
				        super.println(x);
				        cv.addConsoleItem(x);
				    }
				};
				System.setOut(myStream);
				PrintStream myStream2 = new PrintStream(System.err) {
				    @Override
				    public void println(String x) {
				        super.println(x);
				        cv.addConsoleItem(x);
				    }
				};
				System.setErr(myStream2);
			}
		
		// Begin game
		new TowerSomething();
		
		
	}
}
