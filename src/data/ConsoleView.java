package data;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import helpers.Announcer;
import helpers.Clock;
import helpers.LogLevel;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class ConsoleView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	ArrayList<String> values = new ArrayList<String>();
	DefaultListModel<Log> model = new DefaultListModel<>();
	

	
	public ConsoleView()
	{
		/*
		 * JFrame.
		 */
		setSize(600,600);//Size of JFrame
		setResizable(false);
//		setDefaultCloseOperation(JFrame.);
		getContentPane().setLayout(null);
		
		Action textFieldAction = new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cmd = textField.getText();
				textField.setText("");
				
				boolean found = false;
				boolean tooManyPar = false;
				if(cmd.split(" ").length >= 3)
					tooManyPar = true;
				for(ConsoleCommands cc : ConsoleCommands.values())
				{
					if(!tooManyPar && cc.textname.equalsIgnoreCase(cmd.split(" ")[0]))
					{
						if(cmd.split(" ").length == 1) {
							if(interpretCommand(cc, "")) 
							{
								found = true;
							}
						}
						else if(cmd.split(" ").length == 2)
						{
								if(interpretCommand(cc, cmd.split(" ")[1]))
									found = true;
						}
						return;
					} else if (tooManyPar)
					{
						return;
					}
				}
				if(tooManyPar)
					Announcer.printcon("Too many arguments", LogLevel.WARN);
				if(!found) 
					Announcer.printcon("Command not recognized \""+cmd.split(" ")[0]+"\"", LogLevel.WARN);
			}
		};
		
		textField = new JTextField();
		textField.addActionListener(textFieldAction);
		textField.setBounds(0, 552, 484, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 600, 552);
		getContentPane().add(scrollPane);
		
		JList list = new JList(model);
		list.setCellRenderer(new LogCellRenderer());
		list.setVisibleRowCount(4);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cmd = textField.getText();
				textField.setText("");
				
				boolean found = false;
				boolean tooManyPar = false;
				if(cmd.split(" ").length >= 3)
					tooManyPar = true;
				for(ConsoleCommands cc : ConsoleCommands.values())
				{
					if(!tooManyPar && cc.textname.equalsIgnoreCase(cmd.split(" ")[0]))
					{
						if(cmd.split(" ").length == 1) {
							if(interpretCommand(cc, "")) 
							{
								found = true;
							}
						}
						else if(cmd.split(" ").length == 2)
						{
								if(interpretCommand(cc, cmd.split(" ")[1]))
									found = true;
						}
						return;
					} else if (tooManyPar)
					{
						return;
					}
				}
				if(tooManyPar)
					Announcer.printcon("Too many arguments", LogLevel.WARN);
				if(!found){} 
					Announcer.printcon("Command not recognized \""+cmd.split(" ")[0]+"\"", LogLevel.WARN);
			}
		});
		btnNewButton.setBounds(483, 552, 117, 29);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}
	
	public void addConsoleItem(String msg, LogLevel logType)
	{
		model.addElement(new Log(msg, logType));
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("The Button Works!");
	}
	
	public boolean interpretCommand(ConsoleCommands cmd, String args)
	{
		switch(cmd)
		{
			default:
				return false;
			case setTimeScale:
				Clock.multiplier = Float.parseFloat(args);
				Announcer.printcon("Set timescale to " + args);
				return true;
			case checkTimeScale:
				Announcer.printcon("Current timescale: " + Clock.multiplier + "f");
				return true;
			case exit:
				Announcer.printf("Destroying session...");
				System.exit(0);
				return true;
			case help:
				if(args == "") {
					String out = "";
					for(ConsoleCommands cc : ConsoleCommands.values())
					{
						out += cc.textname + ", ";
					}
					Announcer.printcon("List of usable commands: ");
					Announcer.printcon(out);
					return true;
				}
				else
				{
					for(ConsoleCommands cc : ConsoleCommands.values())
					{
						if(cc.textname.equalsIgnoreCase(args))
						{
							Announcer.printcon("\"" + args + "\" information:");
							Announcer.printcon(cc.helpToolTip);
							return true;
						}
						else
						{
//							Announcer.printcon("\""+args+"\" is not a valid command", LogLevel.WARN);
							continue;
						}
					}
				}
				Announcer.printcon("\""+args+"\" is not a valid command", LogLevel.WARN);
				return false;
			case getState:
				Announcer.printcon("Current GameState: " + StateManager.getState());
				return true;
			case setMap:
				if(StateManager.getState() == GameState.EDITOR)
				{
					if(args != "")
					{
						Announcer.printcon("Loading \"" + args + "\"...");
					}
					else
					{
						Announcer.printcon("Usage: setMap (mapName)", LogLevel.WARN);
					}
				}
				else
				{
					Announcer.printcon("Modification of non-EDITOR maps is not allowed...", LogLevel.WARN);
				}
				return true;
			case setState:
				if(args != "")
				{
					switch (args.toUpperCase()) {
					case "EDITOR":
						Announcer.printcon("Setting State to " + args.toUpperCase() + "...");
						StateManager.setState(GameState.EDITOR);
						return true;
					case "GAME":
						Announcer.printcon("Setting State to " + args.toUpperCase() + "...");
						StateManager.setState(GameState.GAME);
						return true;
					case "MAINMENU":
						Announcer.printcon("Setting State to " + args.toUpperCase() + "...");
						StateManager.setState(GameState.MAINMENU);
						return true;
					}
				}
				Announcer.printcon("State " + args + " not recognized...", LogLevel.WARN);
				return false;
		}
	}
	
	private class Log
	{
		private String msg;
		private LogLevel logLevel;
		
		public Log(String msg, LogLevel logLevel)
		{
			this.msg = msg;
			this.logLevel = logLevel;
		}
		
		public LogLevel getLogLevel() 
		{
			return logLevel;
		}
		
		@Override
		public String toString()
		{
			return msg;
		}
		
	}
		
	private class LogCellRenderer extends JLabel implements ListCellRenderer
	{
		 private final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

		  public LogCellRenderer() 
		  {
			    setOpaque(true);
		  }
		

//		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) 
		{
			
			setText(value.toString());
			Log l = (Log) value;
//			 if (isSelected) {
//			      setBackground(HIGHLIGHT_COLOR);
//			      setForeground(Color.white);
//			    } else {
//			      setBackground(Color.white);
//			      setForeground(Color.black);
//			    }
			if(l.getLogLevel() == LogLevel.FATAL)
			{
				setForeground(Color.red);
				setBackground(Color.white);
			}
			if(l.getLogLevel() == LogLevel.WARN)
			{
				setForeground(Color.orange);
				setBackground(Color.white);
			}
			if(l.getLogLevel() == LogLevel.INFO)
			{
				setForeground(Color.black);
				setBackground(Color.white);
			}
			
			return this;
		}
	}
	
}

