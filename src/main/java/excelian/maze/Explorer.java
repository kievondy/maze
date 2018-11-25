package excelian.maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.apache.log4j.Logger;

public class Explorer implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Explorer.class);

	private Maze maze;

	private List<Coordinate> moveHistory = new ArrayList<Coordinate>();

	private static Predicate<Maze> validMaze = maze -> maze != null && maze.getWidth() > 0 && maze.getHeight() > 0 && maze.getContent() != null
			&& maze.getStartingPoint() != null && maze.getFinishingPoint() != null;

	public void exploreMaze(Maze m) {
		if (!validMaze.test(m)) {
			throw new RuntimeException("Unable to explore invalid maze");
		}
		this.maze = m;
		if (logger.isDebugEnabled()) {
			maze.getContentString().stream().forEach(line -> logger.debug(line));
		}
		move();
	}

	private void move() {

		char[][] content = maze.getContent();
		int x = maze.getStartingPoint().getX();
		int y = maze.getStartingPoint().getY();

		boolean pathFound = false;

		if (content[y][x + 1] == Maze.CHAR_SPACE) {
			// East direction (x + 1)
			pathFound = findPath(content, x + 1, y);
		}
		if (!pathFound && content[y][x - 1] == Maze.CHAR_SPACE) {
			// West direction (x - 1)
			pathFound = findPath(content, x - 1, y);
		}
		if (!pathFound && content[y + 1][x] == Maze.CHAR_SPACE) {
			// South direction (y + 1)
			pathFound = findPath(content, x, y + 1);
		}
		if (!pathFound && content[y - 1][x] == Maze.CHAR_SPACE) {
			// North direction (y - 1)
			pathFound = findPath(content, x, y - 1);
		}

		if (pathFound) {
			logger.debug(String.format("Path to exit: %s", this.moveHistory));
		} else {
			logger.warn("Maze has no path for explorer to reach the exit.");
		}
		if (logger.isDebugEnabled()) {
			maze.getContentString().stream().forEach(line -> logger.debug(line));
		}
	}

	private boolean findPath(char[][] content, int x, int y) {

		ArrayList<Coordinate> path = new ArrayList<Coordinate>();

		/*
		 * findPath() changes the content from Maze.CHAR_SPACE to Maze.CHAR_PERSON when
		 * trying to find Maze.CHAR_EXIT. In order to preserve the original content in
		 * case of any intersection or any path leading to no exit, use contentCopy,
		 * which is the copy of the original content.
		 */
		char[][] contentCopy = Utils.copyArray(content);
		if (findPath(contentCopy, x, y, path)) {
			/*
			 * Reverse the list in order to have the starting point first and the finishing
			 * point last.
			 */
			Collections.reverse(path);
			this.moveHistory = path;

			/*
			 * Fill the content with path from starting point to finishing point, using the
			 * move history.
			 */
			this.moveHistory.forEach(coord -> maze.getContent()[coord.getY()][coord.getX()] = Maze.CHAR_PERSON);

			return true;
		}
		return false;
	}

	/*
	 * Path coordinates will be added in reversed order (i.e. the finishing point
	 * first), because recursive calls do not add the coordinate into the list until
	 * it finds the finishing point. After finishing point found, it returns all the
	 * way back and add all the coordinates until the starting point.
	 */
	private static boolean findPath(char[][] mazeContent, int x, int y, List<Coordinate> path) {

		if (mazeContent[y][x] == Maze.CHAR_EXIT) {
			mazeContent[y][x] = Maze.CHAR_PERSON;
			path.add(new Coordinate(x, y));
			return true;
		}

		if (mazeContent[y][x] == Maze.CHAR_SPACE) {
			mazeContent[y][x] = Maze.CHAR_PERSON;

			// East direction (x + 1)
			if (findPath(mazeContent, x + 1, y, path)) {
				path.add(new Coordinate(x, y));
				return true;
			}

			// West direction (x - 1)
			if (findPath(mazeContent, x - 1, y, path)) {
				path.add(new Coordinate(x, y));
				return true;
			}

			// South Direction (y + 1)
			if (findPath(mazeContent, x, y + 1, path)) {
				path.add(new Coordinate(x, y));
				return true;
			}

			// North Direction (y - 1)
			if (findPath(mazeContent, x, y - 1, path)) {
				path.add(new Coordinate(x, y));
				return true;
			}
		}
		return false;
	}

	public List<Coordinate> getMoveHistory() {
		return moveHistory;
	}

}
