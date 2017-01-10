package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import data.Tile;
import data.TileGrid;
import data.TileType;

public class MapUtils 
{

	public static void saveMap(String mapName, TileGrid data)
	{
		String mapData = "";
		for(int i = 0; i < data.getTilesWide(); i++)
		{
			for(int j = 0; j < data.getTilesHigh(); j++)
			{
				mapData += getTileID(data.getTile(i, j));
			}
		}
		
		try 
		{
			File file = new File(mapName + ".tsmd");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static TileType getTileType(String id)
	{
		for(TileType t : TileType.values())
		{
			if(t.getFileID().equals(id))
			{
				return t;
			}
		}
		return TileType.Null;
	}
	
	public static TileGrid loadMap(String fileName)
	{
		TileGrid grid = new TileGrid();
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName + ".tsmd"));
			String data = br.readLine();
			for(int i = 0; i < grid.getTilesWide(); i++)
			{
				for(int j = 0; j < grid.getTilesHigh(); j++)
				{
					grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return grid;
	}
	
	public static String getTileID(Tile t)
	{
		return t.getType().getFileID();
	}
	
}
