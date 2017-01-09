package data;

import java.util.HashMap;
import java.util.Map;

public enum TileType 
{

	Null("water", false, -1, false, "3"),
	Grass("grass", true, 0, true, "0"), 
	Dirt("dirt", false, 1, true, "1"),
	Water("water", false, 2, true, "2");
	
	String textureName;
	boolean buildable;
	private int id;
	boolean placeable;
	String fileID;
	
	private static Map<Integer, TileType> map = new HashMap<Integer, TileType>();
	
	static {
		for (TileType t : TileType.values())
		{
			map.put(t.id, t);
		}
	}
	
	public static int getAmtTiles()
	{
		return TileType.values().length;
	}
	
	public static Map<Integer, TileType> getTileMap()
	{
		return map;
	}
	
	TileType(String textureName, boolean buildable, int id, boolean placeable, String fileID)
	{
		this.textureName = textureName;
		this.buildable = buildable;
		this.id = id;
		this.placeable = placeable;
		this.fileID = fileID;
	}
	
	
	
	public static TileType getTileByID(int id)
	{
		return map.get(id);
	}
	
	public String getFileID() 
	{
		return fileID;
	}
	
}
