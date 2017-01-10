package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.loadTexture;

public class Game
{

	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	
	
	//Temp variables
	
	
	public Game(int[][] map)
	{
		grid = new TileGrid(map);
		waveManager = new WaveManager(new Enemy(grid.getTile(1,3), TILE_SIZE, TILE_SIZE, 100, loadTexture("ufo"), grid, 25), 
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
