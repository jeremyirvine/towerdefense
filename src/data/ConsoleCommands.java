package data;

import helpers.Announcer;
import helpers.Clock;

public enum ConsoleCommands 
{
	
	setTimeScale("setTimeScale","Sets the timescale on which everything (movement, vector calcualations) is based on"),
	setPlayerHealth("setPlayerHealth", "Sets the player's health"),
	goldMaster("goldMaster", "God Mode"),
	checkTimeScale("checkTimeScale", "Prints the current timeScale"),
	exit("exit", "Exits the whole program"),
	help("help", "Prints out helpful information"),
	getState("getState", "Gets the current gameState (MAINMENU, GAME, EDITOR)");
	
	public String textname;
	public String helpToolTip;
	
	
	ConsoleCommands(String textname, String helpToolTip){
		this.textname = textname;
		this.helpToolTip = helpToolTip;
	}
	
}
