package ui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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
	
	public boolean isButtonClicked(String buttonName)
	{
		Button b = getButton(buttonName);
		float mouseY = HEIGHT - Mouse.getY() - 1;
		if(b != null)
			if(Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
					mouseY > b.getY() && mouseY < b.getY() + b.getHeight())
				return true;
		return false;
	}
	
	private Button getButton(String buttonName)
	{
		for(Button b : buttonList)
			if(b.getName().equals(buttonName))
				return b;
		return null;
	}
	
	public void draw()
	{
		for(Button b : buttonList)
			DrawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
	}
	
}
