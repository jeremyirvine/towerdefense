package data;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon 
{

	private float x, y, timeSinceLastShot, fireRate, angle;
	private int width, height, damage;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	
	public TowerCannon(Texture baseTexture, Texture cannonTexture, Tile startTile, int damage, ArrayList<Enemy> enemies)
	{
		this.startTile = startTile;
		this.baseTexture = baseTexture;
		this.cannonTexture = cannonTexture;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.damage = damage;
		this.fireRate = 3;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.target = acquireTarget();
		this.angle = calculateAngle();
	}
	
	public void update()
	{
		timeSinceLastShot += Delta();
		if(timeSinceLastShot > fireRate)
			shoot();
		
		for(Projectile p : projectiles)
			p.update();
		
		angle = calculateAngle();
		draw();
	}
	
	private float calculateAngle()
	{
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}
	
	private Enemy acquireTarget()
	{
		return enemies.get(0);
	}
	
	private void shoot()
	{
		timeSinceLastShot = 0;
		projectiles.add(new Projectile(loadTexture("bullet"), target, x + Game.tileSize / 2 - Game.tileSize / 4, y + Game.tileSize / 2 - Game.tileSize / 4, 32, 32, 600, damage));
	}
	
	public void draw()
	{
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
		
	}
	
}
