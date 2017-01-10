package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.loadTexture;

import org.newdawn.slick.opengl.Texture;

import helpers.Vector2f;

public class Tile
{

	private float width, height;
	private float x, y;
	private Texture texture;
	private TileType type;

	public Tile(float x, float y, float width, float height, TileType type)
	{
		this.texture = loadTexture(type.textureName);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;

	}

	public void draw()
	{
		DrawQuadTex(texture, x, y, width, height);
	}

	public float getX()
	{
		return x;
	}

	public void setX(float var)
	{
		x = var;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float var)
	{
		y = var;
	}

	public Vector2f getLocation()
	{
		return new Vector2f(x, y);
	}

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}

	public TileType getType()
	{
		return type;
	}

	public void setType(TileType type)
	{
		this.type = type;
	}

	public int getXPlace()
	{
		return (int) x / TILE_SIZE;
	}

	public int getYPlace()
	{
		return (int) y / TILE_SIZE;
	}

}
