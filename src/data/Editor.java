package data;

import static helpers.Artist.*;
import static helpers.MapUtils.*;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Announcer;
import helpers.Clock;
import helpers.MapUtils;

public class Editor
{

	private TileGrid grid;
	private int index;
	private TileType[] types;

	public Editor()
	{
		this.grid = loadMap("testmap");
		this.index = 0;
		this.types = new TileType[TileType.getAmtTiles() - 1];

		// + Automatic blocktype initialization

		int i = 0;
		for (TileType t : TileType.values())
		{

			if (t.placeable)
			{
				this.types[i] = t;
				i++;
			}
		}

		// - Automatic blocktype initialization
	}

	public void update()
	{
		grid.draw();

		// Handle mouse input
		if (Mouse.isButtonDown(0))
		{
			setTile();
		}

		// Handle keyboard input
		while (Keyboard.next())
		{
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
			{
				cycleIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState())
			{
				String mapName;
				mapName = JOptionPane.showInputDialog("Map Name");
				saveMap(mapName, grid);
				JOptionPane.showMessageDialog(null, "Map Saved!");
			}
		}
	}

	private void cycleIndex()
	{
		index++;
		if (index > types.length - 1)
		{
			index = 0;
		}
	}

	private void setTile()
	{
		grid.setTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
				types[index]);
	}
}
