package excelian.maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final char CHAR_WALL = 'X';
	public static final char CHAR_SPACE = ' ';
	public static final char CHAR_PERSON = 'S';
	public static final char CHAR_EXIT = 'F';

	/*
	 * content is an array of array of characters with 4 possible values above -
	 * WALL, SPACE, PERSON or EXIT.
	 */
	private char[][] content = null;

	private int height;

	private int width;

	private Coordinate startingPoint = null;

	private Coordinate finishingPoint = null;

	public Maze(char[][] content, int height, int width, Coordinate startingPoint, Coordinate finishingPoint) {
		super();
		this.content = content;
		this.height = height;
		this.width = width;
		this.startingPoint = startingPoint;
		this.finishingPoint = finishingPoint;
	}

	public char[][] getContent() {
		return content;
	}

	public void setContent(char[][] content) {
		this.content = content;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Coordinate getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Coordinate startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Coordinate getFinishingPoint() {
		return finishingPoint;
	}

	public void setFinishingPoint(Coordinate finishingPoint) {
		this.finishingPoint = finishingPoint;
	}

	public List<String> getContentString() {
		List<String> contentString = new ArrayList<String>();
		if (content == null || content.length <= 0) {
			return contentString;
		}
		for (int i = 0; i < content.length; i++) {
			char[] cs = content[i];
			if (cs != null && cs.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < cs.length; j++) {
					sb.append(cs[j]);
				}
				contentString.add(sb.toString());
			}
		}
		return contentString;
	}

}
