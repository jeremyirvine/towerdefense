package data;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon
{

	private float x, y, timeSinceLastShot, fireRate, angle;
	private int width, height, damage, range;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;

	public TowerCannon(Texture baseTexture, Texture cannonTexture, Tile startTile, int damage, int range,
			ArrayList<Enemy> enemies)
	{
		this.startTile = startTile;
		this.baseTexture = baseTexture;
		this.cannonTexture = cannonTexture;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.damage = damage;
		this.range = range;
		this.fireRate = 3;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
		// this.target = acquireTarget();
		// this.angle = calculateAngle();
	}

	public void updateEnemyList(ArrayList<Enemy> newList)
	{
		enemies = newList;
	}

	public void update()
	{
		if (!targeted)
			target = acquireTarget();

		if (target == null || !target.isAlive())
			targeted = false;

		timeSinceLastShot += Delta();
		if (timeSinceLastShot > fireRate)
			shoot();

		for (Projectile p : projectiles)
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
		Enemy closest = null;

		float closestDistance = 10000;
		for (Enemy e : enemies)
		{
			if (isInRange(e) && findDistance(e) < closestDistance)
			{
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		if (closest != null)
			targeted = true;
		return closest;
	}

	private boolean isInRange(Enemy e)
	{
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}

	private float findDistance(Enemy e)
	{
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	private void shoot()
	{
		timeSinceLastShot = 0;
		projectiles.add(new Projectile(loadTexture("bullet"), target, x + Game.tileSize / 2 - Game.tileSize / 4,
				y + Game.tileSize / 2 - Game.tileSize / 4, 32, 32, 600, damage));
	}

	public void draw()
	{
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);

	}

}
