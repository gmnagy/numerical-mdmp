package hu.nsmdmp.utils;

import java.io.File;
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
		Scanner scanner = new Scanner(new FileInputStream(fileName), UTF8);

		return read(scanner);
	}

	public static Apfloat[] read(final File file) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream(file), UTF8);

		return read(scanner);
	}

	private static Apfloat[] read(final Scanner scanner) throws IOException {
		List<Apfloat> rows = new LinkedList<Apfloat>();

		try {
			while (scanner.hasNextLine()) {
				try {
					rows.add(new Apfloat(scanner.nextBigDecimal(), Precision.SCALE));
				} catch (NoSuchElementException e) {
					break;
				}
			}
		} finally {
			scanner.close();
		}

		return rows.toArray(new Apfloat[] {});
	}
}
