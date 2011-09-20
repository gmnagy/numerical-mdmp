package hu.nsmdmp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apfloat.Apfloat;

public class IOFile {

	public static final String UTF8 = "UTF-8";

	public static Apfloat[] read(final String fileName) throws IOException {
		List<Apfloat> binomialMoments = new LinkedList<Apfloat>();

		Scanner scanner = new Scanner(new FileInputStream(fileName), UTF8);

		try {
			while (scanner.hasNextLine()) {
				try {
					binomialMoments.add(new Apfloat(scanner.nextBigDecimal(), Precision.SCALE));
				} catch (NoSuchElementException e) {
					break;
				}
			}
		} finally {
			scanner.close();
		}

		return binomialMoments.toArray(new Apfloat[] {});
	}
}
