package ru.rsreu.astashkin0604.scholarship.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;

public class ScholarshipSheetFileService {

	private ScholarshipSheetFileService() {

	}

	/**
	 * Saves a ScholarshipSheet array to file by ObjectOutPutStream.
	 * 
	 * @param sheets   - input array.
	 * @param filePath - path to saving file.
	 * @throws IOException - in case an error occurred while working with the output
	 *                     stream.
	 */
	public static void createFileFromScholarshipSheetArray(ScholarshipSheet[] sheets, String filePath)
			throws IOException {
		File outputFile = new File(filePath);
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		outputFile.createNewFile();
		try {
			outputStream = new FileOutputStream(outputFile);
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(sheets);

		} finally {
			closeOpenedStreams(outputFile, outputStream, objectOutputStream);
		}
	}

	/**
	 * Reads a ScholarshipSheet array from a file
	 * 
	 * @param filePath - path to reading file.
	 * @return read array.
	 * @throws IOException            - in case the file does not exist
	 * @throws ClassNotFoundException in case of serialization errors or lack of
	 *                                type of readable objects in classpath
	 */
	public static ScholarshipSheet[] getScholarshipSheetArrayFromFile(String filePath)
			throws IOException, ClassNotFoundException {
		ScholarshipSheet[] output = null;
		File inputFile = new File(filePath);
		if (inputFile.exists()) {
			InputStream inputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				inputStream = new FileInputStream(inputFile);
				objectInputStream = new ObjectInputStream(inputStream);
				output = ((ScholarshipSheet[]) objectInputStream.readObject());
			} finally {
				closeOpenedStreams(inputFile, inputStream, objectInputStream);
			}

		} else {
			throw new FileNotFoundException(
					String.format(Resourcer.getString("files.file.exceptionFormat"), inputFile.getAbsolutePath()));
		}
		return output;
	}

	/**
	 * Closes open streams
	 * 
	 * @param inputFile    - the file for which streams were opened
	 * @param fileStream
	 * @param objectStream
	 * @throws IOException in case the stream was not opened.
	 */
	private static void closeOpenedStreams(File inputFile, Closeable fileStream, Closeable objectStream)
			throws IOException {
		try {
			fileStream.close();
			objectStream.close();
		} catch (NullPointerException e) {
			throw new IOException(String.format(Resourcer.getString("files.file.exceptionBadFileFormat"),
					inputFile.getAbsolutePath()));
		}
	}

	/**
	 * Wrapping enum FileOperation constants methods.
	 * 
	 * @param sourcePath      - source file path.
	 * @param destinationPath - the path to the resulting file
	 * @param operation
	 * @throws IOException if the source file does not exist or the resulting file
	 *                     has already been created
	 */
	public static void performOperationWithFile(String sourcePath, String destinationPath, FileOperation operation)
			throws IOException {
		try {
			operation.performOpertaion(sourcePath, destinationPath);
		} catch (IOException e) {
			FileOperation.throwIOExceptionByFilesAvailability(sourcePath, destinationPath);
		}
	}
}
