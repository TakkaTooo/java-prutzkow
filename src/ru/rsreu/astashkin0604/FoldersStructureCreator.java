package ru.rsreu.astashkin0604;

import java.io.File;
import java.io.IOException;

import com.prutzkow.resourcer.Resourcer;

public class FoldersStructureCreator {

	private FoldersStructureCreator() {

	}

	/**
	 * Creates a folder hierarchy according to the job variant.
	 * 
	 * @return the complete message from the result messages of each create
	 *         operation.
	 */
	public static String createFoldersStructureWithGettingMessage() {
		StringBuilder output = new StringBuilder();
		try {
			output.append(createFolderByPathWithGettingMessage(Resourcer.getString("files.folder.source.name")))
					.append("\n");
		} catch (IOException e) {
			output.append(e.getMessage()).append("\n");
		}
		try {
			output.append(createFolderByPathWithGettingMessage(Resourcer.getString("files.folder.move.name")))
					.append("\n");
		} catch (IOException e) {
			output.append(e.getMessage()).append("\n");
		}
		try {
			output.append(createFolderByPathWithGettingMessage(
					String.format(Resourcer.getString("files.folder.copy.pathFormat"), File.separator,
							Resourcer.getString("files.folder.copy.name"))))
					.append("\n");
		} catch (IOException e) {
			output.append(e.getMessage()).append("\n");
		}
		return output.toString();
	}

	/**
	 * Creates a folder at the given path.
	 * 
	 * @param folderPath - folder path.
	 * @return Message - the result of the operation (success / error).
	 * @throws IOException in case the folder has already been created.
	 */
	private static String createFolderByPathWithGettingMessage(String folderPath) throws IOException {
		String errorMessage = null;
		File folderCreator = new File(folderPath);
		if (!folderCreator.mkdir()) {
			errorMessage = generateErrorMessage(folderCreator.getAbsolutePath());
			throw new IOException(errorMessage);
		} else {
			return generateSuccessMessage(folderCreator.getAbsolutePath());
		}

	}

	/**
	 * Generates an error message when creating a folder.
	 * 
	 * @param folderPath - folder path.
	 * @return String - an error message when creating a folder.
	 */
	private static String generateErrorMessage(String folderPath) {
		return String.format(Resourcer.getString("files.folder.exceptionFormat"), folderPath);
	}

	/**
	 * Generates a message about successful folder creation.
	 * 
	 * @param folderPath.
	 * @return String a message about successful folder creation.
	 */
	private static String generateSuccessMessage(String folderPath) {
		return String.format(Resourcer.getString("files.folder.successFormat"), folderPath);
	}

}
