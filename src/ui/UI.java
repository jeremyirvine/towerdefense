package ui;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class UI 
{

	private ArrayList<Button> buttonList;
	
	public UI()
	{
		buttonList = new ArrayList<Button>();
	}
	
	public void addButton(String name, String textureName, int x, int y)
	{
		buttonList.add(new Button(name, loadTexture(textureName), x, y));
	}
	
	public void addButton(String name, Texture texture, int x, int y, int width, int height)
	{
		buttonList.add(new Button(name, texture, x, y, width, height));
	}
	
	public void draw()
	{
		for(Button b : buttonList)
			DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
	}
	
}
