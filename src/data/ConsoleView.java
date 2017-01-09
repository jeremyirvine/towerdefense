package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import helpers.Announcer;
import helpers.Clock;
import helpers.LogLevel;

class ConsoleView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	ArrayList<String> values = new ArrayList<String>();
	DefaultListModel<String> model = new DefaultListModel<>();
	

	
	public ConsoleView()
	{
		/*
		 * JFrame.
		 */
		setSize(600,600);//Size of JFrame
		setResizable(false);
//		setDefaultCloseOperation(JFrame.);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 552, 484, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 600, 552);
		getContentPane().add(scrollPane);
		
		JList list = new JList(model);
//		list.setModel(new AbstractListModel() {
//			
//			public int getSize() {
//				return values.size();
//			}
//			public Object getElementAt(int index) {
//				return values.get(index);
//			}
//		});
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
					Announcer.printf("Too many arguments", LogLevel.WARN);
				if(!found) 
					Announcer.printf("Command not recognized \""+cmd.split(" ")[0]+"\"", LogLevel.WARN);
			}
		});
		btnNewButton.setBounds(483, 552, 117, 29);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}
	
	public void addConsoleItem(String msg)
	{
		model.addElement(msg);
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
				Announcer.printf("Set timescale to " + args);
				return true;
			case checkTimeScale:
				Announcer.printf("Current timescale: " + Clock.multiplier + "f");
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
					Announcer.printf("List of usable commands: ");
					Announcer.printf(out);
				}
				else
				{
					for(ConsoleCommands cc : ConsoleCommands.values())
					{
						if(cc.textname.equalsIgnoreCase(args))
						{
							Announcer.printf("\"" + args + "\" information:");
							Announcer.printf(cc.helpToolTip);
							return true;
						}
						else
						{
							Announcer.printf("\""+args+"\" is not a valid command", LogLevel.WARN);
							return true;
						}
					}
				}
				return true;
		}
	}
	
}