package data;

import static helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

import ui.UI;

public class MainMenu 
{

	private Texture background;
	private UI menuUI;
	
	public MainMenu()
	{
		this.background = loadTexture("mainmenu");
		this.menuUI = new UI();
		menuUI.addButton("Play", "playbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Play", "editorbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f) + 128);
		menuUI.addButton("Play", "quitbutton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f) + 256);
	}
	
	public void update()
	{
		DrawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
	}
	
}
