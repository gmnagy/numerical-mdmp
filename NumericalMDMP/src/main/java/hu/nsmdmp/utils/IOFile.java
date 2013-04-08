package hu.nsmdmp.utils;

import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.operations.IOperation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOFile {

	public static final String UTF8 = "UTF-8";

	public static <T> T[] read(final String fileName, final Class<T> type) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream(fileName), UTF8);

		return read(scanner, type);
	}

	public static <T> T[] read(final File file, final Class<T> type) throws IOException {
		Scanner scanner = new Scanner(new FileInputStream(file), UTF8);

		return read(scanner, type);
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] read(final Scanner scanner, final Class<T> type) throws IOException {
		IOperation<T> op = operation(type);

		List<T> rows = new LinkedList<T>();

		try {
			while (scanner.hasNextLine()) {
				try {
					rows.add(op.valueOf(scanner.nextBigDecimal()));
				} catch (NoSuchElementException e) {
					break;
				}
			}
		} finally {
			scanner.close();
		}

		return rows.toArray((T[]) Array.newInstance(type, rows.size()));
	}

	public static void write(final String fileName, final String separator, final String[][] matrix) throws IOException {

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
