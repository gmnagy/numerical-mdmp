package hu.nsmdmp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

	public static void write(final String fileName, final String separator, final double[][] matrix) throws IOException {

		Writer out = new OutputStreamWriter(new FileOutputStream(fileName), UTF8);

		try {

			for (int i = 0; i < matrix.length; i++) {

				StringBuilder toFile = new StringBuilder();

				int j = 0;
				for (; j < matrix[i].length - 1; j++) {
					toFile.append(matrix[i][j]);
					toFile.append(separator);
				}

				toFile.append(matrix[i][j]);

				out.write(toFile.toString());
				out.write(System.getProperty("line.separator"));
			}

		} finally {
			out.close();
		}
	}
}
