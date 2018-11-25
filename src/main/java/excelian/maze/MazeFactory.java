package excelian.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MazeFactory {

	public static Maze getMaze(String filePath) {
		Path path = Paths.get(filePath);
		Stream<String> lines = null;
		try {
			lines = Files.lines(path);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Unable to open file '%s'", filePath), e);
		}
		List<String> list = new ArrayList<String>();
		lines.forEach(line -> list.add(line));
		return getMaze(list);
	}

	public static Maze getMaze(List<String> contentString) {

		if (contentString == null || contentString.isEmpty()) {
			throw new RuntimeException("Invalid maze - Maze must not be empty.");
		}

		if (!validateAllowedCharacters(contentString)) {
			throw new RuntimeException("Invalid maze - Containing unknown character/s");
		}

		int height = contentString.size();

		// get the longest line
		int width = contentString.stream().mapToInt(line -> line.length()).max().getAsInt();

		char[][] content = new char[height][width];

		int y = 0;
		boolean multipleStartingOrFinishingPointsError = false;
		Coordinate startingPoint = null;
		Coordinate finishingPoint = null;
		for (String line : contentString) {

			// populate content
			for (int j = 0; j < width; j++) {
				if (j < line.length()) {
					content[y][j] = line.charAt(j);
				} else {
					content[y][j] = Maze.CHAR_SPACE;
				}
			}

			// populate coordinates for starting point
			int xPerson = 0;
			while ((xPerson = line.indexOf(Maze.CHAR_PERSON, xPerson)) >= 0) {

				if (startingPoint != null) {
					// 2 or more starting point found, hence error
					multipleStartingOrFinishingPointsError = true;
				}
				startingPoint = new Coordinate(xPerson, y);

				xPerson++;
			}

			// if error found above, don't bother finding finishing point
			if (!multipleStartingOrFinishingPointsError) {

				// populate coordinates for finishing point
				int xExit = 0;
				while ((xExit = line.indexOf(Maze.CHAR_EXIT, xExit)) >= 0) {

					if (finishingPoint != null) {
						// 2 or more finishing point found, hence error
						multipleStartingOrFinishingPointsError = true;
					}
					finishingPoint = new Coordinate(xExit, y);

					xExit++;
				}
			}

			y++;
		}

		if (multipleStartingOrFinishingPointsError) {
			throw new RuntimeException("Invalid maze - More than one starting/finishing points found.");
		}

		return new Maze(content, height, width, startingPoint, finishingPoint);
	}

	private static Pattern MAZE_ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^(X|\\s|S|F)*$");

	private static boolean validateAllowedCharacters(List<String> contentString) {
		long count = contentString.stream().filter(line -> !MAZE_ALLOWED_CHARACTERS_PATTERN.matcher(line).matches()).count();
		if (count <= 0) {
			return true;
		}
		return false;
	}

}
