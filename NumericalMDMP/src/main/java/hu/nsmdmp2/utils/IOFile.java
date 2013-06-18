package hu.nsmdmp2.utils;

import hu.nsmdmp2.numerics.Value;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOFile {

	public static final String UTF8 = "UTF-8";

	public static Value[] read(final String fileName, final Class<? extends Comparable<?>> valueType) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream(fileName), UTF8);

		return read(scanner, valueType);
	}

	public static Value[] read(final File file, final Class<? extends Comparable<?>> valueType) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream(file), UTF8);

		return read(scanner, valueType);
	}

	@SuppressWarnings("unchecked")
	private static Value[] read(final Scanner scanner, final Class<? extends Comparable<?>> valueType) throws IOException {

		List<Value> rows = new LinkedList<Value>();

		try {
			while (scanner.hasNextLine()) {
				try {
					// System.out.println(scanner.next());
					rows.add(Value.valueOf((Class<Comparable<?>>) valueType, new BigDecimal(scanner.next())));
				} catch (NoSuchElementException e) {
					break;
				}
			}
		} finally {
			scanner.close();
		}

		return rows.toArray(new Value[] {});
	}

	public static void write(final String fileName, final String separator, final Object[][] matrix) throws IOException {

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

	public static void write(final String fileName, final Object[] vector) throws IOException {

		Writer out = new OutputStreamWriter(new FileOutputStream(fileName), UTF8);

		try {

			for (int i = 0; i < vector.length; i++) {

				StringBuilder toFile = new StringBuilder();

				toFile.append(vector[i]);

				out.write(toFile.toString());
				out.write(System.getProperty("line.separator"));
			}

		} finally {
			out.close();
		}
	}

	public static void append(final String fileName, final String separator, final boolean newLine, final double value) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

		try {
			if (null != separator)
				bw.append(separator);

			bw.write(String.valueOf(value));

			if (newLine)
				bw.newLine();

			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			bw.close();
		}
	}
}
