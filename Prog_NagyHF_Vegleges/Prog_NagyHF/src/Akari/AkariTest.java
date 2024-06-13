package Akari;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AkariTest {
	Cell cell;
	Cell board[][];
	FileHandling file;
	ArrayList<ArrayList<Character>> lines;
	Menu menu;
	MyGButton testbutton;
	Game game;
	@BeforeEach
	public void initialise() throws Exception{
		cell=new Cell(new MyGButton(),1,2);
		cell.setLightLevel(5);
		cell.setType("bulb");
		file=new FileHandling();
		menu=new Menu();
		testbutton=new MyGButton();
		lines=new ArrayList<>(3);
		game=new Game("test",Color.YELLOW);
		board=game.getBoard();
		for(int i=0; i<3; i++) 
		{
			lines.add(new ArrayList<Character>(5));
			lines.get(i).add('1');
			lines.get(i).add('2');
			lines.get(i).add('3');
			lines.get(i).add('6');
			lines.get(i).add('5');
		}
			
	}
	
	@Test
	public void cellTest() {
		
		//A cellákat, amelyek a játék pályáját építik fel,
		//annak a getterjeit tesztelem itt
		assertEquals(1,cell.getRow());
		assertEquals(2,cell.getCol());
		assertEquals(5,cell.getLightLevel());
		cell.subLight();
		assertEquals(4,cell.getLightLevel());
		cell.addLight();
		assertEquals(5,cell.getLightLevel());
		assertSame("bulb",cell.getType());
		assertEquals(Color.WHITE,cell.getButton().getBackground());
	}
	
	@Test
	public void fileHandlingTest() throws IOException {
		
		//A fájlkezelésért felelős metódusokat teszteli
		assertEquals(3,file.getRowSize("test"));
		assertEquals(5,file.getColSize("test"));
		assertTrue(file.checkSol("maptest.txt", "maptest.txt"));
		assertEquals(lines,file.loadMap("map","test"));
		file.delSols();
		assertFalse(new File("sol1.txt").exists());
	}
	
	
	@Test
	public void myGButtonTest() {
		
		//Egy újonnan létrehozott MyGButton kinézetét teszteli
		assertEquals(Color.WHITE,testbutton.getBackground());
		assertFalse(testbutton.isEnabled());
	}
	
	@Test
	public void gameTest() {
		
		/* A maptest.txt fájból betöltött pályát és annak fényszintjét tesztelgetem, 
		 * az egyik 6-os "empty block"-ra helyezett lámpával
		 * 
		 * A pálya igy néz ki, ahol a 6-osok, a bevílágitható helyet jelzik:
		 * Ezek valamelyik helyére meghívom a függvényeket, mintha lámpát raknék oda
		 * 
		 * 12365
		 * 12365
		 * 12365
		 * 
		 */
		assertEquals(0,board[0][0].getRow());
		assertEquals("block1",board[0][0].getType());
		
		game.addFromBulbDown(0, 3, Color.YELLOW);
		assertEquals(1,board[1][3].getLightLevel());
		assertEquals(1,board[2][3].getLightLevel());
		
		game.subFromBulbDown(0, 3, 0, 3, Color.YELLOW);
		assertEquals(0,board[1][3].getLightLevel());
		assertEquals(0,board[2][3].getLightLevel());
		
		game.addRays(board[1][3],Color.YELLOW);
		assertEquals(1,board[0][3].getLightLevel());
		assertEquals(1,board[2][3].getLightLevel());
		
		game.subRays(board[1][3],Color.YELLOW);
		assertEquals(0,board[0][3].getLightLevel());
		assertEquals(0,board[2][3].getLightLevel());
		
		game.addFromBulbUp(2, 3, Color.YELLOW);
		assertEquals(1,board[0][3].getLightLevel());
		assertEquals(1,board[1][3].getLightLevel());
		
		game.subFromBulbUp(2, 3, 2, 3, Color.YELLOW);
		assertEquals(0,board[0][3].getLightLevel());
		assertEquals(0,board[1][3].getLightLevel());
		
		game.playGame();
		assertTrue(board[0][0].getButton().isEnabled());
		assertTrue(board[2][3].getButton().isEnabled());
		
		game.endGame();
		assertFalse(board[0][0].getButton().isEnabled());
		assertFalse(board[2][3].getButton().isEnabled());
	}
}
