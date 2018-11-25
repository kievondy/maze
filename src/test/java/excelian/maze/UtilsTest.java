package excelian.maze;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testCopyArray_whenNull() {
		char[][] copyArray = Utils.copyArray(null);
		assertThat(copyArray, nullValue());
	}

	@Test
	public void testCopyArray_whenTypical() {
		char[][] arr = new char[][] { { 'A', 'B' }, { 'X', 'Y' } };
		char[][] copyArray = Utils.copyArray(arr);
		assertThat(copyArray, equalTo(arr));
		assertThat(copyArray.hashCode(), not(arr.hashCode()));

		arr[0][0] = 'M';
		assertThat(arr[0][0], equalTo('M'));
		assertThat(copyArray[0][0], equalTo('A'));
	}

}
