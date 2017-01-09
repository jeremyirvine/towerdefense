package data;

import static helpers.Artist.loadTexture;

public class Game
{

	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	public static final int tileSize = 64;
	
	//Temp variables
	
	
	public Game(int[][] map)
	{
		grid = new TileGrid(map);
		waveManager = new WaveManager(new Enemy(grid.getTile(1,3), 64, 64, 100, loadTexture("ufo"), grid), 
				2, 2);
		player = new Player(grid, waveManager);
		
	}
	
	public void update()
	{
		grid.draw();
		waveManager.update();
		player.update();
	}
	
}
