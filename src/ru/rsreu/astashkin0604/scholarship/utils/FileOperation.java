package ru.rsreu.astashkin0604.scholarship.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.prutzkow.resourcer.Resourcer;

/**
 * Enum describes the operation with the file (copy, move)
 * 
 * @author Maxim Astashkin
 *
 */
public enum FileOperation {
	/**
	 * Copy operation
	 */
	COPYING {
		@Override
		public void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			writeBytesToFile(new File(destinationPath), getBytesFromFile(new File(sourcePath)));
		}
	},
	/**
	 * Move operation
	 */
	MOVING {
		@Override
		public void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			COPYING.performOpertaion(sourcePath, destinationPath);
			File moving = new File(sourcePath);
			try {
				moving.delete();
			} catch (SecurityException e) {
				throw new IOException(String.format(Resourcer.getString("files.file.securityExceptionFormat"),
						moving.getAbsolutePath()));
			}
		}
	};

	/**
	 * Performing an operation with a file (copy, move).
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file.
	 * @throws IOException            if the source file does not exist or the
	 *                                resulting file has already been created.
	 * @throws ClassNotFoundException
	 */
	public abstract void performOpertaion(String sourcePath, String destinationPath) throws IOException;

	/**
	 * Gets bytes from file.
	 * 
	 * @param file - input file.
	 * @return byte array of file.
	 * @throws IOException If the first byte cannot be read for any reasonother than
	 *                     the end of the file, if the input stream has been closed,
	 *                     orif some other I/O error occurs or file not found.
	 */
	private static byte[] getBytesFromFile(File file) throws IOException {
		byte[] bytes = new byte[(int) file.length()];
		try {
			InputStream inputStream = new FileInputStream(file);
			try {
				inputStream.read(bytes);
			} finally {
				inputStream.close();
			}
		} catch (FileNotFoundException e) {
			throw new IOException(generateExceptionMessage(file, "files.file.copy.exception"));
		}
		return bytes;
	}

	/**
	 * Writes bytes to file.
	 * 
	 * @param file  - output file.
	 * @param bytes - writtine bytes
	 * @throws IOException - if an I/O error occurs or if the file exists but is a
	 *                     directoryrather than a regular file, does not exist but
	 *                     cannotbe created, or cannot be opened for any other
	 *                     reason.
	 */
	private static void writeBytesToFile(File file, byte[] bytes) throws IOException {
		try {
			OutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
			try {
				outputStream.write(bytes);
			} finally {
				outputStream.close();
			}
		} catch (FileNotFoundException e) {
			throw new IOException(generateExceptionMessage(file, "files.file.copy.exceptionWithWriting"));
		}
	}
	
	private static String generateExceptionMessage(File file, String formatResourceKey) {
		return String.format(Resourcer.getString(formatResourceKey), file.getAbsolutePath());
	}

}
