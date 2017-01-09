# TowerDefense
This is a branch off of IndieProgrammer's (https://www.youtube.com/channel/UC0MHs87ehhLOtPQf_bAWDEA) Game Programming Series.

# Extra Features
- [X] Basic console (requires "console" command line argument)
- [X] Custom announcer (System.out.println replacement)
- [X] Support for custom commands (console)

# Adding a command to the console
To add a command to the console, visit the ConsoleCommands class and type in the name of the command, e.g. setTime("setTime"). 

Then travel to the ConsoleView, scroll down till you see interpretCommand, add your command, e.g. case setTime: and remember to return true once you complete the command
