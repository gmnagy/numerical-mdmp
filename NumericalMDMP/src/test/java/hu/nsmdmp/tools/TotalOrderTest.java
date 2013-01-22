package hu.nsmdmp.tools;

import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

public class TotalOrderTest {

	@Test
	public void test1() {

		int maxOrder = 2;
		int dim = 2;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 }, { 1, 1 }, { 0, 2 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}
	
	@Test
	public void test4() {

		int maxOrder = 1;
		int dim = 2;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { { 0, 0 }, { 1, 0 }, { 0, 1 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}
	
	@Test
	public void test5() {

		int maxOrder = 2;
		int dim = 1;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { { 0 }, { 1 }, { 2 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}

	@Test
	public void test2() {

		int maxOrder = 2;
		int dim = 3;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 0, 2, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 0, 2 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}
	
	@Test
	public void test6() {

		int maxOrder = 3;
		int dim = 2;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 2 }, { 1, 2 }, { 0, 3 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}

	@Test
	public void test3() {

		int maxOrder = 5;
		int dim = 3;

		List<int[]> result = generateTotalOrderOfMomentMembers(maxOrder, dim);

		int[][] expected = { //
				{ 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, //
				{ 5, 0, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 2, 1, 0 }, { 3, 1, 0 }, //
				{ 4, 1, 0 }, { 0, 2, 0 }, { 1, 2, 0 }, { 2, 2, 0 }, { 3, 2, 0 }, //
				{ 0, 3, 0 }, { 1, 3, 0 }, { 2, 3, 0 }, { 0, 4, 0 }, { 1, 4, 0 }, //
				{ 0, 5, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 2, 0, 1 }, { 3, 0, 1 }, //
				{ 4, 0, 1 }, { 0, 1, 1 }, { 1, 1, 1 }, { 2, 1, 1 }, { 3, 1, 1 }, //
				{ 0, 2, 1 }, { 1, 2, 1 }, { 2, 2, 1 }, { 0, 3, 1 }, { 1, 3, 1 }, //
				{ 0, 4, 1 }, { 0, 0, 2 }, { 1, 0, 2 }, { 2, 0, 2 }, { 3, 0, 2 }, //
				{ 0, 1, 2 }, { 1, 1, 2 }, { 2, 1, 2 }, { 0, 2, 2 }, { 1, 2, 2 }, //
				{ 0, 3, 2 }, { 0, 0, 3 }, { 1, 0, 3 }, { 2, 0, 3 }, { 0, 1, 3 }, //
				{ 1, 1, 3 }, { 0, 2, 3 }, { 0, 0, 4 }, { 1, 0, 4 }, { 0, 1, 4 }, //
				{ 0, 0, 5 } };

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}
}
