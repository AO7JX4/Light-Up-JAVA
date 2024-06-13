package Akari;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandling {
    
	//Get row size
	public int getRowSize(String m) throws IOException{
		int r=0;
		BufferedReader br=new BufferedReader(new FileReader("map"+m+".txt")); 
		while ((br.readLine()) != null) 
		{
			r++;
	    }
		br.close();
		return r;
	}
	
	//Get column size
	public int getColSize(String m) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("map"+m+".txt")); 
		int c=br.readLine().length();
		br.close();
		return c;
	}

	//Load map from file
	public ArrayList<ArrayList<Character>> loadMap(String t,String m) throws IOException {
		int r=getRowSize(m);
		int c=getColSize(m);
		ArrayList<ArrayList<Character>> lines =new ArrayList<>(r);
		try {
	   		BufferedReader br=new BufferedReader(new FileReader(t+m+".txt")); 
			String line;
			int i=0;	
			while ((line=br.readLine()) != null) 
			{
				lines.add(new ArrayList<Character>(c));
				for(int j=0; j<c; j++) {
					lines.get(i).add(line.charAt(j));
				}
				i++;
		    }
		br.close();
		} catch (IOException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		return lines;
	}
	
	//Check if solved map matches with solution
	//(Check content of two files line by line)
	public boolean checkSol(String f1, String f2) throws IOException {
	       
			BufferedReader br1 = new BufferedReader(new FileReader(f1));
	        BufferedReader br2 = new BufferedReader(new FileReader(f2));
	        String line1 = br1.readLine();
	        String line2 = br2.readLine();
	         
	        boolean areEqual = true;
	        while (line1 != null || line2 != null)
	        {
	            if(line1 == null || line2 == null)
	            {
	                areEqual = false;
	                 
	                break;
	            }
	            else if(! line1.equalsIgnoreCase(line2))
	            {
	                areEqual = false;
	                 
	                break;
	            }
	            
	            line1 = br1.readLine();
	            line2 = br2.readLine();

	        }
	        
	        br1.close();
	        br2.close();
	        return areEqual;
	    }
	
	//Create file with the content of another file
    public void copyFile(String a, String b) throws Exception
    {
            FileInputStream in = new FileInputStream(a);
            FileOutputStream out = new FileOutputStream(b);
            try {
                int n;
                while ((n = in.read()) != -1) {

                    out.write(n);
                }
            }
            finally {
                if (in != null) 
                {
                    in.close();
                }
                if (out != null) 
                {
                    out.close();
                }
            }
        }
		
	
	
	//Add bulb to solm.txt
	public void addBulb(Cell cell, String m) throws IOException{
        int y = cell.getRow();
        int x = cell.getCol();
        int r=getRowSize(m);
        int c=getColSize(m);
        ArrayList<ArrayList<Character>> lines=loadMap("sol",m);
		PrintWriter pw = new PrintWriter(new FileWriter("sol"+m+".txt"));	
        for(int i=0; i<r; i++) 
        {
        	for(int j=0; j<c; j++)
        	{
        		if(y==i && x==j) {
        			pw.write("L");
        		}
        		else {
        			pw.write(lines.get(i).get(j));
        		}
        		
        	}
        	pw.write("\n");
        	
        }
        pw.close(); 	
	}
	
	//Sub bulb from solm.txt
	public void subBulb(Cell cell, String m) throws IOException {
        int y = cell.getRow();
        int x = cell.getCol();
        int r=getRowSize(m);
        int c=getColSize(m);
        ArrayList<ArrayList<Character>> lines=loadMap("sol",m);
		PrintWriter pw = new PrintWriter(new FileWriter("sol"+m+".txt"));	
        for(int i=0; i<r; i++) 
        {
        	for(int j=0; j<c; j++)
        	{
        		if(y==i && x==j) {
        			pw.write("6");
        		}
        		else {
        			pw.write(lines.get(i).get(j));
        		}
        		
        	}
        	pw.write("\n");
        	
        }
        pw.close(); 	
	}
	
	public void delSols() {
		
		for(int i=1; i<=5; i++) {
			File delFile=new File("sol"+i+".txt");
			delFile.delete();
		}
	}
	

}
