package excelian.maze;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Test;

public class MazeFactoryTest {

	private static final Logger logger = Logger.getLogger(MazeFactoryTest.class);

	@Test(expected = RuntimeException.class)
	public void testGetMazeString_whenFileDoesNotExist() {
		int abs = Math.abs(new Random().nextInt());
		String tmpDir = System.getProperty("java.io.tmpdir");
		String nonExistingFile = tmpDir + abs;
		Maze maze = MazeFactory.getMaze(nonExistingFile);
	}

	@Test
	public void testGetMazeString_whenTypical() {
		String filePath = "src/test/resources/ExampleMaze.txt";
		Maze maze = MazeFactory.getMaze(filePath);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenNull() {
		List<String> list = null;
		MazeFactory.getMaze(list);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenEmpty() {
		MazeFactory.getMaze(new ArrayList<String>());
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsIllegalChar() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X     SX");
		content.add("X A    X");
		content.add("X      X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsMultipleStartingPoints_case1() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X    SSX");
		content.add("X      X");
		content.add("X      X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsMultipleStartingPoints_case2() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X S   SX");
		content.add("X      X");
		content.add("X      X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsMultipleStartingPoints_case3() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X S    X");
		content.add("X      X");
		content.add("X    S X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsMultipleFinishingPoints_case1() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X      X");
		content.add("X      X");
		content.add("X    S X");
		content.add("XFXXXXFX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test(expected = RuntimeException.class)
	public void testGetMazeListOfString_whenContainsMultipleFinishingPoints_case2() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X      F");
		content.add("X      X");
		content.add("X    S X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
	}

	@Test
	public void testGetMazeListOfString_whenTypical_case1() {

		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXX");
		content.add("X     SX");
		content.add("X      X");
		content.add("X      X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);

		assertThat(maze.getWidth(), equalTo(8));
		assertThat(maze.getHeight(), equalTo(5));
		assertThat(maze.getStartingPoint().getX(), equalTo(6));
		assertThat(maze.getStartingPoint().getY(), equalTo(1));
		assertThat(maze.getFinishingPoint().getX(), equalTo(1));
		assertThat(maze.getFinishingPoint().getY(), equalTo(4));

		char[][] arr = new char[][] {
			{'X','X','X','X','X','X','X','X'}
			,{'X',' ',' ',' ',' ',' ','S','X'}
			,{'X',' ',' ',' ',' ',' ',' ','X'}
			,{'X',' ',' ',' ',' ',' ',' ','X'}
			,{'X','F','X','X','X','X','X','X'}
		};
		assertThat(maze.getContent(), equalTo(arr));
	}

	@Test
	public void testGetMazeListOfString_whenTypical_case2() {

		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXXXXX");
		content.add("X   X    SX");
		content.add("X   X  XXXX");
		content.add("X      X");
		content.add("XFXXXXXX");

		Maze maze = MazeFactory.getMaze(content);

		assertThat(maze.getWidth(), equalTo(11));
		assertThat(maze.getHeight(), equalTo(5));
		assertThat(maze.getStartingPoint().getX(), equalTo(9));
		assertThat(maze.getStartingPoint().getY(), equalTo(1));
		assertThat(maze.getFinishingPoint().getX(), equalTo(1));
		assertThat(maze.getFinishingPoint().getY(), equalTo(4));

		char[][] arr = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X','X'}
			,{'X',' ',' ',' ','X',' ',' ',' ',' ','S','X'}
			,{'X',' ',' ',' ','X',' ',' ','X','X','X','X'}
			,{'X',' ',' ',' ',' ',' ',' ','X',' ',' ',' '}
			,{'X','F','X','X','X','X','X','X',' ',' ',' '}
		};
		assertThat(maze.getContent(), equalTo(arr));
	}

	@Test
	public void testGetMazeListOfString_whenTypical_case3() {

		ArrayList<String> content = new ArrayList<String>();
		content.add("XX");
		content.add("XSX");
		content.add("X  X");
		content.add("X   X");
		content.add("X    X");
		content.add("X     X");
		content.add("X      X");
		content.add("X       X");
		content.add("XXXXXXXFX");

		Maze maze = MazeFactory.getMaze(content);

		assertThat(maze.getWidth(), equalTo(9));
		assertThat(maze.getHeight(), equalTo(9));
		assertThat(maze.getStartingPoint().getX(), equalTo(1));
		assertThat(maze.getStartingPoint().getY(), equalTo(1));
		assertThat(maze.getFinishingPoint().getX(), equalTo(7));
		assertThat(maze.getFinishingPoint().getY(), equalTo(8));

		char[][] arr = new char[][] {
			{'X','X',' ',' ',' ',' ',' ',' ',' '}
			,{'X','S','X',' ',' ',' ',' ',' ',' '}
			,{'X',' ',' ','X',' ',' ',' ',' ',' '}
			,{'X',' ',' ',' ','X',' ',' ',' ',' '}
			,{'X',' ',' ',' ',' ','X',' ',' ',' '}
			,{'X',' ',' ',' ',' ',' ','X',' ',' '}
			,{'X',' ',' ',' ',' ',' ',' ','X',' '}
			,{'X',' ',' ',' ',' ',' ',' ',' ','X'}
			,{'X','X','X','X','X','X','X','F','X'}
		};
		assertThat(maze.getContent(), equalTo(arr));
	}

}
