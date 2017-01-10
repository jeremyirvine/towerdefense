package data;

import static helpers.Artist.BeginSession;
import static helpers.Artist.DestroySession;
import static helpers.Artist.FRAME_CAP;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.GL_VENDOR;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import org.lwjgl.opengl.Display;

import helpers.Announcer;
import helpers.Clock;
import helpers.LogLevel;
import helpers.StateManager;

public class TowerSomething
{

	public static int enemyLimit = 50;

	public TowerSomething()
	{
		Announcer.printf("Starting session...", LogLevel.INFO);
		BeginSession();

		Announcer.printf("Enemy Limit: " + enemyLimit);
		Announcer.printf("Using OpenGL ver " + glGetString(GL_VERSION));
		Announcer.printf("Graphics Enabler: (" + glGetString(GL_VENDOR) + ") " + glGetString(GL_RENDERER));
		
		
		// Game game = new Game(map);
		while (!Display.isCloseRequested())
		{
			Clock.update();

			// game.update();
			StateManager.update();

			Display.update();
			Display.sync(FRAME_CAP);
		}

		DestroySession();
	}
	
	public static void main(String args[])
	{
		Announcer.initConsole(args);
		new TowerSomething();
	}
}
