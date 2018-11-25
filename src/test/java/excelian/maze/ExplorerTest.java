package excelian.maze;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;

public class ExplorerTest {

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_MaseIsNull() {
		new Explorer().exploreMaze(null);
	}

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_ContentIsNull() {
		char[][] content = null;
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		new Explorer().exploreMaze(maze);
	}

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_InvalidWidth() {
		char[][] content = new char[1][1];
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		new Explorer().exploreMaze(maze);
	}

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_InvalidHeight() {
		char[][] content = new char[1][1];
		int height = 0;
		int width = 1;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		new Explorer().exploreMaze(maze);
	}

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_InvalidStartingPoint() {
		char[][] content = new char[1][1];
		int height = 1;
		int width = 1;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		new Explorer().exploreMaze(maze);
	}

	@Test(expected = RuntimeException.class)
	public void testExploreMaze_whenInvalidMaze_InvalidFinishingPoint() {
		char[][] content = new char[1][1];
		int height = 1;
		int width = 1;
		Coordinate startingPoint = new Coordinate(1, 1);
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		new Explorer().exploreMaze(maze);
	}

	@Test
	public void testExploreMaze_whenTypical_case1() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXXXXXXXXX");
		content.add("X             X");
		content.add("X XXXXXXXXXXX X");
		content.add("X XS        X X");
		content.add("X XXXXXXXXX X X");
		content.add("X XXXXXXXXX X X");
		content.add("X XXXX      X X");
		content.add("X XXXX XXXX X X");
		content.add("X XXXX XXXX X X");
		content.add("X X    XXXXXX X");
		content.add("X X XXXXXXXXX X");
		content.add("X X XXXXXXXXX X");
		content.add("X X         X X");
		content.add("X XXXXXXXXX   X");
		content.add("XFXXXXXXXXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(73));
		assertThat(explorer.getMoveHistory(), contains(new Coordinate(4, 3), new Coordinate(5, 3), new Coordinate(6, 3), new Coordinate(7, 3),
				new Coordinate(8, 3), new Coordinate(9, 3), new Coordinate(10, 3), new Coordinate(11, 3), new Coordinate(11, 4), new Coordinate(11, 5),
				new Coordinate(11, 6), new Coordinate(10, 6), new Coordinate(9, 6), new Coordinate(8, 6), new Coordinate(7, 6), new Coordinate(6, 6),
				new Coordinate(6, 7), new Coordinate(6, 8), new Coordinate(6, 9), new Coordinate(5, 9), new Coordinate(4, 9), new Coordinate(3, 9),
				new Coordinate(3, 10), new Coordinate(3, 11), new Coordinate(3, 12), new Coordinate(4, 12), new Coordinate(5, 12), new Coordinate(6, 12),
				new Coordinate(7, 12), new Coordinate(8, 12), new Coordinate(9, 12), new Coordinate(10, 12), new Coordinate(11, 12), new Coordinate(11, 13),
				new Coordinate(12, 13), new Coordinate(13, 13), new Coordinate(13, 12), new Coordinate(13, 11), new Coordinate(13, 10), new Coordinate(13, 9),
				new Coordinate(13, 8), new Coordinate(13, 7), new Coordinate(13, 6), new Coordinate(13, 5), new Coordinate(13, 4), new Coordinate(13, 3),
				new Coordinate(13, 2), new Coordinate(13, 1), new Coordinate(12, 1), new Coordinate(11, 1), new Coordinate(10, 1), new Coordinate(9, 1),
				new Coordinate(8, 1), new Coordinate(7, 1), new Coordinate(6, 1), new Coordinate(5, 1), new Coordinate(4, 1), new Coordinate(3, 1),
				new Coordinate(2, 1), new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5),
				new Coordinate(1, 6), new Coordinate(1, 7), new Coordinate(1, 8), new Coordinate(1, 9), new Coordinate(1, 10), new Coordinate(1, 11),
				new Coordinate(1, 12), new Coordinate(1, 13), new Coordinate(1, 14)));

	}

	@Test
	public void testExploreMaze_whenTypical_case2() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXXX");
		content.add("X   X   X");
		content.add("X X   X X");
		content.add("XSX X X F");
		content.add("X X   X X");
		content.add("X   X   X");
		content.add("XXXXXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(13));
		assertThat(explorer.getMoveHistory(),
				contains(new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(2, 5), new Coordinate(3, 5), new Coordinate(3, 4), new Coordinate(4, 4),
						new Coordinate(5, 4), new Coordinate(5, 5), new Coordinate(6, 5), new Coordinate(7, 5), new Coordinate(7, 4), new Coordinate(7, 3),
						new Coordinate(8, 3)));
	}

	@Test
	public void testExploreMaze_whenTypical_case3() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXXX");
		content.add("X      SX");
		content.add("X       X");
		content.add("X       X");
		content.add("X       X");
		content.add("XXXXXXXFX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(28));
		assertThat(explorer.getMoveHistory(),
				contains(new Coordinate(6, 1), new Coordinate(5, 1), new Coordinate(4, 1), new Coordinate(3, 1), new Coordinate(2, 1), new Coordinate(1, 1),
						new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2), new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(6, 2),
						new Coordinate(7, 2), new Coordinate(7, 3), new Coordinate(6, 3), new Coordinate(5, 3), new Coordinate(4, 3), new Coordinate(3, 3),
						new Coordinate(2, 3), new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(2, 4), new Coordinate(3, 4), new Coordinate(4, 4),
						new Coordinate(5, 4), new Coordinate(6, 4), new Coordinate(7, 4), new Coordinate(7, 5)));
	}

	@Test
	public void testExploreMaze_whenTypical_case4() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("X");
		content.add("XX");
		content.add("XSX");
		content.add("X XX");
		content.add("X XXX");
		content.add("X    X");
		content.add("XXXX XX");
		content.add("X    XXX");
		content.add("X XX XXXX");
		content.add("X XX    XX");
		content.add("XXXXXFXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(12));
		assertThat(explorer.getMoveHistory(),
				contains(new Coordinate(1, 3), new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(2, 5), new Coordinate(3, 5), new Coordinate(4, 5),
						new Coordinate(4, 6), new Coordinate(4, 7), new Coordinate(4, 8), new Coordinate(4, 9), new Coordinate(5, 9), new Coordinate(5, 10)));
	}

	@Test
	public void testExploreMaze_whenTypical_case5() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXXXXXXXXXX");
		content.add("XXSXX       XX");
		content.add("XX XX XX XX XX");
		content.add("XX    XX XX XX");
		content.add("XXXXX XX XX XX");
		content.add("XX XX XX XX XX");
		content.add("XX XX XX XXXXX");
		content.add("XX XX XX XXXXX");
		content.add("XX    XX    XX");
		content.add("XXXXXXXXXXFXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(20));
		assertThat(explorer.getMoveHistory(),
				contains(new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(5, 3), new Coordinate(5, 2),
						new Coordinate(5, 1), new Coordinate(6, 1), new Coordinate(7, 1), new Coordinate(8, 1), new Coordinate(8, 2), new Coordinate(8, 3),
						new Coordinate(8, 4), new Coordinate(8, 5), new Coordinate(8, 6), new Coordinate(8, 7), new Coordinate(8, 8), new Coordinate(9, 8),
						new Coordinate(10, 8), new Coordinate(10, 9)));
	}

	/*
	 * Unit test for testing EAST direction findPath() recursive calls
	 */
	@Test
	public void testExploreMaze_whenTypical_eastLoop() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXX");
		content.add("XXXXXX");
		content.add("XS   F");
		content.add("XXXXXX");
		content.add("XXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(4));
		assertThat(explorer.getMoveHistory(), contains(new Coordinate(2, 2), new Coordinate(3, 2), new Coordinate(4, 2), new Coordinate(5, 2)));
	}

	/*
	 * Unit test for testing WEST direction findPath() recursive calls
	 */
	@Test
	public void testExploreMaze_whenTypical_westLoop() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXX");
		content.add("XXXXXX");
		content.add("F   SX");
		content.add("XXXXXX");
		content.add("XXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(4));
		assertThat(explorer.getMoveHistory(), contains(new Coordinate(3, 2), new Coordinate(2, 2), new Coordinate(1, 2), new Coordinate(0, 2)));

	}

	/*
	 * Unit test for testing SOUTH direction findPath() recursive calls
	 */
	@Test
	public void testExploreMaze_whenTypical_southLoop() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXX");
		content.add("XXSXXX");
		content.add("XX XXX");
		content.add("XX XXX");
		content.add("XXFXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(3));
		assertThat(explorer.getMoveHistory(), contains(new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4)));

	}

	/*
	 * Unit test for testing NORTH direction findPath() recursive calls
	 */
	@Test
	public void testExploreMaze_whenTypical_northLoop() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXFXXX");
		content.add("XX XXX");
		content.add("XX XXX");
		content.add("XXSXXX");
		content.add("XXXXXX");

		Maze maze = MazeFactory.getMaze(content);
		Explorer explorer = new Explorer();
		explorer.exploreMaze(maze);

		assertThat(explorer.getMoveHistory(), iterableWithSize(3));
		assertThat(explorer.getMoveHistory(), contains(new Coordinate(2, 2), new Coordinate(2, 1), new Coordinate(2, 0)));

	}

	@Test
	public void testExploreMaze_whenNoPathToExit() {
		ArrayList<String> content = new ArrayList<String>();
		content.add("XXXXXX");
		content.add("X   SX");
		content.add("XXXX X");
		content.add("X  X X");
		content.add("XFXXXX");

		Maze maze = MazeFactory.getMaze(content);
		new Explorer().exploreMaze(maze);
	}

}
