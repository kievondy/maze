package excelian.maze;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class MazeTest {

	@Test
	public void testGetContentString_whenNull() {
		char[][] content = null;
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		List<String> contentString = maze.getContentString();

		assertThat(contentString, Matchers.iterableWithSize(0));
	}

	@Test
	public void testGetContentString_whenLengthZero() {
		char[][] content = new char[0][0];
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		List<String> contentString = maze.getContentString();

		assertThat(contentString, Matchers.iterableWithSize(0));
	}

	@Test
	public void testGetContentString_whenInnerArrayNull() {
		char[][] content = new char[][] { null };
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		List<String> contentString = maze.getContentString();

		assertThat(contentString, Matchers.iterableWithSize(0));
	}

	@Test
	public void testGetContentString_whenInnerArrayEmpty() {
		char[][] content = new char[][] { {} };
		int height = 0;
		int width = 0;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		Maze maze = new Maze(content, height, width, startingPoint, finishingPoint);
		List<String> contentString = maze.getContentString();

		assertThat(contentString, Matchers.iterableWithSize(0));
	}

}
