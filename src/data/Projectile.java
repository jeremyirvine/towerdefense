package data;

import static helpers.Artist.CheckCollision;
import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

import org.newdawn.slick.opengl.Texture;

import helpers.Announcer;
import helpers.Vector2f;

public class Projectile
{

	private Texture texture;
	private float x, y, width, height, speed, xVelocity, yVelocity;
	private int damage;
	private Enemy target;
	private boolean alive;
	private Vector2f pos;
	private Vector2f size;

	public Projectile(Texture texture, Enemy target, float x, float y, float width, float height, float speed,
			int damage)
	{
		this.texture = texture;

		this.x = x;
		this.y = y;
		this.pos = new Vector2f(x, y);

		this.width = width;
		this.height = height;
		this.size = new Vector2f(width, height);

		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();
	}

	private void calculateDirection()
	{
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x + Game.tileSize / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y + Game.tileSize / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;

		if (target.getX() < x)
			xVelocity *= -1;
		if (target.getY() < y)
			yVelocity *= -1;
	}

	public void update()
	{
		if (alive)
		{
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			pos.setX(x);
			pos.setY(y);

			if (CheckCollision(pos, size, target.getPos(), target.getSize()))
			{
				target.damage(damage);
				alive = false;
			}
			draw();
		}
	}

	public void draw()
	{
		DrawQuadTex(texture, x, y, 32, 32);
	}

	public Vector2f getSize()
	{
		return size;
	}

	public Vector2f getPos()
	{
		return pos;
	}

}
