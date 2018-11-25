package excelian.maze;

import java.io.Serializable;

public class Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;

	/**
	 * Coordinate index starts from (0, 0) - similar to an array
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Coordinate[").append("x=").append(x).append(", y=").append(y).append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Coordinate) {
			Coordinate cObj = (Coordinate) obj;
			return x == cObj.getX() && y == cObj.getY();
		}
		return super.equals(obj);
	}

}
