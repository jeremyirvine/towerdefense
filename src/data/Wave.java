package data;

import static helpers.Clock.Delta;

import java.util.ArrayList;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;
	private int enemyCount;
	private boolean waveCompleted;

	public Wave(Enemy enemyType, float spawnTime, int enemyCount) 
	{
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		this.enemyCount = enemyCount;
		this.enemyList = new ArrayList<Enemy>();
		this.timeSinceLastSpawn = 0;
		this.waveCompleted = false;
		
		spawn();
	}

	public void removeEnemy(Enemy enemy) 
	{
		enemyList.remove(enemy);
	}

	public void update() 
	{
		if (enemyList.size() > TowerSomething.enemyLimit)
			enemyList.remove(0);

		boolean allEnemiesDead = true;
		if(enemyList.size() < enemyCount) 
		{
			timeSinceLastSpawn += Delta();
			if (timeSinceLastSpawn > spawnTime) 
			{
				spawn();
				timeSinceLastSpawn = 0;
			}
		}

		for (Enemy e : enemyList) 
			if (e.isAlive()) 
			{
				allEnemiesDead = false;
				e.update();
				e.draw();
			}
		if(allEnemiesDead)
			waveCompleted = true;
	}

	public void spawn() 
	{
		enemyList.add(new Enemy(enemyType.getStartTile(), enemyType.getWidth(), enemyType.getHeight(),
				enemyType.getSpeed(), enemyType.getTexture(), enemyType.getTileGrid(), enemyType.getHealth()));
		
	}
	
	public boolean isCompleted()
	{
		return waveCompleted;
	}
	
	public ArrayList<Enemy> getEnemyList() 
	{
		return enemyList;
	}

}
