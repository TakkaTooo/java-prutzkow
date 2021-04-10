package ru.rsreu.astashkin0604.scholarship.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.prutzkow.resourcer.Resourcer;

/**
 * Enum describes the operation with the file (copy, move)
 * 
 * @author Maxim Astashkin
 *
 */
public enum FileOperation {

	COPY {
		@Override
		void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			Files.copy(new File(sourcePath).toPath(), new File(destinationPath).toPath());
			/*
			 * InputStream inputStream = null; OutputStream outputStream = null; try {
			 * inputStream = new BufferedInputStream(new FileInputStream(new
			 * File(sourcePath))); outputStream = new BufferedOutputStream(new
			 * FileOutputStream(new File(destinationPath))); int length; byte[] buffer = new
			 * byte[1024]; while ((length = inputStream.read()) > 0) {
			 * outputStream.write(buffer, 0, length); } } finally { inputStream.close();
			 * outputStream.close(); }
			 */
		}
	},
	MOVE {
		@Override
		void performOpertaion(String sourcePath, String destinationPath) throws IOException {
			Files.move(new File(sourcePath).toPath(), new File(destinationPath).toPath());
		}
	};

	/**
	 * Performing an operation with a file (copy, move).
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file.
	 * @throws IOException if the source file does not exist or the resulting file
	 *                     has already been created.
	 */
	abstract void performOpertaion(String sourcePath, String destinationPath) throws IOException;

	/**
	 * Generates and throw an exception for 2 situations (if the source file does
	 * not exist or the resulting file has already been created)
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file
	 */
	public static void throwIOExceptionByFilesAvailability(String sourcePath, String destinationPath)
			throws IOException {
		File sourceFile = new File(sourcePath);
		File destinationFile = new File(destinationPath);
		if (!sourceFile.exists()) {
			throw new IOException(
					String.format(Resourcer.getString("files.file.exceptionFormat"), sourceFile.getAbsolutePath()));
		} else {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptionExistsFormat"),
					destinationFile.getAbsolutePath()));
		}
	}
}
