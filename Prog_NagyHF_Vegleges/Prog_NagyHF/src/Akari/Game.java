package Akari;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Game {
	
	//Create game board variables
	public static Cell[][] board;
    int r;
    int c;
    JFrame f;
    
    //Create object to use methods of FileHandling class 
    FileHandling file;

    protected Cell[][] getBoard(){
    	return board;
    }
    
    protected JFrame getFrame() {
		return f;
	}
    
    //add rays from bulb up
    protected void addFromBulbUp(int y, int x,Color color) {
    	while (y > 0)
        {
            y--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
            {
                board[y][x].addLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {
            	addRays(board[y][x],color);
            	board[y][x].getButton().setBackground(color);
            }
            else 
            {
                y = 0;
            }
        }
    }
    
    //add rays from bulb left 
    protected void addFromBulbLeft(int y, int x,Color color) {
        while (x > 0) 
        {
            x--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
            {
                board[y][x].addLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {
            	addRays(board[y][x],color);
            	board[y][x].getButton().setBackground(color);
            }
            else 
            {
                x = 0;
            }
        }
    }
   
    //add rays from bulb down
    protected void addFromBulbDown(int y, int x,Color color) {
        while (y < r - 1) 
        {
            y++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
            {
                board[y][x].addLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {
            	addRays(board[y][x],color);
            	board[y][x].getButton().setBackground(color);
            }
            else 
            {
                y = r;
            }
        }
    }
    
    //add rays from bulb right 
    protected void addFromBulbRight(int y, int x,Color color) {
        while (x < c - 1) 
        {
            x++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
            {
                board[y][x].addLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {
            	addRays(board[y][x],color);
            	board[y][x].getButton().setBackground(color);
            }
            else 
            {
                x = c;
            }
        }
    }
    
	//Add rays according to cell's position
    protected void addRays(Cell cell, Color color) {
        cell.addLight();
        int y = cell.getRow();
        int x = cell.getCol();
        addFromBulbUp(y,x,color);
        addFromBulbLeft(y,x,color);
        addFromBulbDown(y,x,color);
        addFromBulbRight(y,x,color);
        updateBoard(color);
    }
    
    //sub rays from bulb up
    protected void subFromBulbUp(int y, int x, int i, int j, Color color) {

        while (y > 0) 
        {
            y--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit") ) 
            {
                board[y][x].subLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {

            	subRays(board[y][x],color);
            	board[y][x].getButton().setBackground(Color.WHITE);
            	//from prism down
        		board[i][x].setLightLevel(0);
        		if(board[i][x].getType().equals("empty")) 
        		{
        			while(i<r-1)
        			{

                		i++;
                        if (board[i][x].getType().equals("empty") || board[i][x].getType().equals("lit")) 
                        {
                        	board[i][x].addLight();
                        }
                        else
                        {
                        	i=r;
                        }
                	}
        		}
        		else
        		{
        			int k=y;
        			while(k>i) 
        			{
        				k--;
                        if (board[k][x].getType().equals("empty") || board[k][x].getType().equals("lit")) 
                        {
                        	board[k][x].addLight();
                        }
        			}
        		}

            }
            else 
            {
                y = 0;
            }
        }
    }
    
    //sub rays from bulb left
    protected void subFromBulbLeft(int y,int x, int i, int j, Color color) {
        while (x > 0) 
        {
            x--;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
            {
                board[y][x].subLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {
            	subRays(board[y][x],color);
            	board[y][x].getButton().setBackground(Color.WHITE);
            	//from prism right
        		board[y][j].setLightLevel(0);
        		if(board[y][j].getType().equals("empty")) 
        		{
        			while(j<c-1)
                	{

                		j++;
                        if (board[y][j].getType().equals("empty") || board[y][j].getType().equals("lit")) 
                        {
                        	board[y][j].addLight();
                        }
                        else
                        {
                        	j=c;
                        }
                	}	
        		}
        		else
        		{
        			int k=x;
        			while(k>j) 
        			{
        				k--;
                        if (board[y][k].getType().equals("empty") || board[y][k].getType().equals("lit")) 
                        {
                        	board[y][k].addLight();
                        }
        			}
        		}
        			
            	
            }
            else 
            {
                x = 0;
            }
        }
    }
    
    //sub rays from bulb down
    protected void subFromBulbDown(int y, int x, int i, int j,Color color) {
         while (y < r-1) 
         {
             y++;
             if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit")) 
             {
                 board[y][x].subLight();
            
             } 
             else if(board[y][x].getType().equals("prism"))
             {
             	subRays(board[y][x],color);
             	board[y][x].getButton().setBackground(Color.WHITE);
             	//from prism up
         		board[i][x].setLightLevel(0);
        		if(board[i][x].getType().equals("empty")) 
        		{
                 	while(i>0)
                 	{

                 		i--;
                         if (board[i][x].getType().equals("empty") || board[i][x].getType().equals("lit")) 
                         {
                         	board[i][x].addLight();
                         }
                         else
                         {
                         	i=0;
                         }
                 	}
        		}
        		else
        		{
        			int k=i;
        			while(k<i-1) 
        			{
        				k++;
                        if (board[k][x].getType().equals("empty") || board[k][x].getType().equals("lit")) 
                        {
                        	board[k][x].addLight();
                        }
        			}
        		}
             }
             else 
             {
                 y = r;
             }
         }
    }
    
    //sub rays from bulb right
    protected void subFromBulbRight(int y, int x, int i, int j,Color color){
        while (x < c - 1) 
        {
            x++;
            if (board[y][x].getType().equals("empty") || board[y][x].getType().equals("lit") ) 
            {
                board[y][x].subLight();
            } 
            else if(board[y][x].getType().equals("prism"))
            {

            	subRays(board[y][x],color);
            	board[y][x].getButton().setBackground(Color.WHITE);
            	//from prism left
        		board[y][j].setLightLevel(0);
        		if(board[y][j].getType().equals("empty")) 
        		{
                	while(j>0)
                	{

                		j--;
                        if (board[y][j].getType().equals("empty") || board[y][j].getType().equals("lit")) 
                        {
                        	board[y][j].addLight();
                        }
                        else
                        {
                        	j=0;
                        }
                	}
        		}
        		else
        		{
        			int k=x;
        			while(k<j-1) 
        			{
        				k++;
                        if (board[y][k].getType().equals("empty") || board[y][k].getType().equals("lit")) 
                        {
                        	board[y][k].addLight();
                        }
        			}
        		}
            }
            else 
            {
                x = c;
            }
        }
    }
    
  //Sub rays according to cell's position
    protected void subRays(Cell cell, Color color) {
        cell.subLight();
        int y = cell.getRow();
        int x = cell.getCol();

        subFromBulbUp(y,x,y,x,color);
        subFromBulbLeft(y,x,y,x,color);
        subFromBulbDown(y,x,y,x,color);
        subFromBulbRight(y,x,y,x,color);
        
        updateBoard(color);
    }
    
    private void whenLightLeft(int y, int x, Color color, String from) {
		board[y][x].getButton().setBackground(color);
		board[y][x].getButton().setIcon(new ImageIcon("bulbleft.png"));
        board[y][x].setType("bulbleft");
        addFromBulbLeft(y,x,color);
        switch(from) 
        {
        	case"up":
        	{
                subFromBulbUp(y,x,y,x,color);
        		break;
        	}
        	case"right":
        	{
                subFromBulbRight(y,x,y,x,color);
        		break;
        	}
        	case"down":
        	{
                subFromBulbDown(y,x,y,x,color);
        		break;
        	}
        }
        updateBoard(color);
    }

    private void whenLightDown(int y, int x, Color color, String from) {
        board[y][x].getButton().setBackground(color);
        board[y][x].getButton().setIcon(new ImageIcon("bulbdown.png"));
 	   	board[y][x].setType("bulbdown");
        addFromBulbDown(y,x,color);
        switch(from) 
        {
        	case"up":
        	{
                subFromBulbUp(y,x,y,x,color);
        		break;
        	}
        	case"right":
        	{
                subFromBulbRight(y,x,y,x,color);
        		break;
        	}
        	case"left":
        	{
                subFromBulbLeft(y,x,y,x,color);
        		break;
        	}
        }
        updateBoard(color);
    }
    
    private void whenLightRight(int y,int x, Color color, String from) {
        board[y][x].getButton().setBackground(color);
        board[y][x].getButton().setIcon(new ImageIcon("bulbright.png"));
        board[y][x].setType("bulbright");
        addFromBulbRight(y,x,color);
        switch(from) 
        {
        	case"up":
        	{
                subFromBulbUp(y,x,y,x,color);
        		break;
        	}
        	case"down":
        	{
                subFromBulbDown(y,x,y,x,color);
        		break;
        	}
        	case"left":
        	{
                subFromBulbLeft(y,x,y,x,color);
        		break;
        	}
        }
        updateBoard(color);
    }
    
    private void whenLightUp(int y,int x, Color color, String from) {
    	board[y][x].getButton().setBackground(color);
    	board[y][x].getButton().setIcon(new ImageIcon("bulbup.png"));
        board[y][x].setType("bulbup");
        addFromBulbUp(y,x,color);
        switch(from) 
        {
        	case"right":
        	{
                subFromBulbRight(y,x,y,x,color);
        		break;
        	}
        	case"down":
        	{
                subFromBulbDown(y,x,y,x,color);
        		break;
        	}
        	case"left":
        	{
                subFromBulbLeft(y,x,y,x,color);
        		break;
        	}
        }
        updateBoard(color);
    }
    
    //Change color of board according to light level
    protected void updateBoard(Color color) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
            	switch(board[i][j].getType()) 
            	{
            		case"lit":
            		{
            			if(board[i][j].getLightLevel() == 0)
            			{
                            board[i][j].getButton().setBackground(Color.WHITE);
                            board[i][j].setType("empty");
            			}
            			break;
            		}
            		case"empty":
            		{
            			if(board[i][j].getLightLevel() > 0)
            			{
                            board[i][j].getButton().setBackground(color);
                            board[i][j].setType("lit");
            			}
            			break;
            		}
            	}
            }
        }
    }

    protected void playGame() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j].getButton().setEnabled(true);
            }
        }
    }
    
    protected void endGame() {
    	 for (int i = 0; i < r; i++) {
             for (int j = 0; j < c; j++) {
                 board[i][j].getButton().setEnabled(false);
             }
         }
    }
    
	class SimpleBulbActionListener implements ActionListener {
		int x;
		int y;
		Color color;
		String map;
		public SimpleBulbActionListener(int y, int x, Color c,String m)
		{
			this.y=y;
			this.x=x;
			color=c;
			map=m;
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
        	switch(board[y][x].getType()) 
        	{
        		case"empty":
        		{
                    board[y][x].getButton().setBackground(color);
                    board[y][x].getButton().setIcon(new ImageIcon("bulb.png"));
                    board[y][x].setType("bulb");
                    addRays(board[y][x],color);
                    try {
						file.addBulb(board[y][x],map);
					} catch (IOException e1) {
						System.out.println("IOException!");
						e1.printStackTrace();
					}
             
        			break;
        		}
        		case"bulb":
        		{
                    board[y][x].getButton().setBackground(Color.WHITE);
                    board[y][x].getButton().setIcon(new ImageIcon("bulbempty.png"));
                    board[y][x].setType("empty");
                    subRays(board[y][x],color);
                    try {
						file.subBulb(board[y][x],map);
					} catch (IOException e1) {
						System.out.println("IOException!");
						e1.printStackTrace();
					}
        			break;
        		}
        	}
			
		}

	}
	
	class MovingBulbActionListener implements ActionListener{
		int x;
		int y;
		Color color;
		public MovingBulbActionListener(int y, int x, Color c) {
			this.y=y;
			this.x=x;
			color=c;
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			switch(board[y][x].getType())
        	{
        		case"bulbup":
        		{
        			
                    if(board[y][x-1].getLightLevel()==0)
                    {
                    	whenLightLeft(y,x,color,"up");
                    }
                    else if(board[y+1][x].getLightLevel()==0)
                    {
                        whenLightDown(y,x,color,"up");
                    }
                    else if(board[y][x+1].getLightLevel()==0)
                    {
                        whenLightRight(y,x,color,"up");
                    }
        			break;
        		}
        		//State light left
        		case"bulbleft":
        		{
          
                	if(board[y+1][x].getLightLevel()==0) 
                	{
                        whenLightDown(y,x,color,"left");
                	}
                	else if (board[y][x+1].getLightLevel()==0)
                	{
                        whenLightRight(y,x,color,"left");
                	}
                	else if (board[y-1][x].getLightLevel()==0)
                	{
                        whenLightUp(y,x,color,"left");
                	}
        			break;
        		}
        		//State light left
        		case"bulbdown":
        		{
        			if(board[y][x+1].getLightLevel()==0)
                    {
                        whenLightRight(y,x,color,"down");
                    }
                	else if (board[y-1][x].getLightLevel()==0)
                	{
                        whenLightUp(y,x,color,"down");
                	}
                	else if (board[y][x-1].getLightLevel()==0)
                	{
                        whenLightLeft(y,x,color,"down");
                	}
        			break;
        		}
        		//State light right
        		case"bulbright":
        		{
                	if (board[y-1][x].getLightLevel()==0)
                	{
                        whenLightUp(y,x,color,"right");
                	}
                	else if (board[y][x-1].getLightLevel()==0)
                	{
                        whenLightLeft(y,x,color,"right");
                	}
                	else if (board[y+1][x].getLightLevel()==0)
                	{
                        whenLightDown(y,x,color,"right");
                	}
        			break;
        		}
        	}
		}
	}
    
    //Game constructor
    public Game(String m, Color color) throws Exception
    {
    	
    		file=new FileHandling();
    		//Get size of game board
    		r=file.getRowSize(m);
    		c=file.getColSize(m);
    		
    		
    		file.delSols();
			//Create temp_sol file with the content of chosen map
    		file.copyFile("map"+m+".txt","sol"+m+".txt");


    		
            //Create game frame
            f = new JFrame("Light Up!");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setResizable(false);
        	JPanel jp1, jp2;
        	JSplitPane p;
            PropertyChangeListener resizeHandler = new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                }
            };

            jp1 = new JPanel(new GridLayout(r,c));
            jp2 = new JPanel(new GridLayout(1,3));
            Border def = new LineBorder(Color.GRAY);
            board = new Cell[r][c];



            //Fill panel1 with game Buttons
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    board[i][j] = new Cell(new MyGButton(), i, j);
                    jp1.add(board[i][j].getButton());
                }
            }
            
            //Fill panel2 with event Buttons
            JButton back=new JButton("Back");
            JButton rst=new JButton("Reset");
            JButton done=new JButton("Done!");
            JTextField jtf=new JTextField();
            jtf.setEditable(false);
            jp2.add(back);
            jp2.add(rst);
            jp2.add(done);
            jp2.add(jtf);

			back.setBackground(Color.GRAY);
			back.setBorder(new LineBorder(Color.BLACK));
			back.setFont(new Font("Arial", Font.BOLD, 24));
			back.setForeground(Color.WHITE);
			
			rst.setBackground(Color.GRAY);
			rst.setBorder(new LineBorder(Color.BLACK));
			rst.setFont(new Font("Arial", Font.BOLD, 24));
			rst.setForeground(Color.WHITE);
			
			done.setBackground(Color.GRAY);
			done.setBorder(new LineBorder(Color.BLACK));
			done.setFont(new Font("Arial", Font.BOLD, 24));
			done.setForeground(Color.WHITE);
            
			jtf.setBackground(Color.GRAY);
			jtf.setBorder(new LineBorder(Color.BLACK));
			jtf.setFont(new Font("Arial", Font.BOLD, 19));
            
            //Add action listener to event buttons
            back.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent ae)
        	    {
        	    	f.dispose();
        	    	new Menu().getFrame().setVisible(true);;
        	    }
        	});
            
            rst.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent ae)
        	    {
        	    	f.dispose();
        	    	try {
						Game g=new Game(m,color);
						g.getFrame().setVisible(true);
					} catch (Exception e) {
						System.out.println("Exception!");
						e.printStackTrace();
					}
        	    }
        	});
            
            done.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent ae)
        	    {
        	    	
        	    	String f1="map"+m+"_sol.txt";
        	    	String f2="sol"+m+".txt";
        	    	try {
						if(file.checkSol(f1,f2)) {
							jtf.setText("Correct solution!");
							jtf.setForeground(Color.GREEN);
							endGame();
						}
						else 
						{
							jtf.setText("Incorrect solution!");
							jtf.setForeground(Color.RED);
						}
					} catch (IOException e) {
						System.out.println("IOException!");
						e.printStackTrace();
					}
        	    		
        	    }
        	});
     
            p = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jp1, jp2);
            p.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, resizeHandler);
            f.getContentPane().add(p);
            f.add(p);
            f.pack();
            
        //Fill board with different cells
        ArrayList<ArrayList<Character>> lines=file.loadMap("map",m);
        int nextButtonType;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                board[i][j].getButton().setFont(new Font("Arial", Font.BOLD, 48));
                nextButtonType = Character.getNumericValue(lines.get(i).get(j));
                if (nextButtonType <= 4) {
                    board[i][j].getButton().setText(String.valueOf(nextButtonType));
                    board[i][j].getButton().setBackground(Color.BLACK);
                    board[i][j].getButton().setForeground(Color.WHITE);
                    board[i][j].setType("block" + nextButtonType);
                }
                else 
                {
                    switch(nextButtonType) 
                    {
                    	case 5:
                    	{
                            board[i][j].getButton().setBackground(Color.BLACK);
                            board[i][j].setType("block");
                    		break;
                    	}
                    	case 6:
                    	{
                            board[i][j].getButton().setBackground(Color.WHITE);
                            board[i][j].setType("empty");
                            board[i][j].getButton().addActionListener(new SimpleBulbActionListener(i,j,color,m));
                    		break;
                    	}
                    	case 7:
                    	{
                            board[i][j].getButton().setForeground(Color.BLUE);
                            board[i][j].setType("prism");
                            board[i][j].getButton().setIcon(new ImageIcon("prism.png"));
                    		break;
                    	}
                    	case 8:
                    	{
                            board[i][j].getButton().setBackground(color);
                            board[i][j].setType("bulbup");
                            board[i][j].getButton().setIcon(new ImageIcon("bulbup.png"));
                            addFromBulbUp(i,j,color);
                            updateBoard(color);
                            board[i][j].getButton().addActionListener(new MovingBulbActionListener(i,j,color));
                    		break;
                    	}
                    		
                    }
                }
                board[i][j].getButton().setBorder(def);  
            }
        }
        playGame();
    }
    
}
