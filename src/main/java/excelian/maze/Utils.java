package excelian.maze;

import java.util.Arrays;

public final class Utils {

	public static final char[][] copyArray(char[][] arr) {
		if (arr == null) {
			return null;
		}
		char[][] theCopy = Arrays.copyOf(arr, arr.length);
		for (int i = 0; i < arr.length; i++) {
			theCopy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		return theCopy;
	}

}
