package Akari;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class Menu {
	
	//Create menu components
	JFrame menu=new JFrame("Light Up!");
	@SuppressWarnings("rawtypes")
	JComboBox jcb;
	
	//Set default color yellow
	Color color=Color.YELLOW;
	
	//Action listener for map buttons/*
	class MapActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
	    	String m=ae.getActionCommand();
	     	menu.dispose();
	     	try {
				Game g=new Game(m,color);
				g.getFrame().setVisible(true);
			} catch (Exception e) {
				System.out.println("Exception!");
				e.printStackTrace();
			}
			
		}

	}
	
	//Action listener for color selecting JComboBox
	class ComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==jcb) 
			{
				switch(jcb.getSelectedItem().toString())
				{
					case "YELLOW":
						setColor(Color.YELLOW);
						break;
					case "ORANGE":
						setColor(Color.ORANGE);
						break;
					case "RED":
						setColor(Color.RED);
						break;
					case "GREEN":
						setColor(Color.GREEN);
						break;
					case "BLUE":
						setColor(Color.BLUE);
						break;
				}

			}
			
		}

	}

	//Map buttons
	class MyMButton extends JButton{

		private static final long serialVersionUID = 1L;

		public MyMButton(String m) {
			this.setText(m);
			this.setPreferredSize(new Dimension(50,37));
			this.setBackground(Color.GRAY);
			this.setBorder(new LineBorder(Color.LIGHT_GRAY));
			this.setFont(new Font("Arial", Font.BOLD, 24));
	        this.setOpaque(true);
	        this.setFocusPainted(false);
			this.addActionListener(new MapActionListener());
		}
	}
	
	//Set the color of the bulb
	protected void setColor(Color c) {
		 color=c;
	}
	
	protected JFrame getFrame() {
		return menu;
	}
	
	//Menu constructor
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Menu()
	{

		//Create components
    	JPanel panel1=new JPanel(new GridLayout(0,2));
    	JPanel panel2=new JPanel(new GridLayout(2,2));
    	panel1.setBackground(Color.GRAY);
    	panel2.setBackground(Color.GRAY);
    	JButton map1=new MyMButton("Very Easy");
    	JButton map2=new MyMButton("Easy");
    	JButton map3=new MyMButton("Medium");
    	JButton map4=new MyMButton("Hard");
    	JButton map5=new MyMButton("Challenging");
    	JButton map6=new MyMButton("");
    	JSeparator js=new JSeparator();
    	js.setBackground(Color.GRAY);
    	Label l1=new Label("Set bulb color:");
    	l1.setForeground(Color.BLACK);
    	l1.setFont(new Font("Arial",Font.BOLD, 15));
    	String colors[]=new String[] {"YELLOW","ORANGE","RED","GREEN","BLUE" };
    	jcb=new JComboBox(colors);
    	jcb.addActionListener(new ComboListener());
    	jcb.setBackground(Color.LIGHT_GRAY);
    	jcb.setForeground(Color.BLACK);
    	
    	//Add components to menu
    	panel1.add(map1);
    	panel1.add(map2);
    	panel1.add(map3);
    	panel1.add(map4);
    	panel1.add(map5);
    	panel1.add(map6);
    	map6.setEnabled(false);

    	panel2.add(l1);
    	panel2.add(new Label(""));
    	panel2.add(jcb);
    	menu.add(panel1, BorderLayout.NORTH);
    	menu.add(js,BorderLayout.CENTER);
    	menu.add(panel2, BorderLayout.SOUTH);
    	
    	//Set color of each button
    	map1.setForeground(Color.GREEN);
    	map2.setForeground(Color.YELLOW);
    	map3.setForeground(Color.ORANGE);
    	map4.setForeground(Color.RED);
    	map5.setForeground(Color.MAGENTA);
    	
    	//Map buttons by number
    	map1.setActionCommand("1");
    	map2.setActionCommand("2");
    	map3.setActionCommand("3");
    	map4.setActionCommand("4");
    	map5.setActionCommand("5");
    	
    	//Menu frame settings
    	menu.pack();
    	menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	menu.setSize(550,200);
    	menu.setResizable(false);

	}
	
	//Create a menu in main
    public static void main(String[] args) {
    	new Menu().getFrame().setVisible(true);;
    }
	
}
