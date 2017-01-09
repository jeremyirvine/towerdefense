package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Tile;
import data.TileGrid;

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
//		Announcer.printcon(mapData);
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
	
	public static String getTileID(Tile t)
	{
		return t.getType().getFileID();
	}
	
}
