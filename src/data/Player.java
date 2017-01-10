package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.loadTexture;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

public class Player 
{

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<TowerCannon> towerList;
	private boolean leftMouseButtonPressed;
	
	public Player(TileGrid grid, WaveManager waveManager)
	{
		this.grid = grid;
		this.types = new TileType[TileType.getAmtTiles() - 1];
		
		// + Automatic blocktype initialization
		
		int i = 0;
		for (TileType t : TileType.values())
		{
			
			if(t.placeable)
			{
				this.types[i] = t;
				i++;
			}
		}
		
		// - Automatic blocktype initialization
		
		this.waveManager = waveManager;
		this.towerList = new ArrayList<TowerCannon>();
		this.leftMouseButtonPressed = false;
	}
	
	public void update()
	{
		for(TowerCannon t : towerList)
		{
			t.update();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}
		
		//Handle mouse input
		if(Mouse.isButtonDown(0) && !leftMouseButtonPressed)
		{
			towerList.add(new TowerCannon(loadTexture("cannonBase"), loadTexture("cannonGun"), grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY() - 1) / 64),  10, 1000, waveManager.getCurrentWave().getEnemyList()));
//			setTile();
		}
		
		leftMouseButtonPressed = Mouse.isButtonDown(0);
		
		//Handle keyboard input
		while(Keyboard.next())
		{
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
			{
				Clock.changeMultiplier(0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
			{
				Clock.changeMultiplier(-0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState())
			{
				towerList.add(new TowerCannon(loadTexture("cannonBase"), loadTexture("cannonGun"), grid.getTile(5, 2), 10, 1000, waveManager.getCurrentWave().getEnemyList()));
			}
		}
	}
}
