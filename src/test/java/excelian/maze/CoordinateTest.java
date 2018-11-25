package excelian.maze;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testEqualsObject_whenNull() {
		Coordinate coordinate = new Coordinate(0, 0);
		assertFalse(coordinate.equals(null));
	}

	@Test
	public void testEqualsObject_whenNotSameObject() {
		Coordinate coordinate = new Coordinate(0, 0);
		assertFalse(coordinate.equals("someString"));
	}

	@Test
	public void testEqualsObject_whenNotEqualY() {
		Coordinate coordinate1 = new Coordinate(0, 0);
		Coordinate coordinate2 = new Coordinate(0, 1);
		assertThat(coordinate1, not(equalTo(coordinate2)));
	}

	@Test
	public void testEqualsObject_whenNotEqualXY() {
		Coordinate coordinate1 = new Coordinate(0, 0);
		Coordinate coordinate2 = new Coordinate(2, 1);
		assertThat(coordinate1, not(equalTo(coordinate2)));
	}

	@Test
	public void testEqualsObject_whenTypical() {
		Coordinate coordinate1 = new Coordinate(0, 0);
		Coordinate coordinate2 = new Coordinate(0, 0);
		assertThat(coordinate1, equalTo(coordinate2));
	}

}
