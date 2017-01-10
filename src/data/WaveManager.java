package data;

import helpers.Announcer;
import helpers.LogLevel;

public class WaveManager 
{

	private float timeSinceLastWave, enemySpawnTime;
	private int waveNumber, enemiesPerWave;
	private Enemy enemyType;
	private Wave currentWave;
	
	public WaveManager(Enemy enemyType, float enemySpawnTime, int enemiesPerWave)
	{
		this.enemyType = enemyType;
		this.enemiesPerWave = enemiesPerWave;
		this.enemySpawnTime = enemySpawnTime;
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		
		this.currentWave = null;
		
		newWave();
	}
	
	public void update()
	{
		if(!currentWave.isCompleted() && currentWave != null)
			currentWave.update();
		else
			newWave();
	}
	
	public Wave getCurrentWave() 
	{
		return currentWave;
	}
	
	private void newWave()
	{
		currentWave = new Wave(enemyType, enemySpawnTime, enemiesPerWave);
		waveNumber++;
		Announcer.printf("Begginning Wave " + waveNumber, LogLevel.INFO);
	}
}
